package home.work.grpc_server.stock_prices;

import com.google.common.eventbus.Subscribe;

public class StockPriceChangedEventListener {
    //TODO DIVIDE A LOGIC UPDATING STOCK PRICES AND LISTENING EVENTS
    @Subscribe
    public void handleEvent(StockPriceChangedEvent event) {
        System.out.println(event.toString());
        StockPriceChangedSubject.INSTANCE.notify(event);
    }
}