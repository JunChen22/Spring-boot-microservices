# Spring-boot-microservices

Simple microservice project from Hands-On Microservices with Spring Boot and Spring Cloud By Magnus Larsson.
. Major difference is Maven instead of Gradle and Postgres instead of MySQL.

-----------

###Plans
- microservices created using spring boot
- change it to using spring cloud
- then containerized it and deployed to kubernetes

-------------------

### to run the program

$ mvn clean package     # build and package
$ docker-compose build  
$ docker-compose up
$ setup-test.bash    # setup test data and simple test

$ export COMPOSE_FILE=docker-compose-partitions.yml // or other docker-compose files
$ docker-compose build
$ docker-compose up
$ unset COMPOSE_FILE

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


Swagger to see APIs
$Host:$PORT/openapi/swagger-ui.html
E.g running on docker localhost:8080/openapi/swagger-ui.html


running on local machine
READ
curl localhost:7000/product-composite/1 | jq
curl localhost:7001/product/1 | jq
curl localhost:7002/recommendation?productId=1 | jq
curl localhost:7003/review?productId=1 | jq

run on docker
curl -X POST http://localhost:8080/product-composite -H "Content-Type: application/json" --data '{"productId":1,"name":"product name A","weight":100,"recommendations": [{"recommendationId": 1,"author": "author 1","rate": 5,"content":"content 1"}], "reviews":[{"reviewId":1,"author":"author 1","subject":"subject 1","content":"content 1"},     {"reviewId":2,"author":"author 2","subject":"subject 2","content":"content 2"},     {"reviewId":3,"author":"author 3","subject":"subject 3","content":"content 3"} ]}'
curl localhost:8080/product-composite/1 | jq
curl -X "DELETE" localhost:8080/product-composite/1


testing other docker-compose needs to post a productId 2 request



could also scale up a particular service in docker
$ docker-compose up -d --scale review=2


material:
- Shell scripting
  https://bash.cyberciti.biz/guide/Shebang
  https://www.youtube.com/watch?v=v-F3YLd6oMw

- docker



curl localhost:8080/actuator/health | jq
