<template>
  <div class="form-container">
    <h2>Order Form</h2>
    <form @submit.prevent="submitForm">
      <!-- Order Date -->
      <div>
        <label for="orderDate">Order Date</label>
        <input type="datetime-local" id="orderDate" v-model="order.orderDate" required />
      </div>


      <!-- Order Type -->
      <div>
        <label for="orderType">Order Type</label>
        <select id="orderType" v-model="order.orderType" required>
          <option value="Pickup">Pickup - Pittsburgh</option>
          <option value="Pickup">Pickup - Cranberry</option>
          <option value="Pickup">Pickup - Squirrel Hill</option>
          <option value="Delivery">Delivery</option>
        </select>
      </div>

      <!-- Is ASAP -->
      <div class="inline">
        <input type="checkbox" id="isASAP" v-model="order.isASAP" />
        <label for="isASAP">ASAP?</label>
      </div>


      <!-- Pickup Time (only show if isASAP is false) -->
      <div v-if="!order.isASAP">
        <label for="pickupTime">Pickup Time</label>
        <input type="time" id="pickupTime" v-model="order.pickupTime" required />
      </div>


      <!-- Credit Card Number -->
      <div>
        <label for="creditCardNumber">Credit Card Number</label>
        <input type="text" id="creditCardNumber" v-model="order.creditCardNumber" required minlength="16"
          maxlength="16" />
      </div>

      <!-- Expiration Date -->
      <div>
        <label for="expirationDate">Expiration Date (MMYY)</label>
        <input type="text" id="expirationDate" v-model="order.expirationDate" required pattern="\d{4}" />
      </div>

      <!-- CVV -->
      <div>
        <label for="cvv">CVV</label>
        <input type="text" id="cvv" v-model="order.cvv" required minlength="3" maxlength="4" />
      </div>

      <!-- Submit Button -->
      <button type="submit">Submit Order</button>
    </form>
  </div>
</template>

<script>
// This is the import statement for the CustomerService
import CustomerService from '../services/CustomerService.js';

export default {
  data() {
    return {
      // This is the object that will be sent to the server
      order: {
        orderDate: "", // This will be set to the current date and time
        orderType: "", // This will be set by the user
        status: "Pending", // This is the default status
        isASAP: "", // This will be set by the user
        pickupTime: "", // This will be set by the user
        customerId: "", // This will be set by the server
        creditCardNumber: "", // This will be set by the user
        expirationDate: "", // This will be set by the user
        cvv: "" // This will be set by the user
      }
    };
  },

  computed: {

    pizzaIds() {
      return this.$store.state.cart.map(pizza => pizza.id);
    }
  },
  methods: {
    submitForm() {
      const orderData = {
        orderDate: this.order.orderDate, // Order date is already in the correct format
        orderType: this.order.orderType, // Order type is already set
        status: this.order.status, // Status is already set
        isASAP: this.order.isASAP, // ASAP status is already set
        pickupTime: this.order.pickupTime, // Pickup time is already set
        customerId: this.$store.state.customerId, // Customer ID is retrieved from the store
        creditCardNumber: this.order.creditCardNumber, // Credit card number is already set
        expirationDate: this.order.expirationDate, // Expiration date is already set
        cvv: this.order.cvv, // CVV is already set
        pizzas: this.$store.state.cart.map(pizza => ({ id: pizza.id })) // Map the cart to get the pizza IDs

      }

      console.log("Order Data being submitted:", orderData); // Log order data before sending

      // Call the createOrder method from the CustomerService
      CustomerService.createOrder(orderData)
        .then(response => {
          if (response.status === 201) {
            alert("Order Submitted!");
            console.log(response.data);
            // This is the order ID that will be used to create the order and gets pushed to the store

            // This is the order that will get reset after the form is submitted
            this.order = {
              orderDate: this.setCurrentDateTime,
              orderType: "",
              status: "Pending",
              isASAP: "",
              pickupTime: null,
              customerId: "",
              creditCardNumber: "",
              expirationDate: "",
              cvv: ""
            };
            orderData.id = response.data.id;
            this.$store.commit('CLEAR_CART'); // Clear the cart after the order is submitted
            // Redirect the user to the order confirmation page after the form is submitted
            this.$store.commit('SET_ORDER_DATA', orderData);
            this.$router.push({
              path: '/order-confirmation'
            });
          }
        });
    },

    setCurrentDateTime() {
      const now = new Date(); // This gets the current date and time
      const timezoneOffsetInMs = now.getTimezoneOffset() * 60000; // This gets the timezone offset in milliseconds
      const localDate = new Date(now - timezoneOffsetInMs); // This converts the date to local time

      // Format the date for input[type="datetime-local"] (removing seconds and milliseconds)
      this.order.orderDate = localDate.toISOString().slice(0, 16);
    }
  },
  mounted() {
    this.setCurrentDateTime(); // Automatically set the date and time when the page is loaded
  }
};
</script>

<style scoped>
.form-container {
    background-color: rgba(255, 255, 255, 0.5); /* 50% opacity */
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    width: 400px;
}

h2 {
  font-family: 'Pacifico', cursive;
  text-align: center;
  text-decoration: underline;
  margin-bottom: 20px;
  text-transform: none;
  background-color: transparent;
  color: white
}

form {
  display: flex;
  flex-direction: column;
  color: white;
  font-family: 'Bree Serif', serif;
}

label {
  font-family: 'Bree Serif', serif;
  margin: 10px 0 5px;
  font-size: 16px;
}

input[type="text"],
input[type="datetime-local"],
input[type="time"],
select {
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 10px;
  font-family: 'Bree Serif', serif;
}

button {
  background-color: #cc4125;
  font-family: 'Pacifico', cursive;
  color: white;
  padding: 10px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
  border: solid white;
  text-transform: none;

}

button:hover {
  background-color: #45A049;
  text-decoration: underline;
}

div.inline {
  display: flex;
  align-items: center;
}

div.inline label {
  margin-left: 5px;
}
</style>
