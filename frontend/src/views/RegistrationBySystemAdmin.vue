<template>
<div class = "background">
    <NavbarSystemAdmin/>
  <nav class="skew-menu">
    <ul>
      <li> <router-link to="/registration-pharmacy-admin" class="routerlink">Registration pharmacy admin</router-link></li>
      <li> <router-link to="/registration-dermatologist" class="routerlink">Registration dermatologist</router-link></li>
      <li> <router-link to="/registration-supplier" class="routerlink">Registration supplier</router-link></li>
      <li> <router-link to="/registration-system-admin" class="routerlink">Registration system admin</router-link></li>
    </ul>
  </nav>
</div>
</template>

<style scoped>
  .background {
      background-image: url("../assets/img/medicine.jpg");
      position: absolute; 
      top: 0; 
      left: 0; 
      min-width: 100%;
      min-height: 100%;
  } 
body {
  font-family: Helvetica Neue, Helvetica, sans-serif;
  background: tomato;
}
.skew-menu {
  text-align: center;
  margin-top: 5em;
  margin-left: 20em;
}
.skew-menu ul {
  display: inline-block;
  margin: 0;
  padding: 0;
  list-style: none;
  transform: skew(-25deg);
}
.skew-menu ul li {
  background: #fff;
  float: left;
  border-right: 1px solid #eee;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
  text-transform: uppercase;
  color: #555;
  font-weight: bolder;
  transition: all 0.3s linear;
}
.skew-menu ul li:first-child {
  border-radius: 7px 0 0 7px;
}
.skew-menu ul li:last-child {
  border-right: none;
  border-radius: 0 7px 7px 0;
}
.skew-menu ul li:hover {
  background: #eee;
  color: tomato;
}
.skew-menu ul li a {
  display: block;
  padding: 1em 2em;
  color: inherit;
  text-decoration: none;
  transform: skew(25deg);
}
</style>

<script>
import NavbarSystemAdmin from '../components/NavbarSystemAdmin.vue'

export default {
  name: "RegistrationBySystemAdmin",
  components: {
      NavbarSystemAdmin
  }
  }
</script>