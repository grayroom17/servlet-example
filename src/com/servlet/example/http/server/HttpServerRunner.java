package com.servlet.example.http.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServerRunner {

    private final int port;

    public HttpServerRunner(int port) {
        this.port = port;
    }

    public void run() {
        try {
            Socket socket;
            try (ServerSocket server = new ServerSocket(port)) {
                socket = server.accept();
                processSocket(socket);
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

            var body = Files.readAllBytes(Path.of("resources", "example.html"));
            var headers = """
                          HTTP/1.1 200 OK
                          content-type: text/html
                          content-length: %s
                          """.formatted(body.length);
            outputStream.write(headers.getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var server = new HttpServerRunner(5758);
        server.run();
    }

}
