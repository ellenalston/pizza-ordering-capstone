<template>
    <div class="order-container">
        <button v-on:click="goToEmployeeView" class="action-button" id="employee-view">Go to Employee View</button>
        <h1 class="order-title">Pending Orders</h1>
        <div class="order-list">
            <ul>
                <li v-for="order in pendingOrders" :key="order.id" class="order-item">

                    <div class="order-information">

                        <div class="order-details">
                            <h2><strong>Order ID:</strong> {{ order.id }}</h2>
                            <p><strong>Order Date:</strong> {{ order.orderDate }}</p>
                            <p><strong>Order Type:</strong> {{ order.orderType }}</p>
                            <p><strong>Order Status:</strong> {{ order.status }}</p>
                            <p><strong>ASAP?:</strong> {{ order.asap ? 'Yes' : 'No' }}</p>
                            <p v-if="!order.asap"><strong>Preferred Completion Time:</strong> {{ order.pickupTime }}</p>
                        </div>

                        <div class="order-customer">
                            <div v-if="order.customer" class="customer-details">
                                <h2>Customer Information</h2>
                                <p><strong>Name:</strong> {{ order.customer.firstName }} {{ order.customer.lastName }}
                                </p>
                                <p v-if="order.orderType === 'Delivery'"><strong>Address:</strong> {{
            order.customer.address1 }} {{ order.customer.address2 }}
                                </p>
                                <p v-if="order.orderType === 'Delivery'">{{ order.customer.city }}, {{
            order.customer.state }} {{ order.customer.zipcode }}
                                </p>
                                <p><strong>Email:</strong> {{ order.customer.email }}</p>
                            </div>
                        </div>

                        <div class="order-pizzas">
                            <div v-for="pizza in order.pizzas" :key="pizza.id" class="order-pizza">
                                <pizza-display-vue :pizza="pizza"></pizza-display-vue>
                            </div>
                        </div>

                    </div>


                    <div class="order-buttons">
                        <button v-on:click="markComplete(order)" class="action-button">Mark as Completed</button>
                        <button v-on:click="markCancelled(order)" class="action-button">Cancel Order</button>
                    </div>
                </li>
            </ul>

        </div>

        <h1 class="order-title">Completed Orders</h1>
        <div class="order-list">
            <ul>
                <li v-for="order in completedOrders" :key="order.id" class="order-item completed-order">

                    <div class="order-information">

                        <div class="order-details">
                            <h2><strong>Order ID:</strong> {{ order.id }}</h2>
                            <p><strong>Order Date:</strong> {{ order.orderDate }}</p>
                            <p><strong>Order Type:</strong> {{ order.orderType }}</p>
                            <p><strong>Order Status:</strong> {{ order.status }}</p>
                            <p><strong>ASAP?:</strong> {{ order.asap ? 'Yes' : 'No' }}</p>
                            <p v-if="!order.asap"><strong>Preferred Completion Time:</strong> {{ order.pickupTime }}</p>
                        </div>

                        <div class="order-customer">
                            <div v-if="order.customer" class="customer-details">
                                <h2>Customer Information</h2>
                                <p><strong>Name:</strong> {{ order.customer.firstName }} {{ order.customer.lastName }}
                                </p>
                                <p v-if="order.orderType === 'Delivery'"><strong>Address:</strong> {{order.customer.address1 }} {{ order.customer.address2 }}
                                </p>
                                <p v-if="order.orderType === 'Delivery'">{{ order.customer.city }}, {{order.customer.state }} {{ order.customer.zipcode }}
                                </p>
                                <p><strong>Email:</strong> {{ order.customer.email }}</p>
                            </div>
                        </div>

                        <div class="order-pizzas">
                            <div v-for="pizza in order.pizzas" :key="pizza.id" class="order-pizza">
                                <pizza-display-vue :pizza="pizza"></pizza-display-vue>
                            </div>
                        </div>

                    </div>
                </li>
            </ul>
        </div>

        <h1 class="order-title">Cancelled Orders</h1>
        <div class="order-list">
            <ul>
                <li v-for="order in cancelledOrders" :key="order.id" class="order-item cancelled-order">

                    <div class="order-information">

                        <div class="order-details">
                            <h2><strong>Order ID:</strong> {{ order.id }}</h2>
                            <p><strong>Order Date:</strong> {{ order.orderDate }}</p>
                            <p><strong>Order Type:</strong> {{ order.orderType }}</p>
                            <p><strong>Order Status:</strong> {{ order.status }}</p>
                            <p><strong>ASAP?:</strong> {{ order.asap ? 'Yes' : 'No' }}</p>
                            <p v-if="!order.asap"><strong>Preferred Completion Time:</strong> {{ order.pickupTime }}</p>
                        </div>

                        <div class="order-customer">
                            <div v-if="order.customer" class="customer-details">
                                <h2>Customer Information</h2>
                                <p><strong>Name:</strong> {{ order.customer.firstName }} {{ order.customer.lastName }}
                                </p>
                                <p v-if="order.orderType === 'Delivery'"><strong>Address:</strong> {{order.customer.address1 }} {{ order.customer.address2 }}
                                </p>
                                <p v-if="order.orderType === 'Delivery'">{{ order.customer.city }}, {{order.customer.state }} {{ order.customer.zipcode }}
                                </p>
                                <p><strong>Email:</strong> {{ order.customer.email }}</p>
                            </div>
                        </div>

                        <div class="order-pizzas">
                            <div v-for="pizza in order.pizzas" :key="pizza.id" class="order-pizza">
                                <pizza-display-vue :pizza="pizza"></pizza-display-vue>
                            </div>
                        </div>

                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>



<script>
import CustomerService from '../services/CustomerService.js'
import PizzaService from '../services/PizzaService.js';
import PizzaDisplayVue from './PizzaDisplay.vue';

export default {

    components: {
        PizzaDisplayVue
    },
    data() {
        return {
            orders: [],

        }
    },
    created() {
        CustomerService.getOrders().then(
            (response) => {
                this.orders = response.data;
                this.orders.forEach(order => {
                    PizzaService.getPizzasByOrderId(order.id).then(
                        (pizzaResponse) => {
                            order.pizzas = pizzaResponse.data;

                            CustomerService.getCustomerById(order.customerId).then(
                                (customerResponse) => {
                                    order.customer = customerResponse.data;
                                }
                            ).catch((error) => {
                                console.error(`Failed to fetch customer for order ${order.id}:`, error);
                            });
                        }
                    ).catch((error) => {
                        console.error(`Failed to fetch pizzas for order ${order.id}:`, error);
                    });
                });
            }
        ).catch((error) => {
            console.error('Failed to fetch orders:', error);
        });
    },

    methods: {

        goToEmployeeView() {
            this.$router.push('/employee-view');
        },
        markComplete(order) {
            CustomerService.completeOrder(order.id).then(
                (response) => {
                    if (response.status === 200) {
                        alert('Order marked as completed')
                        this.orders = this.orders.filter(o => o.id !== order.id);

                    }
                }
            )

        },

        markCancelled(order) {
            CustomerService.cancelOrder(order.id).then(
                (response) => {
                    if (response.status === 200) {
                        alert('Order cancelled')
                        this.orders = this.orders.filter(o => o.id !== order.id);

                    }
                }
            )
        }
    },
    computed: {
        pendingOrders() {
            return this.orders.filter(order => order.status === 'Pending')
        },

        completedOrders() {
            return this.orders.filter(order => order.status === 'Completed')
        }, 

        cancelledOrders() {
            return this.orders.filter(order => order.status === 'Cancelled')
        }
    }
}

</script>

<style scoped>
.order-container {
    background-image: url('/pizza_imgs/custom-pizza.jpg');
    background-size: cover;
    background-position: center;
    background-color: black;
    height: 100%;
    background-attachment: fixed;
}

h1 {
    font-family: 'Pacifico', cursive;
    color: white;
}

.order-title {
    text-align: center;
}

.order-list {
    padding-left: 30%;
}

.order-item {
    display: flex;
    /* Enable flexbox */
    flex-direction: row;
    /* Stack children vertically */
    background-color: #F7F7F7;
    /* Light background for order items */
    margin-bottom: 15px;
    /* Space between orders */
    padding: 15px;
    border-radius: 8px;
    /* Rounded corners */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    /* Subtle shadow for depth */
    width: 50%;
    font-family: 'Bree Serif', serif;
    list-style-type: none;
    justify-content: space-evenly;
    align-items: center;
    flex-wrap:wrap;
    
}

.order-information {
    display: flex;
    /* Enable flexbox for order information */
    flex-direction: row;
    /* Arrange child elements in a row */
    margin-top: 10px;
    /* Space between order details and other elements */
    justify-content: space-evenly;
}

.order-details,
.order-customer {
    flex: 1;
    /* Adjust this if you want order details to take more space */
}

.order-pizzas {
    /* flex: 1; */
    /* Adjust this if you want pizzas to take less space */
    display: flex;
    /* If you want pizza items in a row too */
    flex-direction: column;
    /* Or keep this as column if you want them stacked */
}

.customer-details {
    margin-top: 10px;

    /* Space between order and customer details */
}

.order-pizzas {
    flex-direction: column;
    /* Stack pizzas vertically */
}

.order-buttons {
    display: flex;
    justify-content: center;
    /* Center buttons */
    margin-top: 10px;
    /* Space above buttons */
    margin-top: auto;
    /* Push buttons to the bottom of the container */
}

button {
    background-color: #cc4125;
    font-family: 'Pacifico', cursive;
    color: white;
    border: solid;
    border-color: white;
    padding: 10px;
    width: 100%;
    /* Makes buttons stretch to the container's width */
    max-width: 50%;
    /* Optional: Limit the maximum button width */
    text-align: center;
    margin: 10px 0;
}

.order-buttons {
    display: flex;
    justify-content: center;
    margin-top: 10px;
    /* Space above buttons */
}

.action-button {
    border-radius: 5px;
    padding: 10px 15px;
    font-size: 1rem;
    margin: 0 5px;
    /* Space between buttons */
    cursor: pointer;
    /* Pointer cursor on hover */
    transition: background-color 0.3s;
    /* Smooth background change */
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

.completed-order {
    background-color: #45A049;
}

.cancelled-order{
    background-color: #cc4125;
}
</style>