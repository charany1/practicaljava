package com.practicaljava.lesson29.onlinestore.model;

import com.practicaljava.lesson29.onlinestore.RoleType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "USERS")
@NamedQueries({
    @NamedQuery(name = "User.byId", query = "select u from User u where u.id = :id")
})
public class User implements Serializable {
    @Id
    private String id = "guest";
    private Integer rewardPoints = 0;

    @Enumerated(value = EnumType.STRING)
    private RoleType role = RoleType.ANONYMOUS;

    @Transient // TODO probably it is a good idea to save cart
    private Map<Item, Integer> cartItems = new HashMap<Item, Integer>();

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

    public Map<Item, Integer> getCartItems() {
        return cartItems;
    }

    public void addItem(Item item) {
        Integer itemCount = cartItems.get(item);

        if (itemCount == null)
            itemCount = 0;

        cartItems.put(item, itemCount + 1);
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
