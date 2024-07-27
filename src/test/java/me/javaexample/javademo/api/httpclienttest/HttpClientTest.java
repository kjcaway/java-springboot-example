package me.javaexample.javademo.api.httpclienttest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("api")
public class HttpClientTest {

    @Test
    public void test() throws IOException, InterruptedException {
        var httpClient = HttpClient.newBuilder().build();
        var request = HttpRequest.newBuilder()
            .uri(URI.create("https://browserleaks.com/tls"))
            .header("Authorization", "Basic test")
            .GET().build();
        var resp = httpClient.send(request, BodyHandlers.ofString());

        System.out.println(resp.statusCode());
        System.out.println(resp.body());
    }
}
