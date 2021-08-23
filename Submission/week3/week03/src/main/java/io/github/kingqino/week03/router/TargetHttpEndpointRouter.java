package io.github.kingqino.week03.router;

import java.util.List;

public class TargetHttpEndpointRouter implements HttpEndpointRouter{

    @Override
    public String route(List<String> urls) {
        return urls.get(0);
    }
}
