package io.kingqino.rpcfxprovider.config;

import io.kingqino.rpcfxapi.service.OrderService;
import io.kingqino.rpcfxapi.service.UserService;
import io.kingqino.rpcfxprovider.service.impl.OrderServiceImpl;
import io.kingqino.rpcfxprovider.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置接口的实现类
 */
@Configuration
public class BeanConfig {
    @Bean("io.kingqino.rpcfxapi.service.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean("io.kingqino.rpcfxapi.service.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
