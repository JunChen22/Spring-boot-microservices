# Spring-boot-microservices
[![Java CI with Maven](https://github.com/JunChen22/Spring-boot-microservices/actions/workflows/maven.yml/badge.svg)](https://github.com/JunChen22/Spring-boot-microservices/actions/workflows/maven.yml)

Simple microservice project from Hands-On Microservices with Spring Boot and Spring Cloud By Magnus Larsson.
. Major difference is Maven instead of Gradle and Postgres instead of MySQL.

-----------

### Plans
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


rabbitmq
localhost:15672   // username and password : guest

https://localhost:8443/eureka/web  // username: u password: p

```
Spring-boot-microservice 
├── product-composite-service -- agregated service 
│     ├── product-service 
│     ├── recommendation-service
│     └── review-service
├──spring-cloud               -- spring cloud servers/functionalities
│     ├── OAuth / Authorization server 
│     ├── Eureka / Discovery server
│     └── Gateway / Edge server
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
| JDK           | 16      | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Postgres      | latest  | https://www.postgresql.org/                                                          |
| Redis         | latest  | https://redis.io/download                                                            |
| MongoDB       | latest  | https://www.mongodb.com/download-center                                              |
| RabbitMQ      | latest  | http://www.rabbitmq.com/download.html                                                |
| Nginx         | latest  | http://nginx.org/en/download.html                                                    |
| Elasticsearch | 7.11.1  | https://www.elastic.co/downloads/elasticsearch                                       |
| Logstash      | 7.11.1  | https://www.elastic.co/cn/downloads/logstash                                         |
| Kibana        | 7.11.1  | https://www.elastic.co/cn/downloads/kibana                                           |

```JSON
COMPOSITE_DATA_JSON= '{
                  "productId":1,"name":"product name A","weight":100,
"recommendations": [{"recommendationId": 1,"author": "author 1","rate": 5,"content":"content 1"},
                    {"recommendationId": 2,"author": "author 1","rate": 5,"content":"content 1"},
                    {"recommendationId": 3,"author": "author 1","rate": 5,"content":"content 1"}], 
"reviews":[{"reviewId":1,"author":"author 1","subject":"subject 1","content":"content 1"},
          {"reviewId":2,"author":"author 2","subject":"subject 2","content":"content 2"},    
          {"reviewId":3,"author":"author 3","subject":"subject 3","content":"content 3"} ]
}'
```
(The product composite JSON have 1 product, 3 recommendations, and 3 reviews)
(Removes the new lines, it's shown here to display better)

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
curl -X POST http://localhost:8080/product-composite -H "Content-Type: application/json" --data {COMPOSITE_DATA_JSON}
curl localhost:8080/product-composite/1 | jq
curl -X "DELETE" localhost:8080/product-composite/1


testing other docker-compose needs to post a productId 2 request



could also scale up a particular service in docker
$ docker-compose up -d --scale review=2










Testing urls
curl http://localhost:8080/actuator/gateway/routes -s | jq # to see all the routes in the edge server/gateway

http://localhost:8080/eureka/web
or see what running on eureka in command line
curl -H "accept:application/json" localhost:8080/eureka/api/apps -s | jq -r .applications.application[].instance[].instanceId


other url for testing for the edge server, it connects to http://httpstat.us/ for api test
curl http://localhost:8080/headerrouting -H "Host: i.feel.lucky:8080"   # code 200 ok

curl http://localhost:8080/headerrouting -H "Host: im.a.teapot:8080"    # code 418

curl http://localhost:8080/headerrouting                                # code 501





After spring security, it needed to have access token to call protected APIs.
There are 5 types of grant flow types(ways) to get access token from authorization server.
There a two authorization used in this project. One is local and one is on the auth0 website.
Both functions the same but the local authorization is used for simple test and security.

### Grant flows types:

- Authorization Code
- Implicit
- Resource Owner Credentials
- Client Credentials
- Refresh Token



### Client Credentials Grant flow
Testing on local authorization server

$ curl -k https://reader:secret@localhost:8443/oauth2/token -d grant_type=client_credentials -s | jq
will return a long access token

Using the long access token to call protected APIs
curl -H "Authorization: Bearer (long token)" -k https://localhost:8443/product-composite/1

This client only have read scope and will return 403 error code if it tries to do create or delete


getting writer
curl -k https://writer:secret@localhost:8443/oauth2/token -d grant_type=client_credentials -s | jq
curl -X POST -H "Authorization: Bearer (LONG TOKEN)" -k https://localhost:8443/product-composite/ "Content-Type: application/json" --data '{COMPOSITE_DATA_JSON}' -s -w "%{http_code}"

delete 

curl -X DELETE -H "Authorization: Bearer (LONG TOKEN)" -k https://localhost:8443/product-composite/1 -w "%{http_code}"

adding -w "%{http_code}" will return request status code 

client credential can used for automatic testing with test script







### Authorization Code Grant Type on local authorization server

Acquiring access tokens using the authorization code grant flow on local authorization server.

getting a reader client authorization code, need to use browser.

https://localhost:8443/oauth2/authorize?response_type=code&client_id=reader&redirect_uri=https://my.redirect.uri&scope=product:read&state=35725

user name: u        // or what is set on authorization configuration
password: p

will be redirected to a URL with the authorization code. 
For example:

https://my.redirect.uri/?code=(LONG_AUTHORIZATION_CODE)&state=35725

AUTHORIZATION_CODE=(LONG_AUTHORIZATION_CODE)

using that authorization code to get an access token.

```
$ curl -k https://reader:secret@localhost:8443/oauth2/token \
-d grant_type=authorization_code \
-d client_id=reader \
-d redirect_uri=https://my.redirect.uri \
-d code=AUTHORIZATION_CODE -s | jq .
```

then an access token with read scope will be given.







getting a reader authorization code.


https://localhost:8443/oauth2/authorize?response_type=code&client_id=writer&redirect_uri=https://my.redirect.uri&scope=product:read+product:write&state=72489

https://my.redirect.uri/?code=LONG_AUTHORIZATION_CODE&state=72489

AUTHORIZATION_CODE=(LONG_AUTHORIZATION_CODE)

```
curl -k https://writer:secret@localhost:8443/oauth2/token \
-d grant_type=authorization_code \
-d client_id=writer \
-d redirect_uri=https://my.redirect.uri \
-d code=AUTHORIZATION_CODE -s | jq .
```

then an access token with create and delete scope will be given.








### Changing authorization server to Oauth

- create account in https://auth0.com/
- get tenant domain id, client ID, and client secret.
- put those information in to env.bash
- and put email and password to create a new user

changing from local authorization server to Auth0 only need to change two service.
product-composite and gateway

change the spring.security.oauth2.resourceserver.jwt.issuer-uri: https://${TENANT}/
in application.yml


```
$ ./setup-tenant.bash     // to get infomation from OAuth 
```

export TENANT=...
export WRITER_CLIENT_ID=...
export WRITER_CLIENT_SECRET=...
export READER_CLIENT_ID=...
export READER_CLIENT_SECRET=...


$ curl https://${TENANT}/.well-known/openid-configuration -s | jq to show information about ur tenant domain




```
change this line in setup-test.bash

ACCESS_TOKEN=$(curl -k https://writer:secret@$HOST:$PORT/oauth2/token -d grant_type=client_credentials -s | jq .access_token -r)

to 

export TENANT=...
export WRITER_CLIENT_ID=...
export WRITER_CLIENT_SECRET=...

ACCESS_TOKEN=$(curl -X POST https://$TENANT/oauth/token \
  -d grant_type=client_credentials \
  -d audience=https://localhost:8443/product-composite \
  -d scope=product:read+product:write \
  -d client_id=$WRITER_CLIENT_ID \
  -d client_secret=$WRITER_CLIENT_SECRET -s | jq -r .access_token)
  
 and 
 
 READER_ACCESS_TOKEN=$(curl -k https://reader:secret@$HOST:$PORT/oauth2/token -d grant_type=client_credentials -s | jq .access_token -r)
 
 to
 
export READER_CLIENT_ID=...
export READER_CLIENT_SECRET=...

READER_ACCESS_TOKEN=$(curl -X POST https://$TENANT/oauth/token \
  -d grant_type=client_credentials \
  -d audience=https://localhost:8443/product-composite \
  -d scope=product:read \
  -d client_id=$READER_CLIENT_ID \
  -d client_secret=$READER_CLIENT_SECRET -s | jq -r .access_token)
```

```
$ ./setup-test.bash    // to see if it works/connects to OAuth server
```



###  Client Credential grant flow on Oatuh

export TENANT=...
export WRITER_CLIENT_ID=...
export WRITER_CLIENT_SECRET=...
curl -X POST https://$TENANT/oauth/token \
-d grant_type=client_credentials \
-d audience=https://localhost:8443/product-composite \
-d scope=product:read+product:write \
-d client_id=$WRITER_CLIENT_ID \
-d client_secret=$WRITER_CLIENT_SECRET

or 

export READER_CLIENT_ID=...
export READER_CLIENT_SECRET=...

READER_ACCESS_TOKEN=$(curl -X POST https://$TENANT/oauth/token \
-d grant_type=client_credentials \
-d audience=https://localhost:8443/product-composite \
-d scope=product:read \
-d client_id=$READER_CLIENT_ID \
-d client_secret=$READER_CLIENT_SECRET -s | jq -r .access_token)



to get reader or writer client access token.




### Authorization Code Grant Type in OAuth

go to browser
https://${TENANT}/authorize?audience=https://localhost:8443/product-composite&scope=openid email product:read product:write&response_type=code&client_id=${WRITER_CLIENT_ID}&redirect_uri=https://my.redirect.uri&state=845361.

username: u
password: p

it will redirect to a URL with the code in it.

use the authorization code to change for access token with write scope

CODE=...
export TENANT=...
export WRITER_CLIENT_ID=...
export WRITER_CLIENT_SECRET=...
curl -X POST https://$TENANT/oauth/token \
-d grant_type=authorization_code \
-d client_id=$WRITER_CLIENT_ID \
-d client_secret=$WRITER_CLIENT_SECRET  \
-d code=$CODE \
-d redirect_uri=https://my.redirect.uri -s | jq .


to get access token with read scope

CODE=...
export TENANT=...
export READER_CLIENT_ID=...
export READER_CLIENT_SECRET=...
curl -X POST https://$TENANT/oauth/token \
-d grant_type=authorization_code \
-d client_id=READER_CLIENT_ID \
-d client_secret=READER_CLIENT_SECRET  \
-d code=$CODE \
-d redirect_uri=https://my.redirect.uri -s | jq .





ACCESS_TOKEN=...
### calling protected APIs

ACCESS_TOKEN=an-invalid-token
curl https://localhost:8443/product-composite/1 -k -H "Authorization: Bearer $ACCESS_TOKEN" -i
will return unauthorized code 401.


GOOD_TOKEN=LONG_ACCESS_CODE
curl https://localhost:8443/product-composite/1 -k -H "Authorization: Bearer $GOOD_TOKEN" -i
will return code 200 success.

ACCESS_TOKEN=...
curl https://localhost:8443/product-composite/999 -k -H "Authorization: Bearer $ACCESS_TOKEN" -X DELETE -i




### extra information about the user
Export TENANT=...
curl -H "Authorization: Bearer $ACCESS_TOKEN" https://$TENANT/userinfo -s | jq


swgger page
https://localhost:8443/openapi/swagger-ui.html







- Implicit Grant Type
- Resource Owner Credentials Grant Type
- Refresh Token Grant





curl localhost:8080/actuator/health | jq

need to add TestSecurityConfig.class to disable security in test

use this command to create self-signed certificate
$ keytool -genkeypair -alias localhost -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore edge.p12 -validity 3650
it generates edge.p12 file in gateway.../resource/keystore/




### material:
- Shell scripting
  https://bash.cyberciti.biz/guide/Shebang
  https://www.youtube.com/watch?v=v-F3YLd6oMw
- book author,https://www.packtpub.com/product/microservices-with-spring-boot-and-spring-cloud-second-edition/9781801072977
- docker
- https://www.baeldung.com/spring-security-oauth-auth-server


