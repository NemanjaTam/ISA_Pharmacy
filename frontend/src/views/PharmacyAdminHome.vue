<template>
  <div class="info">
    <label> Name</label>
    <b-input disabled v-model="User.name"></b-input>
    <br/>
    <label>Surname</label>
     <b-input disabled v-model="User.surname"></b-input>
     <br/>
     <label>Role</label>
     <b-input disabled v-model="userType"></b-input>
    <!-- <ul style="list-style-type:none;">
      <li>Name</li>
      <li>
        <label></label>
      </li>
      <li>Surname</li>
      <li><label></label></li>
      <li>E-mail</li>
      <li><label></label></li>
      <li>Date Of Birth</li>
      <li><label></label></li>
    </ul> -->
  </div>
</template>
<script>
import NavbarAdmin from "../components/NavbarAdmin.vue";
export default {
  name: "PharmacyAdminHome",
  computed:{
    userType() {
      return this.$store.getters.getUserType;
    },
        User() {
            return this.$store.getters.getUser
        },

  },
  components: {},
  mounted(){
       if(this.userType != "PHARMACY_ADMINISTRATOR"){
       this.$router.push("/LoginPage").catch(()=>{});
    }
  }
};
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

.info {
  background: white;

  /* padding: 20px; */
}

* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Style the header */
.header {
  grid-area: header;
  padding: 0px;

  text-align: center;
  font-size: 15px;
}

/* The grid container */
.grid-container {
  background-image: url("../assets/img/medicine.jpg");
  position: absolute;
  top: 0;
  left: 0;
  min-width: 100%;
  min-height: 100%;
  display: grid;
  grid-template-areas:
    "header header header header header header"
    "left middle middle middle middle right"
    "footer footer footer footer footer footer";
  /* grid-column-gap: 10px; - if you want gap between the columns */
}

.left,
.middle,
.right {
  padding: 25px;
  height: 500px; /* Should be removed. Only for demonstration */
}

/* Style the left column */
.left {
  grid-area: left;
}

/* Style the middle column */
.middle {
  grid-area: middle;
}

/* Style the right column */
.right {
  grid-area: right;
}

/* Style the footer */
.footer {
  grid-area: footer;

  padding: 10px;
  opacity: 0.5;
  text-align: center;
}

/* Responsive layout - makes the three columns stack on top of each other instead of next to each other */
@media (max-width: 600px) {
  .grid-container {
    grid-template-areas:
      "header header header header header header"
      "left left left left left left"
      "middle middle middle middle middle middle"
      "right right right right right right"
      "footer footer footer footer footer footer";
  }
}
</style>
