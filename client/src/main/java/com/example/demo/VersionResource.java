package com.example.demo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("account")
public interface VersionResource {
    @RequestMapping(value = "/version", method = GET)
    String version();
}
