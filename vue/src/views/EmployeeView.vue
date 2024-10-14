<template >
  <div class="employee">
    <h1>Employee View</h1>
    <div class="button-container">
    <button @click="goToPendingOrders">View Pending Orders</button>
    <button v-on:click="goToViewSpecialtyPizzas">View Specialty Pizzas</button>
    <button v-on:click="goToSpecialtyPizzas">Create Specialty Pizzas</button>
    <button v-on:click="goToToppingPage">Change Availability of Topping Inventory</button>
  </div>
  </div>
</template>

<script>
export default {

  methods:{

    goToSpecialtyPizzas(){
      this.$router.push('/create-specialty-pizza');
    },
    goToToppingPage(){
      this.$router.push('/topping-inventory');
    },

    goToPendingOrders(){
      this.$router.push('/pending-orders');
    },

    goToViewSpecialtyPizzas(){
      this.$router.push('/view-specialty-pizzas');
    }
  }

}
</script>

<style scoped>
.employee {
  background-image: url('/employee-view.jpg'); /* Corrected path */
  background-size: cover;
  background-position: center;
  height: 100vh;
  display: flex; /* Use flexbox to center items */
  flex-direction: column; /* Stack items vertically */
  justify-content: center; /* Center items vertically */
  align-items: center; /* Center items horizontally */
  font-family: 'Pacifico', cursive;
  color: white;
}

h1 {
  margin: 0;
  padding: 20px;
  font-size: 3em;
  /* Removed height and flex properties */
}

.button-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%; /* Ensure container takes full width */
}

button {
  background-color: #cc4125;
  font-family: 'Pacifico', cursive;
  color: white;
  border: solid;
  border-color: white;
  font-size: 2em;
  padding: 10px;
  width: 100%; /* Makes buttons stretch to the container's width */
  max-width: 50%; /* Optional: Limit the maximum button width */
  text-align: center;
  margin: 10px 0;
}

button:hover {
  background-color: #45A049;
  text-decoration: underline;
}

</style>