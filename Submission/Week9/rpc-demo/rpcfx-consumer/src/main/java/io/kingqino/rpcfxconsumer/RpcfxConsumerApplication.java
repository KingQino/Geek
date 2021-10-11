package io.kingqino.rpcfxconsumer;

import io.kingqino.rpcfxapi.entity.User;
import io.kingqino.rpcfxapi.service.UserService;
import io.kingqino.rpcfxcore.proxy.RpcClient;
import io.kingqino.rpcfxcore.proxy.RpcClientJdk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@Slf4j
public class RpcfxConsumerApplication {

    public static void main(String[] args) {
        RpcClient jdk = new RpcClientJdk();
        UserService userService = jdk.create(UserService.class, "http://localhost:8080/");
        User user = userService.findById(1);
        if (user == null) {
            log.info("Client invoke service From server Error");
            return;
        }
        System.out.println("find user id=1 from server: " + user.getName());



//        SpringApplication.run(RpcfxConsumerApplication.class, args);
    }

}
