package home.work.grpc_server.stock_prices.events_publication;

import home.work.grpc_server.stock_prices.RandomStockPriceUpdatingTaskService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class GrpcServer {
    @Bean
    private void runGrpcServer() throws IOException, InterruptedException {
        DomainEvents.subscribe(new StockPriceChangedEventListener());
        Executors.newSingleThreadExecutor().submit(new RandomStockPriceUpdatingTaskService());
        Server server = ServerBuilder
                .forPort(8050)
                .addService(new StockPriceService())
                .build();

        server.start();
        log.info("gRPC server started, listening on port: {}", server.getPort());
        server.awaitTermination();
    }
}