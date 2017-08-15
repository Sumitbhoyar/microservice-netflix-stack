package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/version")
public class ClientController {
    @Autowired
    private VersionService versionService;

    @GetMapping
    public String getVersion(){
        return versionService.version();
    }
}
