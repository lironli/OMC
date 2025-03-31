

# sensor-registration/Dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/sensor-registration-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar", "--server.address=0.0.0.0"]

