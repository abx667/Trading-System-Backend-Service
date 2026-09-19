FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/gatewayapp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 7060

ENTRYPOINT ["java", "-jar", "app.jar"]