# -------- Stage 1 : Build the application --------
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml first to cache dependencies
COPY pom.xml .



# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests


# -------- Stage 2 : Run the application --------
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose application port
EXPOSE 8081

# Run Spring Boot application
ENTRYPOINT ["java","-jar","app.jar"]