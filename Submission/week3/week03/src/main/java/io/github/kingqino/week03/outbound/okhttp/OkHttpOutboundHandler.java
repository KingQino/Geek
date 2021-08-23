package io.github.kingqino.week03.outbound.okhttp;

import io.github.kingqino.week03.filter.HeaderHttpResponseFilter;
import io.github.kingqino.week03.filter.HttpRequestFilter;
import io.github.kingqino.week03.filter.HttpResponseFilter;
import io.github.kingqino.week03.router.HttpEndpointRouter;
import io.github.kingqino.week03.router.TargetHttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import okhttp3.Response;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static io.github.kingqino.week03.utils.OkHttpUtils.getCall;

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
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
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
        return backend.endsWith("/") ? backend.substring(0,backend.length()-1) : backend;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);
        proxyService.submit(()->fetchGet(fullRequest, ctx, url));
    }

    private void fetchGet(FullHttpRequest fullRequest, ChannelHandlerContext ctx, String url) {
        Response response = getCall(url);
        ctx.write(response);
        ctx.flush();
    }


}
