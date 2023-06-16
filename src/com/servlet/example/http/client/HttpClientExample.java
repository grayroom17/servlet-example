package com.servlet.example.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import static java.net.http.HttpRequest.BodyPublishers;
import static java.net.http.HttpRequest.newBuilder;

public class HttpClientExample {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest request = newBuilder(URI.create("https://www.google.com"))
                .GET()
                .build();

        HttpRequest request1 = newBuilder(URI.create("https://www.google.com"))
                .POST(BodyPublishers.ofFile(Path.of("path", "to", "file")))
                .build();


        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println("________________________________________________________________");
        System.out.println(response.headers());
    }

}