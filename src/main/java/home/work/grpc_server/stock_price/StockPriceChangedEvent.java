package home.work.grpc_server.stock_price;

public record StockPriceChangedEvent(String symbol, double price) {}