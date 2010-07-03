package com.practicaljava.lesson32;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
public class TradeOrderService implements ITradeOrderService {
    public Double getSymbolPrice(String symbol) throws SymbolNotFoundException {
        if (symbol.equals("IBM"))
            return 129.53;

        if (symbol.equals("GOOG"))
            return 572.01;

        throw new SymbolNotFoundException(symbol);
    }
}
