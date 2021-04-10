package jp.gr.java_conf.s1r;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.stream.Collectors;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main {

    private static final String SERVER_URL = "http://localhost:3000/";

    public static void main(String[] args) {
        requestWithOkHttp();
        requestWithHttpClient();
    }

    public static void requestWithOkHttp() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(SERVER_URL)
                .build();

        System.out.println("<--- OkHttp Start...");
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("OkHttp ...End --->");
    }

    public static void requestWithHttpClient() {

        System.out.println("<--- JavaHttpClient Start...");

        // Java11のマイナーバージョン（少なくとも11.0.7？）によっては修正済で例外が発生しない
        String version = System.getProperty("java.version");
        System.out.println("Java Version: " + version);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SERVER_URL))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            HttpHeaders responseHeaders = response.headers();
            responseHeaders.map().entrySet().stream().forEach(e -> {
                System.out.println(e.getKey() + ": " + e.getValue().stream().collect(Collectors.joining(", ")));
            });

            System.out.println(response.body().toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("JavaHttpClient ...End --->");
    }
}
