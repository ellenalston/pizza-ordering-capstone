package com.techelevator.model;

import java.math.BigDecimal;

public class Topping {
    private int id;
    private String name;
    private String type;
    private BigDecimal price;
    private boolean isAvailable;

    public Topping() {}

    public Topping(int id, String name, String type, BigDecimal price, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
