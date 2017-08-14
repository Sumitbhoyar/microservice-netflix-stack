package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${spring.cloud.config.server.git.uri}")
    private String property;
    @GetMapping
    public String test(){
        return property;
    }
}
