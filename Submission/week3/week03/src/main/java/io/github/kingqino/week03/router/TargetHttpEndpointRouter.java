package io.github.kingqino.week03.router;

import java.util.List;
import java.util.Random;

public class TargetHttpEndpointRouter implements HttpEndpointRouter{

    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));
//        urls.stream().filter(url -> url.contains("/backendserver"))
//        return urls
    }
}
