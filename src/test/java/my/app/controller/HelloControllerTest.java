package my.app.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import javax.inject.Inject;

@MicronautTest
public class HelloControllerTest {
    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    void testHelloIndex() {
        final String result = client.toBlocking().retrieve(HttpRequest.GET("/hello"), String.class);
        assertEquals(
                "hello world!!",
                result
        );
    }

    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar" })
    void testHelloIndexPath(String path) {
        final String result = client.toBlocking().retrieve(HttpRequest.GET("/hello/" + path), String.class);
        assertEquals(
                path,
                result
        );
    }
}
