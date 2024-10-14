<template>
  <div class="home">

    <div class="pizza-container" v-if="specialtyPizzas.length">
      <h1>Specialty Pizzas</h1>
      <div class="button-container">
        <button v-on:click="goToCustomPage">Compile Your Own</button>
        <button v-on:click="goToCheckout">Go to Cart [{{ $store.state.cart.length }}]</button>
      </div>

      <!-- Loop through each specialty pizza and display its name, description, and a button to add it to the cart -->
      <div v-for="pizza in specialtyPizzas" v-bind:key="pizza.id" class="specialtyPizzas">
        <div v-if="pizza.available">
          <h2>{{ pizza.specialtyName }} - ${{ calculatePrice(pizza) }}</h2>
          <p>{{ pizza.specialDesc }}</p>
          <button v-on:click="addSpecialtyPizzaToCart(pizza)">Add to Cart</button>
        </div>
      </div>
    </div>
    <!-- Button to go to the cart   -->
    
  </div>
</template>

<script>
// Import the PizzaService
import PizzaService from "../services/PizzaService.js";

export default {
  data() {
    return {
      // This is the array that will hold all specialty pizzas
      specialtyPizzas: [],
    }

  },
  created() {
    // Call the getSpecialtyPizzas method from the PizzaService
    PizzaService.getSpecialtyPizzas().then(
      (response) => {
        this.specialtyPizzas = response.data;
      }
    );
  },
  methods: {

    calculatePrice(pizza) {
      let price = pizza.size.price + pizza.crust.price + pizza.sauce.price;

      pizza.toppings.forEach(topping => {
        price += topping.price;
      });

      return price.toFixed(2);
    },
    // Method to navigate to the custom pizza page
    goToCustomPage() {
      this.$router.push('/create-pizza');
    },
    // Method to navigate to the checkout page
    goToCheckout() {
      this.$router.push('/customer-info');
    },
    // Method to add a specialty pizza to the cart
    addSpecialtyPizzaToCart(pizza) {
      this.$store.commit('ADD_TO_CART', pizza);

    }
  }
}
</script>

<style scoped>
html, body {
  height: 100%;
  margin: 0;
}

body {
  background-color: black; /* Fallback color */
  background-size: cover;
  background-position: center;
  background-attachment: fixed; /* Ensures the background image remains fixed */
}

.home {
  background-image: url('/pizza_imgs/pizza-menu.png'); /* Adjusted path */
  background-size: cover;
  background-position: center;
  background-attachment: fixed; /* Ensures the background image remains fixed */
  min-height: 100vh;
  height: 100%; 
  color: white;
  display: flex;
  flex-direction: column;
  align-items: flex-end; /* Changed to 'flex-end' for consistency */
  padding-right: 50px;
  padding-left: 50%; /* Consider adjusting this if it causes layout issues */
  font-family: 'Bree Serif';
}

.pizza-container {
  color: white;
  display: flex;
  flex-direction: column;
  align-items: flex-end; /* Changed to 'flex-end' for consistency */
  padding-right: 50px;
  padding-bottom: 100px;
}


p {
  font-size: small;
}

button {
  background-color: #cc4125;
  font-family: 'Pacifico', cursive;
  color: white;
  border: white;
  border: solid;
  padding: 10px 20px;
  margin: 10px;
}

button:hover {
  background-color: #45A049;
  text-decoration: underline;
}

.button-container {
  display: flex; /* Use flexbox to align buttons horizontally */
  gap: 10px; /* Optional: Adds space between buttons */
  margin-bottom: 20px; /* Space below the button container */
}
</style>
