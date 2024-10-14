package com.techelevator.model;

import java.util.List;

public class Pizza {
    private int id;
    private String specialtyName;
    private int sizeId;
    private int crustId;
    private int sauceId;
    private boolean isAvailable;
    private int discountId;
    private String specialDesc;
    private List<Topping> toppings;
    private Crust crust;
    private Sauce sauce;
    private Size size;

    public Pizza() {}

    public Pizza(int id, String specialtyName, int sizeId, int crustId, int sauceId, boolean isAvailable, int discountId, String specialDesc, List<Topping> toppings, Crust crust, Sauce sauce, Size size) {
        this.id = id;
        this.specialtyName = specialtyName;
        this.sizeId = sizeId;
        this.crustId = crustId;
        this.sauceId = sauceId;
        this.isAvailable = isAvailable;
        this.discountId = discountId;
        this.specialDesc = specialDesc;
        this.toppings = toppings;
        this.crust = crust;
        this.sauce = sauce;
        this.size = size;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Crust getCrust() {
        return crust;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    public void setTopping(Topping topping){
        toppings.add(topping);
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public String getSpecialDesc() {
        return specialDesc;
    }

    public void setSpecialDesc(String specialDesc) {
        this.specialDesc = specialDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getCrustId() {
        return crustId;
    }

    public void setCrustId(int crustId) {
        this.crustId = crustId;
    }

    public int getSauceId() {
        return sauceId;
    }

    public void setSauceId(int sauceId) {
        this.sauceId = sauceId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }
}
