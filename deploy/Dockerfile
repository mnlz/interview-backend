# 直接使用本地构建的JAR文件
FROM openjdk:11-jre-slim
WORKDIR /app
COPY Interview-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]
