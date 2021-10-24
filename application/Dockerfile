FROM openjdk:8-jdk-alpine
MAINTAINER Karthik_M
RUN apk --no-cache add curl
COPY target/url-shortener-service-*.jar app.jar
EXPOSE 8080