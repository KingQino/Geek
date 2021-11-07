package io.github.kingqino.week03.inbound;

import io.github.kingqino.week03.filter.HeaderHttpRequestFilter;
import io.github.kingqino.week03.filter.HttpRequestFilter;
import io.github.kingqino.week03.outbound.httpclient4.HttpOutboundHandler;
import io.github.kingqino.week03.outbound.okhttp.OkHttpOutboundHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.log4j.Log4j2;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


@Log4j2
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private List<String> proxyServer;
//    private HttpOutboundHandler handler;
    private OkHttpOutboundHandler handler;
    private HttpRequestFilter filter = new HeaderHttpRequestFilter();

    public HttpInboundHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
//        this.handler = new HttpOutboundHandler(this.proxyServer);
        this.handler = new OkHttpOutboundHandler(this.proxyServer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();

            if (uri.contains("/backend")) {
                System.out.println("开始请求backend服务");
                handler.handle(fullRequest, ctx, filter);
                return;
            }

            System.out.println("其他请求，门户服务器直接响应");
            handlerTest(fullRequest, ctx);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 一些简单请求，门户服务器直接响应请求
     *
     * @param fullRequest HTTP请求
     * @param ctx ChannelHandlerContext
     */
    private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            String value = "hello, this is gateway server providing basic services.";
//            String value = "";
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            response.headers().set("Access-Control-Allow-Origin", "*");


        } catch (Exception e) {
            log.error("处置接口错误" + e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    assert response != null;
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
