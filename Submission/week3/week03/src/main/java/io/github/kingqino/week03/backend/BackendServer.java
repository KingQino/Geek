package io.github.kingqino.week03.backend;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 创建了一个固定大小的线程池处理请求
@Component
public class BackendServer {

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

//    public static void main(String[] args) throws IOException {
//        ExecutorService executorService = Executors.newFixedThreadPool(
//                Runtime.getRuntime().availableProcessors() * 4);
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        final ServerSocket serverSocket = new ServerSocket(8808);
//        while (true) {
//            try {
//                final Socket socket = serverSocket.accept();
//                executorService.execute(() -> service(socket));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

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

}
