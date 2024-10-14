package com.techelevator.model;

public class Discount {
    private int id;
    private String discountCode;
    private Double percentage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Discount(int id, String discountCode, Double percentage) {
        this.id = id;
        this.discountCode = discountCode;
        this.percentage = percentage;
    }
}
