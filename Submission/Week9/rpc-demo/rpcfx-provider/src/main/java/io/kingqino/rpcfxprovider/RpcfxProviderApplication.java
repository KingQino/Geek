package io.kingqino.rpcfxprovider;

import io.kingqino.rpcfxcore.netty.server.RpcNettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RpcfxProviderApplication implements ApplicationRunner {


    private RpcNettyServer rpcNettyServer;

    public RpcfxProviderApplication(RpcNettyServer rpcNettyServer) {
        this.rpcNettyServer = rpcNettyServer;
    }

    public static void main(String[] args) {

        SpringApplication.run(RpcfxProviderApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            rpcNettyServer.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rpcNettyServer.destroy();
        }
    }
}
