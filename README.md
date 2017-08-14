microservice-netflix-stack
============================

**Discovery Server**

Eureka is a REST (Representational State Transfer) based service that is primarily used in the AWS cloud for locating services for the purpose of load balancing and failover of middle-tier servers. It allows services to find and communicate with each other without hard coding hostname and port. 

https://github.com/Sumitbhoyar/notes/blob/master/Eureka.adoc

**Feign Client**

a declarative HTTP client developed by Netflix.

Feign aims at simplifying HTTP API clients. Simply put, the developer needs only to declare and annotate an interface while the actual implementation will be provisioned at runtime.

Configuration

1. @EnableFeignClients on @SpringBootApplication

2. Dependency > spring-cloud-starter-feign


Typical Feign looks like this
 ```java
@FeignClient("account")
public interface VersionResource {
    @RequestMapping(value = "/version", method = GET)
    String version();
}
 ```
 
Spring Feign integration supports integration with service discovery clients like Eureka, Consul or ZooKeeper.


http://localhost:8003/account/version