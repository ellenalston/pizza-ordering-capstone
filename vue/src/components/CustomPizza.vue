<template>
  <div class="parent-container">
    <h1>Compile Your Own Custom Pizza</h1>

    <div class="button-container">
      <button class="go-to-cart" v-on:click="goToCheckout">
        Go to Cart [{{ $store.state.cart.length }}]
      </button>
    </div>
    
    <div class="container">
      <div>
        <h2>Choose Your Size</h2>
        <div v-for="size in sizes" :key="size.id">
          <input type="radio" :id="size.id" :value="size" v-model="newPizza.size" />
          <label :for="size.id">{{ size.name }} ${{ size.price }}</label>
        </div>
      </div>
      <div>
        <h2>Choose Your Crust</h2>
        <div v-for="crust in crusts" :key="crust.id">
          <input type="radio" :id="crust.id" :value="crust" v-model="newPizza.crust" />
          <label :for="crust.id">{{ crust.name }} ${{ crust.price }}</label>
        </div>
      </div>
      <div>
        <h2>Choose Your Sauce</h2>
        <div v-for="sauce in sauces" :key="sauce.id">
          <input type="radio" :id="sauce.id" :value="sauce" v-model="newPizza.sauce" />
          <label :for="sauce.id">{{ sauce.name }} ${{ sauce.price }}</label>
        </div>
      </div>
      <div>
        <h2>Choose Your Regular Toppings</h2>
        <div v-for="regularTopping in regularToppings" :key="regularTopping.id">
          <div v-if="regularTopping.available">
            <input type="checkbox" :id="regularTopping.id" :value="regularTopping" v-model="newPizza.toppings" />
            <label :for="regularTopping.id">{{ regularTopping.name }} ${{ regularTopping.price }}</label>
          </div>
        </div>
      </div>
      <div>
        <h2>Choose Your Premium Toppings</h2>
        <div v-for="premiumTopping in premiumToppings" :key="premiumTopping.id">
          <div v-if="premiumTopping.available">
            <input type="checkbox" :id="premiumTopping.id" :value="premiumTopping" v-model="newPizza.toppings" />
            <label :for="premiumTopping.id">{{ premiumTopping.name }} ${{ premiumTopping.price }}</label>
          </div>
        </div>
      </div>
    </div>
    
    <div class="button-container">
      <button v-on:click="createPizza" v-bind:disabled="!canOrderPizza">Create Pizza</button>
    </div>
  </div>
</template>


<script>

import PizzaService from '../services/PizzaService.js';

export default {
  data() {
    return {
      // This is the object that will be sent to the server to create a new pizza
      newPizza: {
        specialtyName: 'Custom Pizza',
        size: null,
        crust: null,
        sauce: null,
        discountId: 1,
        specialDesc: '',
        toppings: []
      },
      //This is the list of crusts that will be displayed in the form
      crusts: [],
      //This is the list of sauces that will be displayed in the form
      sauces: [],
      //This is the list of sizes that will be displayed in the form
      sizes: [],
      //This is the list of regular toppings that will be displayed in the form
      regularToppings: [],
      //This is the list of premium toppings that will be displayed in the form
      premiumToppings: []
    }

  },

  methods: {
    createPizza() {

      // Call the createPizza method from the PizzaService
      PizzaService.createPizza(this.newPizza).then(
        (response) => {
          if (response.status === 201) {
            alert("Pizza Added!");
            // Commit the mutation to add the new pizza to the cart in Vuex $store 
            this.$store.commit('ADD_TO_CART', response.data);
            // Reset the newPizza object after the form is submitted
            this.newPizza = {
              specialtyName: 'Custom Pizza',
              size: null,
              crust: null,
              sauce: null,
              discountId: 1,
              specialDesc: '',
              toppings: []
            };
            // Redirect the user to the customer info page after the form is submitted
            this.$router.push('/customer-info');

          }
        }
      ).catch((error) => {
        console.error("Error creating pizza:", error);
        alert("Failed to create pizza. Please try again.");
      });
    },
    goToCheckout() {
      this.$router.push('/customer-info');
    },
  },

  computed: {

    canOrderPizza() {
      return this.newPizza.size && this.newPizza.crust && this.newPizza.sauce;
    }

  },

  created() {
    // Call the getCrusts method from the PizzaService
    PizzaService.getCrusts().then(
      (response) => {
        this.crusts = response.data;
      }
    );
    // Call the getSauces method from the PizzaService
    PizzaService.getSauces().then(
      (response) => {
        this.sauces = response.data;
      }
    );
    // Call the getSizes method from the PizzaService
    PizzaService.getSizes().then(
      (response) => {
        this.sizes = response.data;
      }
    );
    // Call the getRegularToppings method from the PizzaService
    PizzaService.getRegularToppings().then(
      (response) => {
        this.regularToppings = response.data;
      }
    );
    // Call the getPremiumToppings method from the PizzaService
    PizzaService.getPremiumToppings().then(
      (response) => {
        this.premiumToppings = response.data;
      }
    );
  }
}
</script>

<style scoped>
.parent-container {
  background-image: url('/pizza_imgs/custom-pizza.jpg');
  background-size: cover;
  background-position: center;
  background-color: black;
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: -50px;
  color: white;
  font-family: 'Bree Serif', serif;
}

.container {
  display: flex;
  justify-content: space-around; /* Use space-around for even spacing */
  flex-wrap: wrap; /* Allow wrapping for smaller screens */
  width: 100%; /* Ensures the container takes full width */
}

.container > div {
  background-color: rgba(69, 160, 73, 0.5);
  border-radius: 10px; /* Rounded corners */
  padding: 20px; /* Space inside the box */
  margin: 10px; /* Space around the boxes */
  flex: 1 1 250px; /* Makes boxes equal size */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3); /* Optional shadow for depth */
  min-width: 250px; /* Ensures a minimum width for boxes */
  max-width: 300px; /* Limits maximum width for boxes */
  height: 300px; /* Increase height for boxes to accommodate all toppings */
  display: flex; /* Enables flex properties */
  flex-direction: column; /* Stack content vertically */
  justify-content: space-between; /* Space out elements */
}

h1 {
  align-self: center;
}

h2 {
  font-family: 'Bree Serif';
  font-size: 1.2rem; /* Font size for section headings */
}

.button-container {
  display: flex;
  justify-content: center; /* Center the button container */
  width: 100%; /* Ensures it spans the full width */
  margin-top: 20px; /* Add spacing if needed */
}

button {
  background-color: #cc4125;
  font-family: 'Pacifico', cursive;
  color: white;
  border: none; /* No border for the button */
  padding: 10px 15px; /* Adjust padding for normal size */
  cursor: pointer;
}

button:disabled {
  background-color: grey;
  cursor: not-allowed;
}


button:hover {
  background-color: #45A049;
  text-decoration: underline;
}

label {
  font-size: 16px; /* Adjust font size for labels */
}
</style>


