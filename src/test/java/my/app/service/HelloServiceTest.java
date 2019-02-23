package my.app.service;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.inject.Inject;

@MicronautTest
public class HelloServiceTest {
    @Inject
    HelloService helloService;

    @ParameterizedTest
    @CsvSource({"2,8", "3,12"})
    void testComputeNumToSquare(Integer num, Integer square) {
        final Integer result = helloService.compute(num);

        Assertions.assertEquals(
                square,
                result
        );
    }
}
