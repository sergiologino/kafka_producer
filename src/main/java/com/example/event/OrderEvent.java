package com.example.event;

import java.io.Serializable;

public class OrderEvent implements Serializable {
    private String product;
    private Integer quantity;

    // Constructors, Getters and Setters
    public OrderEvent() {}

    public OrderEvent(String product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
