<template>
      <div class="grid-container">
        <div class="header">
          <PatientNavBar />
        </div>

        <div class="left"></div>
        <div class="middle"><router-view></router-view></div>
        <div class="right">
        </div>
        </div>
  
</template>
<script>
import PatientNavBar from "../components/PatientNavBar.vue";
export default {
  name: "PatientHomePage",
  components: {
    PatientNavBar,
  },
  computed: {
    User() {
      return this.$store.getters.getUser;
    },
    userType() {
      return this.$store.getters.getUserType;
    },
    userId() {
      return this.$store.getters.getId;
    },
    Pharmacy() {
      return this.$store.getters.getPharmacy;
    },
    isRegistered() {
      return this.$store.getters.isRegistered;
    },
  },
  data(){
    return{

    }
  },
  };
</script>
<style scoped>

</style>