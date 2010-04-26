package com.practicaljava.lesson29.onlinestore;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Item> items = new ArrayList<Item>();

    private static Store instance = new Store();

    public static Store getInstance() {
        return instance;
    }

    private Store() {
        items.add(new Item(54L, "Practical Java", "Java Tutorial Book", 58));
        items.add(new Item(62L, "God of War", "PS3 action video game", 42));
        items.add(new Item(68L, "Silver fork", "Dinner desert special silver fork", 30));
        items.add(new Item(185L, "Winter Wheels", "BMW wheels for winter", 150));
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItem(String code) {
        Long productCode;

        try {
            productCode = Long.valueOf(code);
        }
        catch (NumberFormatException e) {
            productCode = null;
        }

        if (productCode == null) {
            return null;
        }

        return getItem(productCode);
    }

    public Item getItem(Long code) {
        for (Item item : items) {
            if (item.getProductCode().equals(code)) {
                return item;
            }
        }

        return null;
    }
}
