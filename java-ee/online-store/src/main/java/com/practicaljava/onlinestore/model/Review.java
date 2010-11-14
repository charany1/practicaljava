package com.practicaljava.onlinestore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "REVIEWS")
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Item item;

    private String text;

    private Integer score;

    public Review() {
    }

    public Review(User user, Item item, Integer score, String text) {
        this.user = user;
        this.item = item;

        this.score = score;
        this.text = text;
    }

    public Integer getScore() {
        return score;
    }

    public String getText() {
        return text;
    }

    public Item getItem() {
        return item;
    }

    public User getUser() {
        return user;
    }
}
