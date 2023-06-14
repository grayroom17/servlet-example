package com.servlet.example.http.socker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketRunner {

    public static void main(String[] args) throws IOException {
        InetAddress inetAddress = InetAddress.getByName("google.com");
        try (Socket socket = new Socket(inetAddress, 80);
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream inputStream = new DataInputStream(socket.getInputStream())) {
            outputStream.writeUTF("Hello world!");
            byte[] response = inputStream.readAllBytes();
            System.out.println(response.length);
        }
    }
}
