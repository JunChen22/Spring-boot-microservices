# Spring-boot-microservices

Simple microservice project from Hands-On Microservices with Spring Boot and Spring Cloud By Magnus Larsson.
. Major difference is Maven instead of Gradle and Postgres instead of MySQL.

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

- maven build
- docker-compose build
- docker-compose up






Problems I encountered Q and A

- more configuration error rather than coding problem
- Gradle to maven conversion
- Pom multi module project ordering
- docker can't build when perssistance is added, needs to have databaes running to build
  . needs to have embeded database(H2 for rsdb , and flapdoodle for no sql) for testing to build
- Embeded/in-memory MongoDB,Plapdoodle doesn't work with certain version of Spring boot. Spring boot 2.5 and later need to check documentation.
  will cause mongo template error.(Use Testcontainers instead now) Plapdoodle is not MongoDB official , so it's not exact

need a centralized place to store version of each dependecy in maven
- pom property is where to put version number


Mapstruct error with IntelliJ
- version 1.3.1 final don't work with IntelliJ
  There are several intellij bugs here. If I change the maven version 1.3.0.Final to 1.4.1.Final the intellij don't want to update the version.
-
might have issues with perssistance testing scope in pom.xml


no H2 Testcontainers is not as performant as H2, but does give you the benefit of 100% database compatibility




Pre-requist material:
- Shell scripting
  https://bash.cyberciti.biz/guide/Shebang
  https://www.youtube.com/watch?v=v-F3YLd6oMw

- docker
- 