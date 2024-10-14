package com.techelevator.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.model.*;

public interface PizzaDao {

    public void togglePizzaAvailability(int pizzaId);
    public List<Pizza> getPizzasByOrderId(int orderId);
    public void toggleToppingAvailability(int toppingId);
    public List<Crust> getCrusts();
    public List<Size> getSizes();
    public List<Sauce> getSauces();
    public List<Topping> getRegularToppings();
    public List<Topping> getPremiumToppings();
    public List<Pizza> getSpecialtyPizzas();
    public Pizza getPizzaById(int pizzaId);
    public List<Topping> getToppingsByPizzaId(int pizzaId);
    public Pizza createPizza(Pizza newPizza);
    public void addToppings(int pizzaId, List<Topping> toppings);
    public Crust getCrustByCrustId(int crustId);
    public Sauce getSauceBySauceId(int sauceId);
    public Size getSizeBySizeId(int sizeId);
    public BigDecimal calculatePrice(int pizzaId);
    public Customer createCustomer(Customer newCustomer);
    public Customer getCustomerById(int customerId);
    public Order createOrder(Order newOrder);
    public List<Order> getOrders();
    public void completeOrder(int orderId);
    public void cancelOrder(int orderId);





}




