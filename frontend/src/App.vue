<template>
  <div >
    <NavBar/>
     <!-- <RegistrationPage/> -->
    
    <!-- use router-link component for navigation. -->
    <!-- specify the link by passing the `to` prop. -->
    <!-- `<router-link>` will be rendered as an `<a>` tag by default -->
   <router-view> </router-view>
 
  <!-- route outlet -->
  <!-- component matched by the route will render here -->
  
  </div>
</template>

<script>
import AddMedication from './components/AddMedication.vue'
//import AddPharmacy from './views/AddPharmacy'
//import AdminRegistration from './views/AdminRegistration'
import RegistrationPage from './views/RegistrationPage.vue'
import NavBar from './components/NavBar.vue'
import LoginPage from './views/LoginPage.vue'
//import Home from './views/Home.vue'
import RegistrationPharmacyAdmin from './components/RegistrationPharmacyAdmin.vue'
import RegistrationDermatologist from './components/RegistrationDermatologist.vue'
import RegistrationSupplier from './components/RegistrationSupplier.vue'
import RegistrationSystemAdmin from './components/RegistrationSystemAdmin.vue'

export default {
  name: 'App',
  components: {
   NavBar,
    RegistrationPage,
    LoginPage,
    AddMedication,
    RegistrationPharmacyAdmin,
    RegistrationDermatologist,
    RegistrationSupplier,
    RegistrationSystemAdmin
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
