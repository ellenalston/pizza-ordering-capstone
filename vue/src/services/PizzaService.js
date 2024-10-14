import axios from 'axios';

export default {

    // Get all specialty pizzas
    getSpecialtyPizzas() {
        return axios.get("/specialty-pizzas");
    },

    // Get all crusts
    getCrusts() {
        return axios.get("/crusts");
    },

    // Get all sizes
    getSizes() {
        return axios.get("/sizes");
    },
    // Get all sauces
    getSauces() {
        return axios.get("/sauces");
    },
    // Get all regular toppings
    getRegularToppings() {
        return axios.get("/regular-toppings");
    },
    // Get all premium toppings
    getPremiumToppings() {
        return axios.get("/premium-toppings");
    },
    // Get all pizzas
    getPizzaById(id) {
        return axios.get(`/pizza/${id}`);
    },
    // Create a new pizza
    createPizza(newPizza) {
        return axios.post("/create-pizza", newPizza);
    },

    toggleToppingInventory(toppingId) {
        return axios.put(`topping/${toppingId}/availability`);
    },

    togglePizzaAvailability(pizzaId) {
        return axios.put(`pizza/${pizzaId}/availability`);
    },

    getPizzasByOrderId(orderId){
        return axios.get(`pizzas/${orderId}`);
    }
}