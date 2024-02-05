package home.work.grpc_server.stock_price_service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public enum StockRepository {
    INSTANCE;
    private final Map<String, Stock> stocks = new HashMap<>();

    StockRepository() {
        var random = ThreadLocalRandom.current();
        addStock(new Stock("GOOGL", random.nextInt(50, 200)));
        addStock(new Stock("AMZN", random.nextInt(50, 200)));
        addStock(new Stock("MSFT", random.nextInt(50, 200)));
        addStock(new Stock("AAPL", random.nextInt(50, 200)));
        addStock(new Stock("NFLX", random.nextInt(50, 200)));
    }

    public void addStock(Stock stock) {
        stocks.put(stock.getSymbol(), stock);
    }

    public Collection<Stock> getStocks() {
        return Collections.unmodifiableCollection(stocks.values());
    }

    public Optional<Stock> getStock(String symbol) {
        return Optional.ofNullable(stocks.get(symbol));
    }
}
