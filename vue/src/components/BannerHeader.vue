<template>
  <header v-if="!$route.meta.requiresAuth">
    <div class="header-container">
      <div class="logo">DOM's Bytes</div>
      <img src="/pizza_imgs/doms_logo_blk.png" class="logo-img" >

      <nav>
        <ul>
          <li><a href="/">Home</a></li>
          <li><a :href="pdfLink" target="_blank">Menu</a></li>
          <li><a href="/about-us">About Us</a></li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      pdfLink: '/doms_menu.pdf'
    };
  }
};
</script>

<style scoped>
body {
  margin: 0;
  margin-top: 0px;
  padding: 0;
  height: 100%;
  background-color: white;
}

.header-container {
  height: 15vh;
  display: flex;
  align-items: center;
  /* Vertically center items */
  padding: 0 1rem;
  font-family: 'Pacifico', cursive;
  /* width: 100%; */
  /* Ensure full width of the screen */
}

.logo {
  /* You can style the logo separately if needed */
  font-size: 4rem;
  /* Adjust size of the logo text */
  color: white;

  font-family: 'Pacifico', cursive;

  text-decoration: underline;

  margin-bottom: 50px;

}

nav {
  margin-left: auto;
  /* Pushes the nav to the right */
}

nav ul {
  list-style: none;
  /* Remove default list styles */
  padding: 100px;
  /* Remove padding */
  margin: 0;
  /* Remove margin */
  display: flex;
  /* Display nav items in a row */
}

nav li {
  margin: 0 2rem;
  font-size: 2rem;
  /* Space between nav items */
}

nav a {
  color: white;
  text-decoration: none;
}

header {
  background-color: black;
  color: white;
  padding: 1rem 0;
  position: sticky;
  /* Makes the header stay at the top when scrolling */
  top: 0;
  width: 100%;
  z-index: 1000;
  /* Ensure it stays on top */
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  height: 15vh;
}

.logo-img {
  max-height: 80%;
  /* Ensures the image is no more than 80% of the header height */
  margin-right: 1rem;
  /* Adds space between the image and the logo text */
}
</style>