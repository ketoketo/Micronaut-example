package my.app.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")
public class HelloController {
    @Get(produces = MediaType.TEXT_PLAIN)
    public String index() {
        return "hello world!!";
    }

    @Get(uri = "/{path}",processes = MediaType.TEXT_PLAIN)
    public String index(String path) {
        return path;
    }
}
