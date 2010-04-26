package com.practicaljava.lesson29.onlinestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String id = "guest";
    private Integer rewardPoints = 0;

    private RoleType role = RoleType.ANONYMOUS;

    private List<Item> cartItems = new ArrayList<Item>();

    public User() {
    }

    public User(RoleType role) {
        this.role = role;
    }

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

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
