package io.github.kingqino.week03.backend;

import io.github.kingqino.week03.inbound.HttpInboundServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 创建了一个固定大小的线程池处理请求
@Component
public class BackendServer implements CommandLineRunner {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";

    public void run() throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * 4);

        final ServerSocket serverSocket = new ServerSocket(8808);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> service(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void service(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "Hi, this is backend server providing the core service. \r\nIf you wanna access me, you would have processed the Http request Authentication";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        Runnable runnable = () -> {
            try {
                run();
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
