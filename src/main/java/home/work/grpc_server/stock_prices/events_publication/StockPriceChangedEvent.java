package home.work.grpc_server.stock_prices.events_publication;

public record StockPriceChangedEvent(String symbol, double price) {}