package home.work.grpc_server.stock_prices.events_publication;

import home.work.grpc_server.StockPriceRequest;
import home.work.grpc_server.StockPriceResponse;
import home.work.grpc_server.StockPriceServiceGrpc;
import home.work.grpc_server.stock_prices.StockRepository;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

public class StockPriceService extends StockPriceServiceGrpc.StockPriceServiceImplBase {
    private final StockRepository repository = StockRepository.INSTANCE;
    private final StockPriceChangedSubject subject = StockPriceChangedSubject.INSTANCE;

    @Override
    public void getPrice(StockPriceRequest request, StreamObserver<StockPriceResponse> responseObserver) {
        repository.getStock(request.getSymbol())
                .ifPresentOrElse(
                        stock -> {
                            responseObserver
                                    .onNext(StockPriceResponse.newBuilder()
                                            .setSymbol(stock.getSymbol())
                                            .setPrice(stock.getPrice())
                                            .build());
                            subject.register(request.getSymbol(), responseObserver);
                            var serverCallStreamObserver = (
                                    (ServerCallStreamObserver<StockPriceResponse>) responseObserver
                            );
                            serverCallStreamObserver.setOnCancelHandler(
                                    () -> subject.unregister(request.getSymbol(), responseObserver)
                            );
                        }, () -> responseObserver.onError(new StatusException(Status.NOT_FOUND))
                );
    }
}