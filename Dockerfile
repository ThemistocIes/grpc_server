FROM openjdk:21-jdk-alpine
WORKDIR /stockPricesApp
COPY build/libs/grpc_server-0.0.2.jar app.jar

EXPOSE 8050
ENTRYPOINT ["java","-jar","/grpc_server-0.0.2.jar"]
