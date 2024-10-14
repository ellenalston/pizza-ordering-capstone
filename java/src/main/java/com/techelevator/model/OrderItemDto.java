package com.techelevator.model;

import java.math.BigDecimal;

public class OrderItemDto {
    private int orderId;
    private int pizzaId;
    private int quantity;
    private BigDecimal price;

    public OrderItemDto(int orderId, int pizzaId, int quantity, BigDecimal price) {
        this.orderId = orderId;
        this.pizzaId = pizzaId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
