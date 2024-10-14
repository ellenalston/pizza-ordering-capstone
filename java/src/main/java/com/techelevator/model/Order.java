package com.techelevator.model;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

public class Order {
    private int id;
    private Date orderDate;
    private String orderType;
    private String status;
    private boolean isASAP;
    private LocalTime pickupTime;
    private int customerId;
    private String creditCardNumber;
    private String expirationDate;
    private String cvv;
    private List<Pizza> pizzas;

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

public Date getOrderDate() {
       return orderDate;
    }

   public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isASAP() {
        return isASAP;
    }

    public void setIsASAP(boolean ASAP) {
        isASAP = ASAP;
    }

   public LocalTime getPickupTime() {
        return pickupTime;
    }

   public void setPickupTime(LocalTime pickupTime) {
        this.pickupTime = pickupTime;
   }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Order() {
        this.id = id;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.status = status;
        this.isASAP = isASAP;
        this.pickupTime = pickupTime;
        this.customerId = customerId;
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }
}

