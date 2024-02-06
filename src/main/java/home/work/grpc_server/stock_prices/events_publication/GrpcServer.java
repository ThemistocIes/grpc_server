package home.work.grpc_server.stock_prices.events_publication;

import home.work.grpc_server.stock_prices.update_simulation.RandomStockPriceUpdatingTask;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.concurrent.Executors;

@Slf4j
@GrpcService
public class GrpcServer {
    @Bean
    private void runGrpcServer() throws IOException, InterruptedException {
        DomainEvents.subscribe(new StockPriceChangedEventListener());
        Executors.newSingleThreadExecutor().submit(new RandomStockPriceUpdatingTask());

        Server server = ServerBuilder
                .forPort(8080)
                .addService(new PriceService())
                .build();

        server.start();
        log.info("gRPC server started, listening on port: {}", server.getPort());
        server.awaitTermination();
    }
}