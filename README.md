microservice-netflix-stack
============================

**Discovery Server**
--------------------

Eureka is a REST (Representational State Transfer) based service that is primarily used in the AWS cloud for locating services for the purpose of load balancing and failover of middle-tier servers. It allows services to find and communicate with each other without hard coding hostname and port. 

References: 

https://github.com/Sumitbhoyar/notes/blob/master/Eureka.adoc

http://www.baeldung.com/spring-cloud-netflix-eureka

**Feign Client**
----------------

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

References:

https://jmnarloch.wordpress.com/2015/08/19/spring-cloud-designing-feign-client/

https://github.com/OpenFeign/feign

http://www.baeldung.com/intro-to-feign

https://exampledriven.wordpress.com/2016/07/01/spring-cloud-eureka-ribbon-feign-example/


**Spring Cloud Config**
-----------------------

Spring Cloud Config provides server and client-side support for externalized configuration in a distributed system. With the Config Server you have a central place to manage external properties for applications across all environments. 

- Configuration

Need to create a seperate project which will act as **Config Server**.

1. Dependency > spring-cloud-config-server

2. Add @EnableConfigServer on @SpringBootApplication

3. Specify the location of git repository in application.properties where config files are placed.

```
spring.cloud.config.server.git.uri=https://github.com/piomin/sample-config-repo.git
```

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

**Reference**: 
https://cloud.spring.io/spring-cloud-config/spring-cloud-config.html

http://www.baeldung.com/spring-cloud-configuration

https://spring.io/guides/gs/centralized-configuration/

http://localhost:8003/account/version

**Zuul Proxy**
--------------

The Cross-Origin Resource Sharing (CORS) mechanism gives web servers cross-domain access controls, which enable secure cross-domain data transfers. The Cross-Origin Resource Sharing standard works by adding new HTTP headers that allow servers to describe the set of origins that are permitted to read that information using a web browser.

UI application may need to proxy calls to one or more back end services. UI application cannot call multiple domain due to Same Origin Policy restriction of the browser.
Zuul provides work around CORS and the Same Origin Policy restriction of the browser and allow the UI to call the API even though they don’t share the same origin. 
This feature is useful for a user interface to proxy to the backend services it requires, avoiding the need to manage CORS and authentication concerns independently for all the backends.
Zuul is a JVM based router and server side load balancer by Netflix.

**Configuration**

1. dependency > spring-cloud-starter-zuul

2. Add @EnableZuulProxy on @SpringBootApplication. If you are using Eureka then add @EnableEurekaClient too.

3. application.yml
```
zuul:
  routes:
    foos:
      path: /foos/**
      url: http://localhost:8081/spring-zuul-foos-resource/foos
    account-service:
      path: /account/**
      serviceId: ACCOUNT
      
eureka:
  client:
    serviceUrl:
      defaultZone: ${EUREKA_URI:http://localhost:8001/eureka}
      
ribbon.eureka.enabled=true
```

Zuul provides some default filters as well as allows to add custom filters to interccept the request for authentication, changing/adding request header etc.

Zuul Request lifecycle

[Client] --> HTTP request --> [pre filters --> Custom filters --> Routing filters] --> [Origin Server] --> [Error filters if any error occurs --> post filters] --> [Client]

**References:**

https://dzone.com/articles/spring-cloud-netflix-zuul-edge-serverapi-gatewayga

http://www.baeldung.com/spring-rest-with-zuul-proxy

https://spring.io/guides/gs/routing-and-filtering/




