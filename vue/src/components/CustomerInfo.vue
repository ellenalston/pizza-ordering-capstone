<template>
    <div class="form-container">
        <h2>Customer Information</h2>
        <form v-on:submit.prevent="createCustomer" class="customerForm">
            <!-- First Name -->
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName" placeholder="Enter your first name" required
                v-model="newCustomer.firstName">

            <!-- Last Name -->
            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="lastName" placeholder="Enter your last name" required
                v-model="newCustomer.lastName">

            <!-- Address 1 -->
            <label for="address1">Address 1</label>
            <input type="text" id="address1" name="address1" placeholder="Enter your address" required
                v-model="newCustomer.address1">

            <!-- Address 2 -->
            <label for="address2">Address 2</label>
            <input type="text" id="address2" name="address2" placeholder="Enter your address"
                v-model="newCustomer.address2">

            <!-- City -->
            <label for="city">City</label>
            <input type="text" id="city" name="city" placeholder="Enter your city" required 
                v-model="newCustomer.city">

            <!-- State -->
            <label for="state">State</label>
            <input type="text" id="state" name="state" placeholder="Enter your state" required
                v-model="newCustomer.state">

            <!-- ZIP Code -->
            <label for="zipcode">ZIP Code</label>
            <input type="text" id="zipcode" name="zipcode" placeholder="Enter your ZIP Code" required
                v-model="newCustomer.zipcode">

            <!-- Email -->
            <label for="email">Email</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required
                v-model="newCustomer.email">

            <!-- Submit Button -->
            <button type="submit">Submit</button>
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
            newCustomer: { 
                firstName: '',
                lastName: '',
                address1: '',
                address2: '',
                city: '',
                state: '',
                zipcode: '',
                email: '',

            }
        }

    },
    methods: {
        createCustomer() {
            // This is the method that will be called when the form is submitted
            CustomerService.createCustomer(this.newCustomer)
                .then(response => {
                    if (response.status === 201) {
                        alert("Your customer information has been submitted!");
                        console.log(response.data);
                        let customerId = response.data.id;
                        // This is the customer ID that will be used to create the order and gets pushed to the store
                        this.$store.commit('SET_CUSTOMER_ID', customerId);
                        // This is the route that the user will be redirected to after submitting the form
                        this.$router.push('/checkout');
                        // This is the object that will be reset after the form is submitted
                        this.newCustomer = {
                            firstName: '',
                            lastName: '',
                            address1: '',
                            address2: '',
                            city: '',
                            state: '',
                            zipcode: '',
                            email: '',

                        }

                    }
                })
                .catch(e => {
                    console.log(e);
                });
        }
    }
}
</script>

<style scoped>
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

body {
    font-family: 'Bree Serif', serif;
    background-color: #F2F2F2;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

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
}

form {
    display: flex;
    flex-direction: column;
}

label {
    font-family: 'Bree Serif', serif;
    margin: 10px 0 5px;
    font-size: 16px;
}

input[type="text"],
input[type="email"],
input[type="month"] {
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
}

button:hover {
    background-color: #45A049;
    text-decoration: underline;
}
</style>
