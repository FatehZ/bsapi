# Stage 1: Build
FROM gradle:8.3.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN test -f build/libs/*-SNAPSHOT.jar || gradle build

# Stage 2: Runtime
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*-SNAPSHOT.jar /app/book-ms.jar
CMD ["java", "-jar", "book-ms.jar"]
