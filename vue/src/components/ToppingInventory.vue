<template>
    <div class="inventory-container">
        <button v-on:click="goToEmployeeView" class="action-button" id="employee-view">Go to Employee View</button>
        <h1 class="inventory-title">Topping Inventory</h1>
        <h2 class="inventory-subtitle">Check all toppings that are currently in stock</h2>

        <div class="toppings-wrapper">
            <div class="regular-toppings-container">
                <h3 class="toppings-section-title">Regular Toppings</h3>
                <ul class="toppings-list regular-toppings">
                    <li v-for="topping in regularToppings" :key="topping.id" class="topping-item">
                        <label class="topping-label">
                            <input type="checkbox" :checked="topping.available"
                                @change="toggleToppingInventory(topping.id, topping.available)"
                                class="topping-checkbox" />
                            {{ topping.name }}
                        </label>
                    </li>
                </ul>
            </div>

            <div class="premium-toppings-container">
                <h3 class="toppings-section-title">Premium Toppings</h3>
                <ul class="toppings-list premium-toppings">
                    <li v-for="topping in premiumToppings" :key="topping.id" class="topping-item">
                        <label class="topping-label">
                            <input type="checkbox" :checked="topping.available"
                                @change="toggleToppingInventory(topping.id, topping.available)"
                                class="topping-checkbox" />
                            {{ topping.name }}
                        </label>
                    </li>
                </ul>
            </div>

        </div>
        <button v-on:click="goToEmployeeView" class="action-button" id="done-button">Done</button>

    </div>
</template>



<script>
import PizzaService from '../services/PizzaService.js';
export default {
    data() {
        return {
            toppings: [],
        }
    },
    created() {
        PizzaService.getRegularToppings().then(
            (response) => {
                this.toppings.push(...response.data);
            }
        );
        PizzaService.getPremiumToppings().then(
            (response) => {
                this.toppings.push(...response.data);
            }
        );
    },

    methods: {

        goToEmployeeView() {
            this.$router.push('/employee-view');
        },

        toggleToppingInventory(toppingId, isAvailable) {
            const newAvailability = !isAvailable; //toggle the availability
            PizzaService.toggleToppingInventory(toppingId).then(() => {
                // Optionally update the local state after successful toggle
                this.toppings.forEach((topping) => {
                    if (topping.id === toppingId) {
                        topping.available = newAvailability;
                    }
                });
            })
                .catch((error) => {
                    console.error("Error toggling topping availability:", error);
                });

        }
    },
    computed: {
        premiumToppings() {
            return this.toppings.filter((item) => {
                return item.type === "Premium";
            });
        },
        regularToppings() {
            return this.toppings.filter((item) => {
                return item.type === "Regular";
            });
        },
    },

}
</script>

<style scoped>
h1 {
    font-family: 'Pacifico', cursive;
    
}

h2 {
    font-family: 'Bree Serif', serif;
}

h1,h2{
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

.inventory-container {
    background-image: url('/pizza_imgs/topping-inventory-image.jpg');
    background-size: cover;
    background-position: center;
    background-color: black;
    height: 100vh;
    background-attachment: fixed;
    display: flex;
    justify-content: center;
    text-align: center;
    align-content: center;
    flex-direction: column;
    align-items: center;
}

.toppings-wrapper {
    display: flex;
    /* Use Flexbox for layout */
    justify-content: space-between;
    /* Space them out */
    margin: 20px 0;
    /* Add some vertical spacing */
    font-family: 'Bree Serif', serif;
    /* Font style */

    width: 50%

}

.regular-toppings-container,
.premium-toppings-container {
    flex: 1;
    /* Allow both containers to grow equally */
    margin: 0 10px;
    /* Add some horizontal margin between them */
    background-color: rgba(69, 160, 73, 0.5);
    padding: 10px;
    /* Add some padding */
    color: white;
    /* White text color */
    border-radius: 8px;
    /* Rounded corners */


}

.toppings-list {
    list-style-type: none;
    /* Removes bullet points */
    padding: 0;
    /* Removes default padding */
    margin: 0;
    /* Removes default margin */
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

.action-button:hover {
    background-color: #45A049;
    text-decoration: underline;
}

#employee-view {
    margin: 20px 0;
    max-width: 10%;
    margin-left: 90%;
}

#done-button {
    margin-top: 20px;
    align-content: center;
}
</style>