<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-form class="routerlink">{{ userType }}</b-form>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item>
            <router-link to="/pharmacy/1" class="routerlink"
              >SEE PHARMACY PROFILE</router-link
            >
          </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav>
          <b-nav-item>
            <router-link to="/allClients" class="routerlink"
              >DERMATOLOGIST & PHARMACIST</router-link
            >
          </b-nav-item>
        </b-navbar-nav>

       
        <b-navbar-nav class="ml-auto">
          <b-nav-item>
            <b-button style="background-color:red" @click="logout"
              >Logout</b-button
            >
          </b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "TopMenuForPD",
  computed: {
    userType() {
      return this.$store.getters.getUserType;
    },
    isDermatologist() {
      return this.$store.getters.isDermatologist;
    },
  },
  methods: {
    logout() {
          this.$router.push("/LoginPage").catch(()=>{});
      this.$store.dispatch("logout");
    
      localStorage.setItem("user", null);
    },
  },
};
</script>

<style scoped>
.routerlink {
  display: block;
  color: rgb(255, 255, 255);
  font-size: 18px;
}
</style>
