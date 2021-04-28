<template>
  <div >
        <!-- <AdminPage/> -->
     <NavBar/>
     <p>
    <!-- use router-link component for navigation. -->
    <!-- specify the link by passing the `to` prop. -->
    <!-- `<router-link>` will be rendered as an `<a>` tag by default -->
    <router-link to="/">Go to home</router-link>
    <router-link to="/login">Go to about</router-link>
  </p>
  <!-- route outlet -->
  <!-- component matched by the route will render here -->
  <router-view></router-view>
  </div>
</template>

<script>
//import AddMedication from './views/AddMedication'
//import AddPharmacy from './views/AddPharmacy'
//import AdminPage from './views/AdminPage'
//import RegistrationPage from './views/RegistrationPage.vue'
//import Login from './views/Login'
import NavBar from './components/NavBar'


export default {
  name: 'App',
  components: {
   //AddPharmacy
    NavBar
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
