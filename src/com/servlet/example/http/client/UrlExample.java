package com.servlet.example.http.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class UrlExample {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.google.com");
        URLConnection connection = url.openConnection();

        //для того чтобы в запросе передать тело(например POST запрос)
        //необходимо флаг doOutput в URLConnection установить в true
        //и затем в OutputStream передать тело запроса
        connection.setDoOutput(true);
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write("Hello World!".getBytes());
        }

        URL fileURL =
                new URL("file:/run/media/grayroom/HDD/MyProjects/servlet-example/src/com/servlet/example/http/client/UrlExample"
                        + ".java");
        URLConnection fileConnection = fileURL.openConnection();
        System.out.println(new String(fileConnection.getInputStream().readAllBytes()));
    }
}
