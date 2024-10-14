<template>
    <div class="home">
        <button v-on:click="goToEmployeeView" class="action-button" id="employee-view">Go to Employee View</button>
        <h1>Specialty Pizzas</h1>
        <button v-on:click="goToCustomPage" class="action-button">Compile Your Own</button>
        <div class="pizza-container" v-if="specialtyPizzas.length">
            <!-- Loop through each specialty pizza and display its name, description-->
            <div v-for="pizza in specialtyPizzas" v-bind:key="pizza.id" class="specialtyPizzas">
                <h2>{{ pizza.specialtyName }} - ${{ calculatePrice(pizza) }}</h2>
                <h3>{{ pizza.available ? 'Available' : 'Unavailable' }}</h3>
                <p>{{ pizza.specialDesc }}</p>
                <button v-on:click="togglePizzaAvailability(pizza.id, pizza.available)"
                    :class="pizza.available ? 'mark-available' : 'mark-unavailable'">
                    {{ pizza.available ? 'Mark Unavailable' : 'Mark Available' }}
                </button>
            </div>
        </div>
    </div>
</template>

<script>
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
        goToEmployeeView() {
            this.$router.push('/employee-view');
        },

        togglePizzaAvailability(pizzaId, isAvailable) {
            const newAvailability = !isAvailable; //toggle the availability
            PizzaService.togglePizzaAvailability(pizzaId).then(() => {
                // Optionally update the local state after successful toggle
                this.specialtyPizzas.forEach((pizza) => {
                    if (pizza.id === pizzaId) {
                        pizza.available = newAvailability;
                    }
                });
            }).catch((error) => {
                console.error("Error toggling topping availability:", error);
            });
        },

        calculatePrice(pizza) {
            let price = pizza.size.price + pizza.crust.price + pizza.sauce.price;

            pizza.toppings.forEach(topping => {
                price += topping.price;
            });

            return price.toFixed(2);
        },
        // Method to navigate to the custom pizza page
        goToCustomPage() {
            this.$router.push('/create-specialty-pizza');
        },


    }

}
</script>

<style scoped>
.home {
    background-image: url('/pizza_imgs/employee-specialty-page.jpg');
    background-size: cover;
    background-position: center;
    background-color: black;
    height: 100%;
    background-attachment: fixed;
    font-family: 'Bree Serif', serif;
    /* Font style matching the second template */
    padding: 20px;
    /* Adding some padding for overall spacing */
    text-align: center;
    /* Centered text */
}

.pizza-container {
    
    border-radius: 8px;
    /* Rounded corners */
    padding: 15px;
    /* Padding around the container */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    /* Subtle shadow for depth */
    margin-bottom: 20px;
    /* Space below the pizza container */
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
}

h1 {
    text-align: center;
    /* Centered title */
    margin-bottom: 20px;
    /* Space below the title */

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

.specialtyPizzas {
    margin-bottom: 15px;
    /* Space between each specialty pizza */
    padding: 10px;
    /* Padding inside each pizza item */
    background-color: white;
    /* White background for each pizza item */
    border-radius: 5px;
    /* Rounded corners for pizza items */
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
    /* Subtle shadow for depth */
    width: 50%;
}

h2 {
    margin: 5px 0;
    /* Space above and below the pizza name */
}

h3 {
    margin: 5px 0;
    /* Space above and below the availability */
}

button {
    background-color: #cc4125;
    /* Match button color */
    font-family: 'Pacifico', cursive;
    /* Matching font style */
    color: white;
    /* Text color */
    border: solid;
    /* Border style */
    border-color: white;
    /* Border color */
    padding: 10px;
    /* Padding inside button */
    width: 100%;
    /* Stretch to full width */
    max-width: 50%;
    /* Limit button width */
    text-align: center;
    /* Center text in button */
    margin: 10px 0;
    /* Space around buttons */
}

.mark-unavailable {
    background-color: #cc4125;
    /* Red when marked unavailable */
}

.mark-unavailable:hover {
    background-color: green;
    /* Green on hover */
}

/* Button styles for "Mark Available" */
.mark-available {
    background-color: green;
    /* Green when marked available */
}

.mark-available:hover {
    background-color: #cc4125;
    /* Red on hover */
}

#employee-view {
    margin: 20px 0;
    max-width: 10%;
    margin-left: 90%;
}

#employee-view:hover {
    background-color: #45A049;
    text-decoration: underline;
}

.action-button:hover {
    background-color: #45A049;
    text-decoration: underline;
}
</style>
