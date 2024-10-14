<template>
  <div class="container">
    <pizza-cart />
    <customer-info />

  </div>
</template>

<script>
import PizzaCart from '../components/PizzaCart.vue';
import CustomerInfo from '../components/CustomerInfo.vue';

export default {
  components: {
    PizzaCart,
    CustomerInfo,
  }
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: space-evenly; /* Distribute space between components */
  align-items: flex-start; /* Align the components at the top */
  margin: 0 auto; /* Center the container on the page */
  padding: 20px;
  background-image: url('/pizza_imgs/custom-pizza.jpg');
  background-size: cover;
  background-position: center;
}

pizza-cart,
customer-info {
  flex: 1; /* Allow both components to take equal space */
  max-width: 45%; /* Limit each component to take 45% of the container */
  margin: 0 20px; /* Add horizontal space between components */
  padding: 20px; /* Add padding inside each component for better spacing */
  box-sizing: border-box; /* Ensure padding is included in width calculations */
}

pizza-cart {
  display: flex;
  justify-content: center; /* Center the cart within its section */
}

customer-info {
  display: flex;
  justify-content: center; /* Center the customer info within its section */
}
</style>


