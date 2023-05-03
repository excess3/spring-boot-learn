package com.springboot.learnspringboot.socialmedia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    //http://localhost:9090/login?name=Andrey
    @RequestMapping(method = RequestMethod.GET, path = "/login")
    public String login(@RequestParam String name) {

        logger.debug("Request param is {}", name);
        return name;

    }
}
