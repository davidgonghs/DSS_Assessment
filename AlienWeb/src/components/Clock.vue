<template>
  <div>
    <div>
      <h1>Earth Time:</h1>
      <p>{{ earthTime }}</p>
    </div>
    <div>
      <h1>Alien Time:</h1>
      <p>{{ alienTime }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      earthTime: '',
      alienTime: '',
    };
  },
  mounted() {
    this.refreshTime();
    setInterval(this.refreshTime, 500); // Refresh every 0.5 seconds
  },
  methods: {
    refreshTime() {
      axios.get('http://localhost:8080/alienClock/getTime')
          .then(response => {
            this.alienTime = response.data.alienTime; // You need to adapt to your API response
            this.earthTime = response.data.earthTime; // You need to adapt to your API response
            console.log(response.data);
          })
          .catch(error => {
            console.error('Error:', error);
          });
    },
  },
};
</script>
