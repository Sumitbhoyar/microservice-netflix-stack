microservice-netflix-stack
============================

**Discovery Server**

Eureka is a REST (Representational State Transfer) based service that is primarily used in the AWS cloud for locating services for the purpose of load balancing and failover of middle-tier servers. It allows services to find and communicate with each other without hard coding hostname and port. 

https://github.com/Sumitbhoyar/notes/blob/master/Eureka.adoc

**Feign Client**

a declarative HTTP client developed by Netflix.

Feign aims at simplifying HTTP API clients. Simply put, the developer needs only to declare and annotate an interface while the actual implementation will be provisioned at runtime.

Configuration

1. Dependency > spring-cloud-starter-feign

2. @EnableFeignClients on @SpringBootApplication




Typical Feign looks like this
 ```java
@FeignClient("account")
public interface VersionResource {
    @RequestMapping(value = "/version", method = GET)
    String version();
}
 ```
 
Spring Feign integration supports integration with service discovery clients like Eureka, Consul or ZooKeeper.

**Spring Cloud Config**

Spring Cloud Config provides server and client-side support for externalized configuration in a distributed system. With the Config Server you have a central place to manage external properties for applications across all environments. 

- Configuration

Need to create a seperate project which will act as **Config Server**.

1. Dependency > spring-cloud-config-server

2. Add @EnableConfigServer on @SpringBootApplication

3. Specify the location of git repository in application.properties where config files are placed.
spring.cloud.config.server.git.uri=https://github.com/piomin/sample-config-repo.git

**Client application** configuration

1. Dependency > spring-cloud-config-server

2. You don't need local application.yml file in client project.

create bootstrap.yml for specifying config file deails

```java
spring.application.name=account
spring.cloud.config.name=account
spring.cloud.config.discovery.service-id=config
spring.cloud.config.discovery.enabled=true
spring.cloud.config.profile=dev
```

3. Inject properties using @ConfigurationProperties or @Value("${…}")

Reference: https://cloud.spring.io/spring-cloud-config/spring-cloud-config.html



http://localhost:8003/account/version
