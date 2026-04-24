FROM eclipse-temurin:21-jdk

COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "user-service.jar"]
