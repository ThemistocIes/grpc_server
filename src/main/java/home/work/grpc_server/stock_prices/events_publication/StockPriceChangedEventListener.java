package home.work.grpc_server.stock_prices.events_publication;

import com.google.common.eventbus.Subscribe;

public class StockPriceChangedEventListener {
    @Subscribe
    public void handleEvent(StockPriceChangedEvent event) {
        System.out.println(event.toString());
        StockPriceChangedSubject.INSTANCE.notify(event);
    }
}