<template>
  <div id="capstone-app">
    <banner-header/>
    <div id="nav" v-if="$route.meta.requiresAuth">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
   
  </div>
</template>

<script>
import BannerHeader from './components/BannerHeader.vue';

export default {
  components: {
    BannerHeader,
  },
};
</script>
<style>

body{
  background-color: black;
}

#nav{
  padding-left: 10px;
  font-family:Pacifico;
}
</style>
