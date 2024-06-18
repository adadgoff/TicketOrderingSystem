FROM gradle:jdk21 as builder

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src
COPY gradlew gradlew.bat ./
COPY gradle ./gradle

RUN chmod +x gradlew
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
