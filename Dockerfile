FROM openjdk:21.0.2
WORKDIR /grpc_server
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar app.jar
EXPOSE 8050
