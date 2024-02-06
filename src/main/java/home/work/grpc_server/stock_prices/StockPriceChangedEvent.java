package home.work.grpc_server.stock_prices;

public record StockPriceChangedEvent(String symbol, double price) {}