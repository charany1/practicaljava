package com.practicaljava.lesson30.jms;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Random;

@Singleton
@Startup
public class PriceProvider {

    @EJB
    private MessageService messageService;

    @Schedule(second = "*/5", minute="*", hour="*")
    public void producePrice() {
        String price = Double.toString(generator.nextDouble() * 100.0);
        String symbol = symbols[generator.nextInt(symbols.length)];

        messageService.sendMessage(symbol + " " + price);
    }

    private static final Random generator = new Random();

    private static final String[] symbols = {"AAPL", "MSFT", "YHOO", "AMZN", "MOT"};
}
