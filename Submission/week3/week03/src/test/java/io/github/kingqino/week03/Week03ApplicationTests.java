package io.github.kingqino.week03;

import io.github.kingqino.week03.backend.BackendServer;
import io.github.kingqino.week03.inbound.HttpInboundServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;

@SpringBootTest
class Week03ApplicationTests {

    @Autowired
    BackendServer backendServer;

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";

    @Test
    void contextLoads() {

        Runnable runnable = () -> {
            try {
                backendServer.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("The Back-end Server is Ready ...");
        System.out.println("--------------------------------");

        String proxyPort = System.getProperty("proxyPort","8888");

        String backendServers = System.getProperty("proxyServers","http://localhost:8808");

        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" Starting ...");
        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(backendServers.split(",")));
        System.out.println("The gateway server will be deployed at http://localhost:" + port + ", and the corresponding backend server is " + server);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
