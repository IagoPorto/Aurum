FROM amazoncorretto:23-jdk

WORKDIR /app

COPY ./target/aurum-core-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8765

ENTRYPOINT ["java", "-jar", "app.jar"]