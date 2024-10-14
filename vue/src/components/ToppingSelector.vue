<template>

    <div>
      <h1>Regular Toppings</h1>
      {{ customer }}
      {{ order }}
      {{ pizza }}
      {{ toppings }}
      {{ toppingList }}
      <div id="regTopping" v-for="item in RegularToppings" :key="item.id">
        <input
          type="checkbox"
          v-model="orderDTO.pizza.toppings"
          :value="{topping_id: item.id}"
        />
        <label>{{ item.topping_name }}</label>
      </div>
  
      <h1>Premium Toppings</h1>
      <div id="premiumTopping" v-for="item in PremiumToppings" :key="item.id">
        <input
          type="checkbox"
          v-model="orderDTO.pizza.toppings"
          :value="{topping_id: item.id}"
        />
        <label>{{ item.topping_name }}</label>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        orderDTO: {
          customer: {},
          order: {},
          pizza: {
            toppings: [],
          },
        },
        toppingList: [
          {
            id: 1,
            topping_name: "Burrata Cheese",
            topping_type: "Premium",
            topping_price: 2.0,
          },
          {
            id: 2,
            topping_name: "Blue Cheese",
            topping_type: "Premium",
            topping_price: 2.0,
          },
          {
            id: 3,
            topping_name: "Roasted Peppers",
            topping_type: "Premium",
            topping_price: 2.0,
          },
          {
            id: 4,
            topping_name: "Pepperoni",
            topping_type: "Regular",
            topping_price: 1.0,
          },
          {
            id: 5,
            topping_name: "Mushrooms",
            topping_type: "Regular",
            topping_price: 1.0,
          },
        ],
      };
    },
    computed: {
      PremiumToppings() {
        return this.toppingList.filter((item) => {
          return item.topping_type === "Premium";
        });
      },
      RegularToppings() {
        return this.toppingList.filter((item) => {
          return item.topping_type === "Regular";
        });
      },
    },
  };
  </script>