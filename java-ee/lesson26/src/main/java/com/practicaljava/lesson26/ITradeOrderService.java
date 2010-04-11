package com.practicaljava.lesson26;

import javax.ejb.Remote;

@Remote
public interface ITradeOrderService {
    Double getSymbolPrice(String symbol) throws SymbolNotFoundException;
}
