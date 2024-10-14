package com.techelevator.controller;

import com.techelevator.dao.PizzaDao;
import com.techelevator.model.Customer;
import com.techelevator.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class OrderController {

    @Autowired
    private PizzaDao pizzaDao;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="create-customer", method = RequestMethod.POST)
    public Customer createCustomer(@RequestBody @Valid Customer newCustomer){
        return pizzaDao.createCustomer(newCustomer);
    }

    @RequestMapping(path="price/{pizzaId}", method = RequestMethod.GET)
    public BigDecimal getPrice(@PathVariable int pizzaId){
        return pizzaDao.calculatePrice(pizzaId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping (path="create-order", method = RequestMethod.POST)
    public Order createOrder(@RequestBody @Valid Order newOrder){
        return pizzaDao.createOrder(newOrder);
    }

    @RequestMapping(path="orders", method = RequestMethod.GET)
    public List<Order> getOrders(){
        return pizzaDao.getOrders();
    }

    @RequestMapping(path="order/{orderId}/complete", method = RequestMethod.PUT)
    public void completeOrder(@PathVariable int orderId){
        pizzaDao.completeOrder(orderId);
    }

    @RequestMapping(path="order/{orderId}/cancel", method = RequestMethod.PUT)
    public void cancelOrder(@PathVariable int orderId){
        pizzaDao.cancelOrder(orderId);
    }

    @RequestMapping(path="customer/{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable int customerId){
       return pizzaDao.getCustomerById(customerId);
    }


}

