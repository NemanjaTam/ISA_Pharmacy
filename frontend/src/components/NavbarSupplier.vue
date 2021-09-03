<template>
  <div>
    <b-navbar class="first" toggleable="lg" type="dark" variant="dark">
      <b-navbar-nav>
        <b-nav-item
          ><router-link to="/supplier-home" class="routerlink"
            >Home</router-link
          ></b-nav-item
        >
        <b-nav-item>
            <router-link to="/orders" class="routerlink">Orders</router-link>
        </b-nav-item>

        <b-nav-item>
            <router-link to="/my-offers" class="routerlink">My Offers</router-link>
        </b-nav-item>

        <b-nav-item>
            <router-link to="/edit-offer/:id" class="routerlink">Edit offer</router-link>
        </b-nav-item>

        <b-nav-item>
            <router-link to="/orders/:id" class="routerlink">Order</router-link>
        </b-nav-item>

         <b-nav-item>
            <router-link to="/view-profile" class="routerlink">Profile</router-link>
        </b-nav-item>

        <b-nav-item>
            <router-link to="/edit-profile" class="routerlink">Edit Profile</router-link>
            </b-nav-item>

        <b-nav-item>
            <b-button style="background-color:red" v-on:click="logout">Logout</b-button>
        </b-nav-item>
      </b-navbar-nav> </b-navbar
    >``
    <div></div>
  </div>
</template>

<script>
export default {
  name: "NavbarSupplier",
  methods: {
    logout(){
      window.location.href=`http://localhost:8080/`
    }
  }
};
</script>
<style scoped>
.dropdaun {
  border: 2px solid #343a40;
  background: #343a40;
}

.routerlink {
  display: block;
  color: rgb(255, 255, 255);
  font-size: 18px;
}
</style>
