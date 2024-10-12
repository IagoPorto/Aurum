FROM amazoncorretto:23-jdk

WORKDIR /app

COPY aurum-core/target/*.jar app.jar

EXPOSE 8765

ENTRYPOINT ["java", "-jar", "app.jar"]