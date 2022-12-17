FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8761
ADD ./target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
