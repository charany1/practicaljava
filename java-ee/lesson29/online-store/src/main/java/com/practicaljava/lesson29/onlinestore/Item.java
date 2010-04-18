package com.practicaljava.lesson29.onlinestore;

import java.io.Serializable;

public class Item implements Serializable {
    private Long productCode;
    private String name;
    private String description;
    private Integer priceInPoints;

    public Item(Long productCode, String name, String description, Integer priceInPoints) {
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.priceInPoints = priceInPoints;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriceInPoints() {
        return priceInPoints;
    }

    public void setPriceInPoints(Integer priceInPoints) {
        this.priceInPoints = priceInPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Item item = (Item) o;

        return productCode.equals(item.productCode);
    }

    @Override
    public int hashCode() {
        return productCode.hashCode();
    }
}
