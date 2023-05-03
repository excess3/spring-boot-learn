package com.springboot.learnspringboot.helloworld;

import com.springboot.learnspringboot.helloworld.HelloWorldBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    //http://localhost:9090/hello
    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public HelloWorldBean helloWorld() {

        logger.debug("Request param is {}", "Say Hello");
        return new HelloWorldBean("Hello World");

    }
}
