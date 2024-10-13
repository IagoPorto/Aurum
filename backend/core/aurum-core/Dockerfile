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
# Run Maven package command and execute tests
RUN ./mvnw clean package -DskipTests

# Copy the jar to the final image
COPY target/aurum-core-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8765

ENTRYPOINT ["java", "-jar", "app.jar"]
