<template>
  <div>
    <form @submit.prevent="convertDate">
      <label for="alienDate">Select Alien Date:</label>
      <input type="text" id="alienDate" v-model="selectedAlienDate" @input="validateInput">

      <button type="submit">Convert</button>
      <br>
      <span v-if="inputError" class="error" style="color: red">{{ inputError }}</span>
    </form>

    <div v-if="earthDate">
      <p>Selected Alien Date: {{ selectedAlienDate }}</p>
      <p>Corresponding Earth Date: {{ earthDate }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      selectedAlienDate: '',
      earthDate: '',
      inputError: ''
    };
  },
  methods: {
    validateInput() {
      const alienDate = this.selectedAlienDate;

      // Validation condition
      const alienDateRegex = /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/;
      if (alienDateRegex.test(alienDate)) {
        this.inputError = ''; // Input is valid
      } else {
        this.inputError = 'Invalid input. Please use the format YYYY-MM-DD HH:mm:ss'; // Input is invalid
      }
    },
    convertDate() {
      if (!this.inputError) { // Check for input validity before sending the request
        axios
            .post('http://localhost:8080/alienClock/convertEarthTime', {
              alienTime: this.selectedAlienDate
            })
            .then((response) => {
              this.earthDate = response.data;
            })
            .catch((error) => {
              console.error('Error:', error);
            });
      }
    }
  }
};
</script>
