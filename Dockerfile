#FROM java:8-jdk-alpine
FROM openjdk:8-alpine

ENV TZ=Asia/Seoul

COPY target/common-api-0.0.1-SNAPSHOT.jar /opt/app.jar

WORKDIR /app

EXPOSE 9001

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "app.jar"]
