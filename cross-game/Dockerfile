FROM openjdk:17
COPY target/matchmaking-0.0.1-SNAPSHOT.jar /app/matchmaking.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "matchmaking.jar"]
EXPOSE 8080