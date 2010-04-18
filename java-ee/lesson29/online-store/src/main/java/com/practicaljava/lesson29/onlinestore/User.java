package com.practicaljava.lesson29.onlinestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String id;
    private Integer rewardPoints = 0;

    private List<Item> cartItems = new ArrayList<Item>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public List<Item> getCartItems() {
        return cartItems;
    }

    public Integer getCartSize() {
        return cartItems.size();
    }
}
