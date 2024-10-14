<template>
  <div>
    <h1>Cart</h1>
    <div v-if="$store.state.cart.length === 0">
      <h2>Your cart is empty!</h2>
    </div>
    <div v-else>
      <div class="pizza-list"> <!-- Container to hold all pizzas -->
        <div v-for="pizza in $store.state.cart" :key="pizza.id" class="pizza-item">
          <!-- Loop through each pizza in the cart -->
          <h2>{{ pizza.specialtyName }}</h2>
          <p>Size: {{ pizza.size.name }}</p>
          <p>Crust: {{ pizza.crust.name }}</p>
          <p>Sauce: {{ pizza.sauce.name }}</p>
          <p>Toppings:
          <div v-for="topping in pizza.toppings" :key="topping.id">{{ topping.name }}</div>
          </p>

          <p>Price: ${{ calculatePrice(pizza) }}</p> <!-- Call method to calculate price  of each pizza-->
          <button @click="removeFromCart(pizza)">Remove from Cart</button>
        </div>
      </div>
      <h2>Total Price: ${{ calculateTotalPrice() }}</h2> <!-- Call method to calculate total price -->
    </div>
  </div>
</template>

<script>

export default {
  methods: {
    // Method to calculate the price of a pizza
    calculatePrice(pizza) {
      let price = pizza.size.price + pizza.crust.price + pizza.sauce.price;

      pizza.toppings.forEach(topping => {
        price += topping.price;
      });

      return price;
    },
    // Method to calculate the total price of all pizzas in the cart
    calculateTotalPrice() {
      let totalPrice = 0;
      // Loop through each pizza in the cart and calculate its price
      this.$store.state.cart.forEach(pizza => {
        totalPrice += this.calculatePrice(pizza); 
      });

      // Commit the mutation to store the calculated total price in Vuex
      this.$store.commit('SET_TOTAL_PRICE', totalPrice);

      return totalPrice;
    },
    // Method to remove a pizza from the cart
    removeFromCart(pizza) {
      this.$store.commit('REMOVE_FROM_CART', pizza);
    }
  },
  created() {
    console.log(this.$store.state.cart);
  }
}
</script>
<style scoped>
h1 {
  font-family: 'Pacifico', cursive;
  text-decoration: underline;
}

h2,p {
  font-family: 'Bree Serif';
  
}

h1{
  color: white;
  text-shadow: 
  -1px -1px 0 black,
  1px 1px 0 black,
  -1px 1px 0 black,
  1px 1px 0 black;
}

h2{color: white;
  text-shadow: 
  -1px -1px 0 black,
  1px 1px 0 black,
  -1px 1px 0 black,
  1px 1px 0 black;
}

button {
  background-color: #cc4125;
  font-family: 'Pacifico', cursive;
  color: white;
  border: white;
  border: solid;
}

button:hover {
  background-color: #45A049;
  text-decoration: underline;
}

.pizza-list {
  display: flex;
  /* Use flexbox to display pizzas in a row */
  flex-wrap: wrap;
  /* Allow wrapping if the row is too long for one line */
  gap: 20px;
  /* Add some space between the pizzas */
  justify-content: center;
  /* Center the pizzas within the container */
}

.pizza-item {
  flex: 0 1 30%;
  /* Each pizza will take up about 30% of the container width */
  background-color: rgba(69, 160, 73, 0.5);
  padding: 10px;
  border-radius: 8px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  color: white;
}

.pizza-item h2 {
  font-size: 1.5rem;
}

.pizza-item p {
  font-size: 1rem;
}
</style>
