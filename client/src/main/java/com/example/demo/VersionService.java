
package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionService {
    @Autowired
    private VersionClient versionClient;

    @HystrixCommand(fallbackMethod = "defaultVersion")
    public String version() {
        return versionClient.version();
    }
    private String defaultVersion() {
        return "1.0.0.0";
    }
}
