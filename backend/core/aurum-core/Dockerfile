FROM amazoncorretto:23-alpine3.20-full

WORKDIR /app

# Install required tools
RUN apk add --no-cache \
    bash \
    tar \
    unzip

COPY mvnw ./
COPY .mvn .mvn
COPY src ./src
COPY pom.xml ./

RUN ls -l

# Run Maven package command and execute tests
RUN ./mvnw clean package

# Copy the jar to the final image
COPY target/*.jar app.jar

EXPOSE 8765

ENTRYPOINT ["java", "-jar", "app.jar"]
