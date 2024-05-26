# Build stage
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app
COPY . .
RUN ./gradlew clean bootJar

# Run stage
FROM eclipse-temurin:21-jdk-alpine AS runner

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]