<template>
    <div class="background">
        <NavbarAdmin/>
        <div >

        </div>


    </div>
</template>
<script>

import NavbarAdmin from '../components/NavbarAdmin.vue'
export default({
 name: "PharmacyAdmin",
  components: {
      NavbarAdmin
  }
})
</script>
<style scoped>
.background {
    background-image: url("../assets/img/medicine.jpg");
    position: absolute; 
    top: 0; 
    left: 0; 
    min-width: 100%;
    min-height: 100%;
  }
</style>