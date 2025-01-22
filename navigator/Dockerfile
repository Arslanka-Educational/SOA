FROM gradle:7.4.2-jdk17 as builder

USER root
RUN apt-get update && apt-get install -y nodejs npm

RUN npm install -g @openapitools/openapi-generator-cli

WORKDIR /navigator

COPY gradlew /navigator/
COPY gradle /navigator/gradle
COPY build.gradle.kts /navigator/
COPY settings.gradle.kts /navigator/
COPY src /navigator/src

COPY payara-micro-6.2025.1.jar /navigator/

RUN chmod +x gradlew

RUN ./gradlew build;

RUN ./gradlew deploy;

EXPOSE 8080