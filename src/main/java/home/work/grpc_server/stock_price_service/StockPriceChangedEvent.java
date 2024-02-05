package home.work.grpc_server.stock_price_service;

public record StockPriceChangedEvent(String symbol, double price) {}