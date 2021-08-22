package io.github.kingqino.week03.router;

import java.util.List;

public interface HttpEndpointRouter {
    String route(List<String> endpoints);
}
