FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /src
COPY pom.xml
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-termurin:21-jre-alpine
WORKDIR /app
COPY --from=build /src/target/*.jar taskmanager.jar
EXPOSE 8080 8081
ENTRYPOINT ["java", "-jar", "taskmanager.jar"]
