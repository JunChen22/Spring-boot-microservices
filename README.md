# Spring-boot-microservices

Simple microservice project from Hands-On Microservices with Spring Boot and Spring Cloud By Magnus Larsson.

-----------

###Plans
- microservices created using spring boot
- change it to using spring cloud
- then containerized it and deployed to kubernetes

-------------------


```
Spring-boot-microservice 
├── product-composite-service -- agregated service 
│     ├── product-service 
│     ├── recommendation-service
│     └── review-service
├── util - helper classes hanlding HTTP info and exception
└── api - API definitions
```

### Tech stack

| Tech                    | type                    | documentation                           |
|-------------------------|-------------------------|-----------------------------------------|
| SpringBoot              | MVC                     | https://spring.io/projects/spring-boot  |
| SpringCloud/Netflix OSS | cloud                   | https://spring.io/projects/spring-cloud |
| Docker                  | Containerization        | https://www.docker.com/                 |
| Kubernetes              | container orchestration | https://kubernetes.io/                  |
| Istio                   | Service mesh            | https://istio.io/                       |
| Maven                   | Build tool              | https://maven.apache.org/               |



#### Diagram
##### System architecture
##### Business layer


## Environment setup

### Development tools

| Tech     | type          | documentation                    |
|----------|---------------|----------------------------------|
| IntelliJ | IDE           | https://www.jetbrains.com/idea/  |
| Postman  | API platform  | https://www.postman.com/         |


### Development environment

| Tool          | version | Download                                                                             |
|---------------|---------|--------------------------------------------------------------------------------------|
| JDK           | 11      | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Postgres      | latest  | https://www.postgresql.org/                                                          |
| Redis         | latest  | https://redis.io/download                                                            |
| MongoDB       | latest  | https://www.mongodb.com/download-center                                              |
| RabbitMQ      | latest  | http://www.rabbitmq.com/download.html                                                |
| Nginx         | latest  | http://nginx.org/en/download.html                                                    |
| Elasticsearch | 7.11.1  | https://www.elastic.co/downloads/elasticsearch                                       |
| Logstash      | 7.11.1  | https://www.elastic.co/cn/downloads/logstash                                         |
| Kibana        | 7.11.1  | https://www.elastic.co/cn/downloads/kibana                                           |




setup environment 
- Lombok needed IDE configuration/plugin to work.
- doesn't work with mapstruct/mapper unless configured.
https://www.baeldung.com/lombok-ide