<template>
  <div class="parent-container">
    <button v-on:click="goToEmployeeView" class="action-button" id="employee-view">Go to Employee View</button>
    <h1>Create Specialty Pizza</h1>
    <div class="text-container">
          <h2>Name Your Pizza</h2>
          <input type="text" v-model="newPizza.specialtyName" placeholder="Enter a name for your pizza" />
          <h2>Describe Your Pizza</h2>
          <input type="text" v-model="newPizza.specialDesc" placeholder="Enter a description for your pizza" />
        </div>
    <div class="container">
      <div>

        <!-- This is the form that will be used to create a new pizza -->
        <h2>Choose Your Size</h2>
        <!-- This is the list of sizes that will be displayed in the form -->
        <div v-for="size in sizes" :key="size.id">
          <input type="radio" :id="size" :value="size" v-model="newPizza.size" />
          <label :for="size">{{ size.name }} ${{ size.price }}</label>
        </div>
      </div>
      <div>
        <h2>Choose Your Crust</h2>
        <!-- This is the list of crusts that will be displayed in the form -->
        <div v-for="crust in crusts" :key="crust.id">
          <input type="radio" :id="crust.id" :value="crust" v-model="newPizza.crust" />
          <label :for="crust">{{ crust.name }} ${{ crust.price }}</label>
        </div>
      </div>
      <div>
        <!-- This is the list of sauces that will be displayed in the form -->
        <h2>Choose Your Sauce</h2>
        <div v-for="sauce in sauces" :key="sauce.id">
          <input type="radio" :id="sauce" :value="sauce" v-model="newPizza.sauce" />
          <label :for="sauce">{{ sauce.name }} ${{ sauce.price }}</label>
        </div>
      </div>
      <div>
        <!-- This is the list of regular toppings that will be displayed in the form -->
        <h2>Choose Your Regular Toppings</h2>
        <div v-for="regularTopping in regularToppings" :key="regularTopping.id">
          <div v-if="regularTopping.available">
            <input type="checkbox" :id="regularTopping" :value="regularTopping" v-model="newPizza.toppings" />
            <label :for="regularTopping">{{ regularTopping.name }} ${{ regularTopping.price }}</label>
          </div>
        </div>
      </div>
      <div>
        <!-- This is the list of premium toppings that will be displayed in the form -->
        <h2>Choose Your Premium Toppings</h2>
        <div v-for="premiumTopping in premiumToppings" :key="premiumTopping.id">
          <div v-if="premiumTopping.available">
            <input type="checkbox" :id="premiumTopping" :value="premiumTopping" v-model="newPizza.toppings" />
            <label :for="premiumTopping">{{ premiumTopping.name }} ${{ premiumTopping.price }}</label>
          </div>
        </div>
      </div>
      
    </div>
    <!-- This is the button that will submit the form and create a new pizza -->
    <div class="button-container">
        <button v-on:click="createPizza" class="action-button">Create Pizza</button>
      </div>

  </div>
</template>

<script>
import PizzaService from '../services/PizzaService';
export default {
  data() {
    return {
      // This is the object that will be sent to the server to create a new pizza
      newPizza: {
        specialtyName: '',
        size: null,
        crust: null,
        sauce: null,
        discountId: 2,
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
  },

  methods: {

    goToEmployeeView() {
            this.$router.push('/employee-view');
        },
    createPizza() {
      // Check if the user has selected a size, crust, and sauce
      if (this.newPizza.size === null || this.newPizza.crust === null || this.newPizza.sauce === null) {
        alert("Please make sure you've selected a size, crust, and sauce.");
        return;
      }
      // Call the createPizza method from the PizzaService
      PizzaService.createPizza(this.newPizza).then(
        (response) => {
          if (response.status === 201) {
            alert("Pizza Added!");
            this.newPizza = {
              specialtyName: '',
              size: null,
              crust: null,
              sauce: null,
              discountId: 2,
              specialDesc: '',
              toppings: []
            };
          }
        }).catch((error) => {
          console.error("Error creating pizza:", error);
          alert("Failed to create pizza. Please try again.");
        });
    },
  }
}
</script>

<style scoped>



.parent-container {
  font-family: 'Bree Serif', serif;
  text-align: center;
  background-image: url('/pizza_imgs/create-specialty-pizza-image.jpg');
    background-size: cover;
    background-position: center;
    background-color: black;
    height: 100%;
    color: white;
    /* Text color */
    text-shadow:
        -1px -1px 0 black,
        /* Top-left shadow */
        1px -1px 0 black,
        /* Top-right shadow */
        -1px 1px 0 black,
        /* Bottom-left shadow */
        1px 1px 0 black;
    /* Bottom-right shadow */
}

.container > div {
  background-color: rgba(69, 160, 73, 0.5);
  border-radius: 10px; /* Rounded corners */
  padding: 20px; /* Space inside the box */
  margin: 10px; /* Space around the boxes */
  flex: 1 1 250px; /* Makes boxes equal size */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3); /* Optional shadow for depth */
  height: 100%; /* Increase height for boxes to accommodate all toppings */
  display: flex; /* Enables flex properties */
  flex-direction: column; /* Stack content vertically */
  justify-content: space-between; /* Space out elements */
}

h1, h2{
  font-family: 'Pacifico', cursive;
}

.text-container {
  margin-bottom: 20px;
  display:flex;
  flex-direction: column;
  align-items: center;
}
.container {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

input[type="text"], 
 
textarea {
    width: 100%; /* Full width of the container */
    height: 50px; /* Increase height */
    padding: 10px; /* Add padding for better spacing */
    font-size: 16px; /* Increase font size */
    border: 2px solid #ccc; /* Border style */
    border-radius: 5px; /* Rounded corners */
    box-sizing: border-box; /* Include padding and border in element's total width and height */
    width: 50%;
}

button {
    background-color: #cc4125; /* Match button color */
    font-family: 'Pacifico', cursive; /* Matching font style */
    color: white; /* Text color */
    border: solid; /* Border style */
    border-color: white; /* Border color */
    padding: 10px; /* Padding inside button */
    width: 100%; /* Stretch to full width */
    max-width: 50%; /* Limit button width */
    text-align: center; /* Center text in button */
    margin: 10px 0; /* Space around buttons */
}

.action-button:hover {
    background-color: #45A049;
  text-decoration: underline;
}

#employee-view {
    margin: 20px 0;
    max-width:10%;
    margin-left: 90%;
}

</style>