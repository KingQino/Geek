package io.github.kingqino.week03.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public interface HttpResponseFilter {
    void filter(FullHttpResponse response);
}
