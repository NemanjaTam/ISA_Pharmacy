<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-form class="routerlink">{{ userType }}</b-form>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item>
            <router-link to="/homePage" class="routerlink">Profile</router-link>
            <!-- <router-link class="routerlink">{{userFullname}}</router-link> -->
          </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav>
          <b-nav-item>
            <router-link to="/updateBasicInformation" class="routerlink"
              >Edit Profile</router-link
            >
          </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav>
          <b-nav-item>
            <router-link to="/allClients" class="routerlink"
              >All Clients</router-link
            >
          </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav>
          <b-nav-item>
            <router-link to="/workCalendar" class="routerlink"
              >Work Calendar</router-link
            >
          </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav v-if="!isDermatologist">
          <b-nav-item>
            <router-link to="/perscriptionMedication" class="routerlink"
              >Prescription Medication</router-link
            >
          </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav v-if="!isDermatologist">
        <b-nav-item>
            <router-link to="/subscription-list" class="routerlink"
              >Subscribe list</router-link
            >
          </b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav v-if="!isDermatologist">
        <b-nav-item>
            <router-link to="/sendComplaint" class="routerlink"
              >Send complaint</router-link
            >
          </b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav v-if="!isDermatologist">
        <b-nav-item>
            <router-link to="/complaintsUser" class="routerlink"
              >Complaints</router-link
            >
          </b-nav-item>
          </b-navbar-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item>
            <b-button style="background-color:red" v-on:click="logout"
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
      this.$store.dispatch("logout");
      this.$router.push({path:"loginPage"})
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
