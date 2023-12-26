FROM openjdk:20-ea-slim
COPY target/AlcomsurProyect-0.0.1-SNAPSHOT.jar alcomsur-app.jar
ENTRYPOINT ["java", "-jar", "alcomsur-app.jar"]