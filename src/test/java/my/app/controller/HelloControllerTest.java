package my.app.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import my.app.service.HelloService;
import my.app.service.HelloServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@MicronautTest
public class HelloControllerTest {
    @Inject
    @Client("/")
    RxHttpClient client;

    @Inject
    HelloService helloService;

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

    @ParameterizedTest
    @CsvSource({"2,4", "3,9"})
    void testComputeNumToSquare(Integer num, Integer square) {

        when(helloService.compute(num))
                .then(invocation -> Long.valueOf(Math.round(Math.pow(num, 2))).intValue());

        final Integer result = client.toBlocking().retrieve(HttpRequest.GET("/hello/compute/" + num), Integer.class);

        assertEquals(
                square,
                result
        );
        verify(helloService).compute(num);
    }

    @MockBean(HelloServiceImpl.class)
    HelloService mathService() {
        return mock(HelloService.class);
    }
}
