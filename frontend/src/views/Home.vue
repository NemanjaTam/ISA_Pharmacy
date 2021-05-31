<template>
  <div class="background">  
    <Navbar/>
  </div>
</template>

<script>

import Navbar from '../components/Navbar.vue'

export default {
  name: "Home",
  components: {
    Navbar
  }
};
</script>

<style scoped>
  .background {
    background-image: url("../assets/img/medicine.jpg");
    position: fixed; 
    top: 0; 
    left: 0; 
    min-width: 100%;
    min-height: 100%;
}
</style>