package home.work.grpc_server.stock_prices.events_publication;

import home.work.grpc_server.stock_prices.RandomStockPriceUpdatingTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Slf4j
@Service
public class GrpcServer {
    @Value("${grpc.server.port}")
    private String grpcServerPort;

    @Bean
    private void runGrpcServer() {
        DomainEvents.subscribe(new StockPriceChangedEventListener());
        Executors.newSingleThreadExecutor().submit(new RandomStockPriceUpdatingTaskService());
        log.info("gRPC server started, listening on port: {}", grpcServerPort);
    }
}