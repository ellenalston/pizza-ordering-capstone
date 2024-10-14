<template>
    <div class="container">
      <h1>Order Confirmation</h1>
      <!-- If there is an order, then display it here -->
      <div v-if="order">
        <h2>Thank you for your order!</h2>
        <p><strong>Order ID:</strong> {{ order.id }}</p>
        <p><strong>Order Date:</strong> {{ formatDate(order.orderDate) }}</p>
        <p><strong>Order Type:</strong> {{ order.orderType }}</p>
        <p><strong>Status:</strong> {{ order.status }}</p>
        <p><strong>ASAP:</strong> {{ order.isASAP ? 'Yes' : 'No' }}</p>
        <p v-if="!order.isASAP"><strong>Pickup Time:</strong> {{ order.pickupTime }}</p>
  
        <h4>Pizzas Ordered:</h4>
        <div class="pizza-container">
          <div class="pizza-box" v-for="pizza in pizzas" :key="pizza.id">
            <p><strong> {{ pizza.specialtyName }} </strong></p>
            <p>Size: {{ pizza.size.name }}</p>
            <p>Crust: {{ pizza.crust.name }}</p>
            <p>Sauce: {{ pizza.sauce.name }}</p>
            <p>Toppings:
              <span>{{ pizza.toppings.map(topping => topping.name).join(', ') }}</span>
            </p>
            <p>Price: ${{ calculatePrice(pizza) }}</p>
          </div>
        </div>
      </div>
      <button @click="goHome">Go to Home</button>
      <h4>Total Price: ${{ this.$store.state.totalPrice }}</h4>
    </div>
  </template>
  

<script>
import PizzaService from '../services/PizzaService.js';

export default {
    data() {
        return {
            order: null,
            pizzas: [] // Array to store pizza details from cart
        };
    },
    methods: {

        formatDate(dateString) {
            const options = { year: 'numeric', month: 'long', day: 'numeric' }; 
            return new Date(dateString).toLocaleDateString('en-US', options);
        },

        calculatePrice(pizza) {
            let price = pizza.size.price + pizza.crust.price + pizza.sauce.price;

            pizza.toppings.forEach(topping => {
                price += topping.price;
            });

            return price;
        },

        async fetchPizzaDetails() {
            if (this.order && this.order.pizzas) {
                for (const pizza of this.order.pizzas) {
                    try {
                        const response = await PizzaService.getPizzaById(pizza.id); // Fetch pizza details
                        this.pizzas.push(response.data); // Assuming response.data contains pizza details
                    } catch (error) {
                        console.error(`Error fetching pizza with ID ${pizza.id}:`, error);
                    }
                }
            }
        },

        goHome() {
            this.$router.push('/');
        }
    },
    mounted() {
        this.order = this.$store.state.orderData;
            this.fetchPizzaDetails();
        }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background-image: url('/pizza_imgs/pizza-home.png');
  background-size: cover;
  background-position: center;
  font-family: 'Bree', serif;
  color: white;
  text-shadow: 
    -1px -1px 0 black,
    1px 1px 0 black,
    -1px 1px 0 black,
    1px 1px 0 black;
    text-align: center;
}

h1 {
  font-family: 'Pacifico', cursive;
  text-decoration: underline;
}

h2, h4, p {
  font-family: 'Bree Serif';
}

button {
  background-color: #cc4125;
  font-family: 'Pacifico', cursive;
  color: white;
  border: white;
  border: solid;
  padding: 10px 20px;
  cursor: pointer;
  margin-top: 20px;
}

button:hover {
  background-color: #45A049;
  text-decoration: underline;
}

.pizza-container {
  display: flex;
  flex-wrap: wrap; /* Allow wrapping of boxes */
  justify-content: space-evenly; /* Space boxes evenly */
  width: 100%; /* Make it full width */
  margin-top: 20px; /* Add space above the pizzas */
}

.pizza-box {
  background-color: rgba(255, 255, 255, 0.7); /* Translucent white */
  border-radius: 10px; /* Rounded corners */
  padding: 15px; /* Padding for better appearance */
  margin: 10px; /* Space around each box */
  flex: 0 1 calc(50% - 20px); /* Wider boxes */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Add shadow for depth */
  color: black; /* Dark text for better contrast */
  text-shadow: none;
}

.pizza-box p {
  margin: 0; /* Reset margins on paragraphs */
}

/* Optional: style the list of pizzas */
ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-bottom: 5px;
  font-size: 1rem;
}
</style>
