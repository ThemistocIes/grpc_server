package home.work.grpc_server.stock_price_service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.concurrent.Executors;

@GrpcService
public class GrpcServer {
    @Bean
    private void runGrpcServer() throws IOException, InterruptedException {
        DomainEvents.subscribe(new StockPriceChangedEventListener());
        Executors.newFixedThreadPool(1).submit(new RandomStockPriceUpdatingTask());

        Server server = ServerBuilder
                .forPort(8080)
                .addService(new PriceService())
                .build();

        server.start();
        System.out.println("gRPC server started, listening on port:" + server.getPort());
        server.awaitTermination();
    }
}