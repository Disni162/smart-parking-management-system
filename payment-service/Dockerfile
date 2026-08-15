FROM eclipse-temurin:21-jre
LABEL authors="disni-maleesha"
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]