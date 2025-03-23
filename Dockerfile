FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/Interview-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]
