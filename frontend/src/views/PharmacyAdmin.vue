<template>
  <div>
    <div>
      <div class="grid-container">
        <div class="header">
          <NavbarAdmin />
        </div>

        <div class="left"></div>
        <div class="middle"><router-view></router-view></div>
        <div class="right">
          <div class="info">
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
        </div>

        <!-- <div class="footer">
  </div> -->
      </div>
    </div>
  </div>
</template>
<script>
import NavbarAdmin from "../components/NavbarAdmin.vue";
export default {
  name: "PharmacyAdmin",
  components: {
    NavbarAdmin,
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
  //   User() {
  //     return this.$store.getters.getUser;
  //   },
  //   isPharmacyAdmin() {
  //     return this.$store.getters.isPharmacyAdministrator;
  //   },
  //   isRegisteredUser() {
  //     return this.$store.getters.isRegisteredUser;
  //   },
  // },
methods:{
    getPharmacyIdbyUserId(id) {
      fetch(`http://localhost:9005/api/pharmacy/getpharmacyidbyuser/${id}`, {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        method: "GET",
      })
        .then(function(response) {
          if (response.ok) {
            return response.json();
          } else {
            return Promise.reject(response);
          }
        })
        .then((data) => ( this.$store.dispatch("updatePharmacy",data)))
        .catch(function(error) {
          console.warn(error);
        });
    },
  
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
  border-radius: 25px;
  border: 5px solid #343a40;
  padding: 20px;
}
.containerMiddle {
  grid-area: middle;
  height: 500px;
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
  /*height: 500px; /* Should be removed. Only for demonstration */
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
