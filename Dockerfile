#FROM java:8-jdk-alpine
FROM openjdk:8

COPY target/*.jar /opt/app.jar

WORKDIR /opt

EXPOSE 9001

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=local", "app.jar"]
