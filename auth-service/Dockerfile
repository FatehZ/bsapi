# Stage 1: Build
FROM gradle:8.3.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN test -f build/libs/auth-service.jar || gradle build

# Stage 2: Runtime
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/auth-service.jar /app/
CMD ["java", "-jar", "auth-service.jar"]
