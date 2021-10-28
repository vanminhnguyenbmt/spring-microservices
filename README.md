# Building microservice using Spring Boot and Eureka

- [Netflix OSS framework](#netflix-oss-framework)
  - [Overview](#overview)
  - [Architecture](#architecture)
  - [URL](#url)


## Netflix OSS framework

### Overview
![Netflix OSS's ecosystem](readme/netflix-oss-framework.png)

-  **Service Discovery:** Netflix Eureka
-  **Circuit Breaker:** Netflix Hystrix
-  **Gatekeeper:** Netflix Zuul
-  **Intelligent Routing, Load Balancing:** Netflix Ribbon
-  **Monitoring:** Netflix Hystrix Dashboard and Netflix Turbine
-  **Log Tracing:** Sleuth, Zipkin
-  **Centralized Configuration:** Spring Cloud Config Server
### Architecture
![Netflix OSS's architecture](readme/netflix-oss-architecture.png)

### URL
- Authenticate: http://localhost:8762/auth (admin/12345)
- Gallery: http://localhost:8762/gallery?id=1&isFallBack=false (isFallBack=true will perform fall back method)
- Hystrix Dashboard: http://localhost:8081/hystrix
- Turbine Stream: http://localhost:8989 (used by **Hystrix Dashboard** to monitor stream)
- ![Hystrix Dashboard](readme/hystrix-stream.png)