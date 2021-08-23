package io.github.kingqino.week03.outbound.okhttp;

import io.github.kingqino.week03.filter.HeaderHttpResponseFilter;
import io.github.kingqino.week03.filter.HttpRequestFilter;
import io.github.kingqino.week03.filter.HttpResponseFilter;
import io.github.kingqino.week03.router.HttpEndpointRouter;
import io.github.kingqino.week03.router.TargetHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import okhttp3.Response;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static io.github.kingqino.week03.utils.OkHttpUtils.getCall;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkHttpOutboundHandler {
    private CloseableHttpAsyncClient httpclient;
    private ExecutorService proxyService;
    private List<String> backendUrls;


    HttpResponseFilter filter = new HeaderHttpResponseFilter();
    HttpEndpointRouter router = new TargetHttpEndpointRouter();

    public OkHttpOutboundHandler(List<String> backends) {

        this.backendUrls = backends.stream().map(this::formalUrl).collect(Collectors.toList());

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);

        IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();

        httpclient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioConfig)
                .setKeepAliveStrategy((response,context) -> 6000)
                .build();
        httpclient.start();
    }

    private String formalUrl(String backend) {
        return backend.endsWith("/") ? backend.substring(0, backend.length() - 1) : backend;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);
        proxyService.submit(()->fetchGet(fullRequest, ctx, url));
    }

    private void fetchGet(FullHttpRequest fullRequest, ChannelHandlerContext ctx, String url) {
        Response response = getCall(url);

        String content = null;
        try {
            content = Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert content != null;

        FullHttpResponse response_answer = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(content.getBytes(StandardCharsets.UTF_8)));
        response_answer.headers().set("Content-Type", "application/json");
        response_answer.headers().setInt("Content-Length", Integer.parseInt(String.valueOf(Objects.requireNonNull(response.body()).contentLength())));

        filter.filter(response_answer);

        ctx.write(response_answer);
        ctx.flush();
        ctx.close();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
