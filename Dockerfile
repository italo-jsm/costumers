FROM openjdk:8 AS build-env
WORKDIR /usr/src/app
COPY / ./
RUN apt-get update && apt-get install maven -y && mvn install && mv /usr/src/app/target/*.jar /usr/src/app/app.jar
FROM alpine
COPY --from=build-env /usr/src/app/app.jar .
RUN apk update && apk add openjdk8-jre
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar"]