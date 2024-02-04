package home.work.grpc_server;

import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

public class PriceService extends PriceServiceGrpc.PriceServiceImplBase {

    /*private final PriceRepository repository = PriceRepository.INSTANCE;
    private final PriceChangedSubject subject = PriceChangedSubject.INSTANCE;

    @Override
    public void getPrice(PriceRequest request, StreamObserver<PriceResponse> responseObserver) {
        repository.getPrice(request.getSymbol())
                .ifPresentOrElse(
                        Price -> {
                            responseObserver.onNext(PriceResponse.newBuilder().setSymbol(Price.getSymbol()).setPrice(Price.getPrice()).build());
                            subject.register(request.getSymbol(), responseObserver);
                            var serverCallStreamObserver = ((ServerCallStreamObserver<PriceResponse>) responseObserver);
                            serverCallStreamObserver.setOnCancelHandler(() -> subject.unregister(request.getSymbol(), responseObserver));
                        },
                        () -> responseObserver.onError(new StatusException(Status.NOT_FOUND))
                );
    }*/
}
