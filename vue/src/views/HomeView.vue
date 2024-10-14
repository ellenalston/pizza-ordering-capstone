<template>
  <div class="home">
    <h1>DOM'S Bytes</h1>
    <h2> Pizzeria </h2>
    <p>Commit to a crust you can trust!</p>
    <div class="buttonContainer">
      <button v-on:click="goToMenuPage">Order Now</button>
    </div>

    <div class="carousel">
      <button @click="prevReview" :disabled="currentReviewIndex === 0" class="arrow left-arrow">←</button>
      <div class="review-box">
        <p v-if="reviews.length > 0">{{ reviews[currentReviewIndex].text }}</p>
        <p v-if="reviews.length > 0">- {{ reviews[currentReviewIndex].author }}</p>
      </div>
      <button @click="nextReview" :disabled="currentReviewIndex === reviews.length - 1"
        class="arrow right-arrow">→</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      reviews: [
        { text: "\"This pizza is a full-stack masterpiece! Crispy crust (back-end) and gooey toppings (front-end) work seamlessly. The jalapeños were a spicy hotfix! Worth every byte.\"", author: "Neil Pointer" },
        { text: "\"This pizza delivers in sprints—light, simple, and efficient. Clean, modular flavors, though it could use more seasoning in deployment. Scrum-tious!\"", author: "JSON Derulo" },
        { text: "\"Perfectly balanced pepperoni and cheese—no merge conflicts here! The crust is a great abstraction layer. I’ll commit to this order again soon!\"", author: "Al Gorithm" }
      ],
      currentReviewIndex: 0
    };
  },

  methods: {
    goToMenuPage() {
      this.$router.push('/menu');
    },
    nextReview() {
      if (this.currentReviewIndex < this.reviews.length - 1) {
        this.currentReviewIndex++;
      }
    },
    prevReview() {
      if (this.currentReviewIndex > 0) {
        this.currentReviewIndex--;
      }
    }
  }
};
</script>

<style scoped>

.home {
  background-image: url('/pizza_imgs/pizza-home.png');
  background-size: cover;
  background-position: center;
  height: 100%;
  font-family: 'Pacifico', cursive;
  color: white;
  justify-content: center;
  align-items: center;
  padding-top: 100px;
  background-attachment: fixed;
}

h1,h2,p {
  text-align: center;
}

h1 {
  font-size: 3em;
  margin-bottom: -0.5em;
  text-decoration: underline;

}

h2 {
  font-size: 1.8em;
  margin-top: 0;
}

p {
  font-size: 1.5em;
  margin-top: 0;
}

.buttonContainer {
  display: flex;
  justify-content: center;
  width: 100%;
  /* height: 8%; */
}

button {
  background-color: #cc4125;
  font-family: 'Pacifico', cursive;
  color: white;
  border: solid;
  padding: 1.2rem 2rem;
  /* Proportional padding for the button */
  font-size: 1.5rem;
  /* Larger font size */

  /* border: none;            
      border-radius: 10px;     */
  cursor: pointer;
  width: 20vw;
  /* Button width proportional to viewport width */
  text-align: center;
}

button:hover {
  background-color: #45A049;
}



nav a:hover {
  text-decoration: underline;
  color: #45A049;
}

.carousel {
  display: flex;
  align-items: center;
  /* Center align items vertically */
  justify-content: center;
  /* Center align items horizontally */
  margin: 20px 0;
  margin-top: 200px;
}

.review {
  flex: 1;
  /* Allow the review section to grow and take up available space */
  text-align: center;
}

.arrow {
  background: none;
  border: none;
  font-size: 5em;
  /* Larger font size for arrows */
  color: white;
  /* White color for the arrows */
  cursor: pointer;
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

.arrow:disabled {
  color: rgb(255, 255, 255);
  /* Change color when disabled */
  cursor: not-allowed;
  /* Change cursor to indicate disabled */

}

.carousel button:hover {
  background-color: rgba(255, 255, 255, 0.25);

}

.review-box {
  /* 50% opacity */
  padding: 20px;
  border-radius: 10px;
  /* Rounded corners */
  color: white;
  /* Text color */
  text-align: center;
  /* Center text */
  margin: 0 20px;
  /* Margin to separate from arrows */

  width: 60%;
  /* Set a fixed width or percentage */
  max-width: 800px;
  /* Optional: cap the width to a maximum */
  box-sizing: border-box;
  /* Ensures padding is included in width calculation */
}

.review-box p:first-child {
  font-family: 'Bree Serif';
  /* Change to your desired font for the review text */
  font-size: 1.2rem;
  /* Optional: adjust font size */

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

/* Style for the author (keep the same font) */
.review-box p:last-child {
  font-family: 'Pacifico', cursive;
  /* Keep the existing font for the author */
  font-size: 1rem;
  /* Optional: adjust font size */
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

;
</style>
