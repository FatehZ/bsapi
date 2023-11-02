# Stage 1: Build
FROM gradle:8.3.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN test -f build/libs/gateway-service.jar || gradle build

# Stage 2: Runtime
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/gateway-service.jar /app/
CMD ["java", "-jar", "gateway-service.jar"]
