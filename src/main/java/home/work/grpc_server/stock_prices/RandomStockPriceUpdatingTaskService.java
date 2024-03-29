package home.work.grpc_server.stock_prices;

import java.util.concurrent.ThreadLocalRandom;

public class RandomStockPriceUpdatingTaskService implements Runnable {
    private final StockRepository repository = StockRepository.INSTANCE;

    @Override
    public void run() {
        while (true) {
            updateRandomStock();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void updateRandomStock() {
        var random = ThreadLocalRandom.current();
        var stocks = repository.getStocks();
        var randomStock = stocks.stream()
                .skip(random.nextInt(stocks.size()))
                .findFirst()
                .orElseThrow();
        var newPrice = randomStock.getPrice() + random.nextDouble(-5.0, 5.0);
        randomStock.updatePrice(newPrice);
    }
}
