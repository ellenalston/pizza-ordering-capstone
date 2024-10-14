<template>
  <div class="container">
    <pizza-cart />
    <order-info />
  </div>
</template>

<script>
import PizzaCart from '../components/PizzaCart.vue';
import OrderInfo from '../components/OrderInfo.vue';

export default {
  components: {
    PizzaCart,
    OrderInfo
  }
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: space-evenly;
  /* Space them out evenly */
  align-items: flex-start;
  /* Align the components at the top */
  width: 100%;
  height: 100vh; /* Make container fill the full height of the viewport */
  margin: 0 auto;
  padding: 20px;
  background-image: url('/pizza_imgs/custom-pizza.jpg');
  background-size: cover;
  background-position: center;
}
.container{
  background-image: url('/pizza_imgs/custom-pizza.jpg');
  background-size: cover;
  background-position: center;
}

pizza-cart,
order-info {
  flex: 1;
  /* Allow both components to take equal space */
  max-width: 45%;
  /* Limit each component to take 45% of the container */
}

pizza-cart {
  display: flex;
  justify-content: center;
  /* Center the cart within its section */
}

order-info {
  display: flex;
  justify-content: center;
  /* Center the customer info within its section */
}
</style>