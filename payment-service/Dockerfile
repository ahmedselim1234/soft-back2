FROM eclipse-temurin:21-jdk

COPY target/payment-service-0.0.1-SNAPSHOT.jar payment-service.jar

EXPOSE 9002

ENTRYPOINT ["java", "-jar", "payment-service.jar"]
