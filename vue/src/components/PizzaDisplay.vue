<template>
    <div>
        <h2>{{ pizza.specialtyName }}</h2>
        <p>Size: {{ pizza.size.name }}</p>
        <p>Crust: {{ pizza.crust.name }}</p>
        <p>Sauce: {{ pizza.sauce.name }}</p>
        <p>Toppings:
        <div v-for="topping in pizza.toppings" :key="topping.id">{{ topping.name }}</div>
        </p>
    </div>
</template>

<script>
export default {

    props: {
        pizza: {
            type: Object,
            required: true
        }
    }

}
</script>

<style></style>