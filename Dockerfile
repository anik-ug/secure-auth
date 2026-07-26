# Use Java 17 runtime
FROM eclipse-temurin:17-jdk

# Working directory inside container
WORKDIR /app

# Copy jar file
COPY target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]