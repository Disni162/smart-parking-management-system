FROM eclipse-temurin:21-jre
LABEL authors="disni-maleesha"
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]