<template>
    <div class="background">
        <NavbarSystemAdmin/>
    </div>
</template>

<script>
import axios from 'axios'
import NavbarSystemAdmin from '../components/NavbarSystemAdmin.vue'

export default {
    name: "SupplierHome",
    components: {
        NavbarSystemAdmin
    },
}
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