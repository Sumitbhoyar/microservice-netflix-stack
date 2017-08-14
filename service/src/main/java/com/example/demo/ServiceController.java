package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RefreshScope
@RestController
class ServiceController {

    @Value("${example.version}")
    private String version;

    @RequestMapping("/version")
    String getVersion() {
        return this.version;
    }
}