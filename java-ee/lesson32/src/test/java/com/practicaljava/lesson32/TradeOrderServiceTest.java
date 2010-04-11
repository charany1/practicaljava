package com.practicaljava.lesson32;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class TradeOrderServiceTest {

    private static ITradeOrderService tradeOrderService;
    
    @BeforeClass
    public static void init() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty("org.omg.CORBA.ORBInitialPort", "3720");
        
        InitialContext initialContext = new InitialContext(properties);

        tradeOrderService = (ITradeOrderService) initialContext.lookup("com.practicaljava.lesson32.ITradeOrderService");
    }
    
    @Test
    public void getSymbolPrice() throws Exception {
        Double ibmPrice = tradeOrderService.getSymbolPrice("IBM");
        Assert.assertEquals(129.53, ibmPrice, 0.01);

        Double googlePrice = tradeOrderService.getSymbolPrice("GOOG");
        Assert.assertEquals(572.01, googlePrice, 0.01);
    }

    @Test(expected = SymbolNotFoundException.class)
    public void symbolNotFound() throws SymbolNotFoundException {
        tradeOrderService.getSymbolPrice("UNKNOWN");
    }
}
