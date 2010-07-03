package com.practicaljava.lesson32;

import javax.ejb.Remote;

@Remote
public interface ITradeOrderService {
    Double getSymbolPrice(String symbol) throws SymbolNotFoundException;
}
