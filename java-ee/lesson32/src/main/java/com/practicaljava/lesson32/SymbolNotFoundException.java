package com.practicaljava.lesson32;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class SymbolNotFoundException extends Exception {
    public SymbolNotFoundException(String symbol) {
        super("Symbol '" + symbol + "' not found");
    }
}
