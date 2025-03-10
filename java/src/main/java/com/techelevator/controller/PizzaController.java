package com.techelevator.controller;

import com.techelevator.dao.PizzaDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class PizzaController {

    @Autowired
    private PizzaDao pizzaDao;

    @RequestMapping(path="pizza/{id}", method = RequestMethod.GET)
    public Pizza getPizzaById(@PathVariable int id){
        return pizzaDao.getPizzaById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="create-pizza", method = RequestMethod.POST)
    public Pizza createPizza(@RequestBody @Valid Pizza newPizza){
        return pizzaDao.createPizza(newPizza);
    }

    @RequestMapping(path="/specialty-pizzas", method = RequestMethod.GET)
    public List<Pizza> specialtyPizzas(){
        return pizzaDao.getSpecialtyPizzas();
    }

    @RequestMapping(path="/crusts", method = RequestMethod.GET)
    public List<Crust> crusts() {
        return pizzaDao.getCrusts();
    }

    @RequestMapping(path="/sizes", method = RequestMethod.GET)
    public List<Size> sizes() {
        return pizzaDao.getSizes();
    }

    @RequestMapping(path="/sauces", method = RequestMethod.GET)
    public List<Sauce> sauces() {
        return pizzaDao.getSauces();
    }

    @RequestMapping(path="/regular-toppings", method = RequestMethod.GET)
    public List<Topping> regularToppings() {
        return pizzaDao.getRegularToppings();
    }

    @RequestMapping(path="/premium-toppings", method = RequestMethod.GET)
    public List<Topping> premiumToppings() {
        return pizzaDao.getPremiumToppings();
    }

    @RequestMapping(path="topping/{toppingId}/availability", method = RequestMethod.PUT)
    public void toggleToppingAvailability(@PathVariable String toppingId){
        pizzaDao.toggleToppingAvailability(Integer.parseInt(toppingId));

    }

    @RequestMapping(path="pizza/{pizzaId}/availability", method = RequestMethod.PUT)
    public void togglePizzaAvailability(@PathVariable String pizzaId){
        pizzaDao.togglePizzaAvailability(Integer.parseInt(pizzaId));

    }

    @RequestMapping(path="pizzas/{orderId}", method = RequestMethod.GET)
    public List<Pizza> getPizzasByOrderId(@PathVariable int orderId){
        return pizzaDao.getPizzasByOrderId(orderId);
    }

}
