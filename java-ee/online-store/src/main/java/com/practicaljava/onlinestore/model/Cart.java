package com.practicaljava.onlinestore.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Item, Integer> items = new HashMap<Item, Integer>();

    private Integer totalPrice = 0;

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void addItem(Item item) {
        Integer itemCount = items.get(item);

        if (itemCount == null)
            itemCount = 0;

        items.put(item, itemCount + 1);

        totalPrice += item.getPriceInPoints();
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void clear() {
        items.clear();
        totalPrice = 0;
    }
}
