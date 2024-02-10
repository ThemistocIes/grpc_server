FROM openjdk:21
WORKDIR /stockPricesApp
COPY build/libs/grpc_server-0.0.2.jar grpc_server-0.0.2.jar

EXPOSE 8050
ENTRYPOINT ["java", "-jar", "grpc_server-0.0.2.jar"]
