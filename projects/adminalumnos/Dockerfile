
FROM openjdk:17-jdk-slim


WORKDIR /app


COPY target/adminalumnos-0.0.1-SNAPSHOT.jar adminalumnos.jar


EXPOSE 8081


ENTRYPOINT ["java", "-jar", "adminalumnos.jar"]
