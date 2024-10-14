<template>
    <div class="about-container">
        <div class="about">
            <h1>About Us</h1>
            <p>Welcome to DOM's Bytes, where passion for pizza meets the art of coding! Located in the heart of
                Pittsburgh, with branches in Cranberry and Squirrel Hill, we’re dedicated to crafting custom pizzas that
                are as unique as your favorite algorithms.</p>

            <p>At DOM's Bytes, we believe that every pizza is a masterpiece waiting to be created. Our menu features a
                variety of specialty pizzas that change weekly, ensuring there's always something new to try. Whether
                you’re in the mood for our vibrant Array of Veggies, the hearty Byte of the Beast, or the nostalgic
                Primitive Cravings, we have something to satisfy every craving.</p>

            <p>For the adventurous, we present the Compiling Error—a pizza that pushes the boundaries of taste (and
                common sense) with its daring combination of strawberries and anchovies. As we like to say, “You don’t
                want this one…” but we dare you to try!</p>

            <p>Whether you’re ordering for delivery or picking up your piping hot creation, our team is here to ensure
                you enjoy every byte. Join us at DOM's Bytes, where every slice is a code-breaking experience!</p>
        </div>
        
        <h2>Contact Us</h2>
        
        <div class="locations">
            <div class="location">
                <p>Squirrel Hill</p>
                <p>2127 Murray Ave, Pittsburgh, PA, 15217</p>
                <p>Phone: 412-555-0001</p>
            </div>
            <div class="location">
                <p>Pittsburgh </p>
                <p>8 Market Square, Pittsburgh, PA, 15222</p>
                <p>Phone: 412-555-0002</p>
            </div>
            <div class="location">
                <p>Cranberry</p>
                <p>20111 Route 19, Cranberry Township, PA, 16066</p>
                <p>Phone: 724-555-0003</p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            // Add data here
        }
    },
    methods: {
        // Add methods here
    }
}
</script>

<style scoped>
.about-container {
    background-image: url('/pizza_imgs/pizza-home.png');
    background-size: cover;
    background-position: center;
    height: 100%;
    font-family: 'Pacifico', cursive;
    color: white;
    justify-content: center;
    align-items: center;
    padding-top: 100px;
}

.contact,
p {
    font-family: 'Bree Serif';
    font-size: 20px;
    margin-left: 15%;
    margin-right: 15%;
    text-align: center;
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

h1,
h2 {
    font-family: 'Pacifico', cursive;
    text-align: center;
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

.locations {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

.location {
    margin: 0 15px;
    /* Adjust spacing between locations */
    text-align: center;
    font-family: 'Bree Serif';
    font-size: 18px;
    /* Adjust font size for location details */
    color: white;
    /* Text color */
    text-shadow:
        -1px -1px 0 black,
        1px -1px 0 black,
        -1px 1px 0 black,
        1px 1px 0 black;
}
</style>