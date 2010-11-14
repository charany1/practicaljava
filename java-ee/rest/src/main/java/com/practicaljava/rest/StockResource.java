package com.practicaljava.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/stock")
public class StockResource {
    
    @Produces({"application/xml", "application/json"})
    @Path("{symbol}")
    @GET
    public Stock getStock(@PathParam("symbol") String symbol) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Stock stock = StockService.getStock(symbol);

        if (stock == null) {
            return new Stock("NOT FOUND", 0.0, "--", "--");
        }

        return stock;
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void addStock(@FormParam("symbol") String symbol,
                         @FormParam("currency") String currency,
                         @FormParam("price") String price,
                         @FormParam("country") String country) {
        if (StockService.getStock(symbol) != null) {
            throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
        }

        double priceToUse;
        try {
            priceToUse = new Double(price);
        }
        catch (NumberFormatException e) {
            priceToUse = 0.0;
        }

        StockService.addStock(new Stock(symbol, priceToUse, currency, country));
    }
}
