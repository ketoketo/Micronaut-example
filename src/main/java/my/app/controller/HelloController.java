package my.app.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import my.app.service.HelloService;

@Controller("/hello")
public class HelloController {
    HelloService helloService;

    HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    public String index() {
        return "hello world!!";
    }

    @Get(uri = "/{path}",processes = MediaType.TEXT_PLAIN)
    public String index(String path) {
        return path;
    }

    @Get(uri = "/compute/{number}",processes = MediaType.TEXT_PLAIN)
    public String compute(Integer number) {
        return String.valueOf(helloService.compute(number));
    }
}
