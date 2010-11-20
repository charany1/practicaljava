package com.practicaljava.lesson34;

import java.util.HashMap;
import java.util.Map;

public class StockService {
    public static void addStock(Stock stock) {
        stocks.put(stock.getSymbol(), stock);
    }

    public static void removeStock(String symbol) {
        stocks.remove(symbol);
    }

    public static Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    private static Map<String, Stock> stocks = new HashMap<String, Stock>();

    static {
        generateStocks();
    }

    private static void generateStocks() {
        addStock(new Stock("IBM", 43.12, "USD", "US"));
        addStock(new Stock("APPL", 320.0, "USD", "US"));
     }
}
