package com.servlet.example.http.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HttpServerRunner {
    private final ExecutorService executorService;
    private final int port;
    private boolean stopped;

    public HttpServerRunner(int port) {
        this.port = port;
        this.executorService = null;
    }

    public HttpServerRunner(int port, int poolSize) {
        this.port = port;
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    public void run() {
        try {
            Socket socket;
            try (ServerSocket server = new ServerSocket(port)) {
                socket = server.accept();
                System.out.println("Socket accepted");
                processSocket(socket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void runAsync() {
        try {
            Socket socket;
            try (ServerSocket server = new ServerSocket(port)) {
                while (!stopped) {
                    socket = server.accept();
                    Socket finalSocket = socket;
                    executorService.submit(() -> processSocket(finalSocket));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void processSocket(Socket socket) {
        try (socket;
             var inputStream = new DataInputStream(socket.getInputStream());
             var outputStream = new DataOutputStream(socket.getOutputStream())) {
            System.out.println("Request: " + new String(inputStream.readNBytes(400)));
            TimeUnit.SECONDS.sleep(5);

            var body = Files.readAllBytes(Path.of("resources", "example.html"));
            var headers = """
                          HTTP/1.1 200 OK
                          content-type: text/html
                          content-length: %s
                          """.formatted(body.length);
            outputStream.write(headers.getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var server = new HttpServerRunner(5758, 100);
//        server.run();
        server.runAsync();
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}
