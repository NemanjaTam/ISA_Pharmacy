<template>
   <div>  
       <b-navbar toggleable="lg" type="dark" variant="dark">
           <b-form class="routerlink">{{ userType }}</b-form>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item> 
                      <router-link to="/pharmacies" v-if="userType == 'SYSTEM_ADMINISTRATOR'" class="routerlink">Pharmacies</router-link>
                    </b-nav-item>

                    <b-nav-item>
                           <router-link to="/medications" class="routerlink">Medications</router-link>
                        </b-nav-item>

                    <b-nav-item>
                           <router-link to="/add-pharmacy" v-if="userType == 'SYSTEM_ADMINISTRATOR'" class="routerlink">Add pharmacy</router-link>
                        </b-nav-item>


                    <b-nav-item>
                           <router-link to="/add-medication" v-if="userType == 'SYSTEM_ADMINISTRATOR'" class="routerlink">Add medication</router-link>
                        </b-nav-item>

                   <b-nav-item>
                           <router-link to="/registration-by-system-admin" v-if="userType == 'SYSTEM_ADMINISTRATOR'" class="routerlink">Registration</router-link>
                        </b-nav-item>

                    <b-nav-item>
                           <router-link to="/add-loyalty" v-if="userType == 'SYSTEM_ADMINISTRATOR'" class="routerlink">Loyality program</router-link>
                        </b-nav-item>

                        <b-nav-item>
                           <router-link to="/complaintsAdmin" v-if="userType == 'SYSTEM_ADMINISTRATOR'" class="routerlink">Complaints</router-link>
                        </b-nav-item>

                   <b-nav-item>
                      <router-link to="/orders" v-if="userType == 'SUPPLIER'" class="routerlink">Orders</router-link>
                  </b-nav-item>

                  <b-nav-item>
                      <router-link to="/my-offers" v-if="userType == 'SUPPLIER'" class="routerlink">My Offers</router-link>
                  </b-nav-item>


                </b-navbar-nav>

                <b-navbar-nav class="ml-auto">
                   <b-nav-item>
                     <router-link to="/view-profile" v-if="userType == 'SYSTEM_ADMINISTRATOR' || 'SUPPLIER'" class="routerlink">Profile</router-link>
                   </b-nav-item>

                    <b-nav-item>
                        <router-link to="/edit-profile" v-if="userType == 'SYSTEM_ADMINISTRATOR' || 'SUPPLIER'" class="routerlink">Edit Profile</router-link>
                     </b-nav-item>

                   <b-nav-item>
                     <b-button style="background-color:red" v-if="userType == 'SYSTEM_ADMINISTRATOR' || 'SUPPLIER'" v-on:click="logout">Logout</b-button>
                  </b-nav-item>
                </b-navbar-nav>
            </b-collapse>

     </b-navbar>     
    </div>
</template>

<style scoped>
.background {
  background-image: url("../assets/img/medicine.jpg");
  position:absolute;
  top: 0; 
  left: 0; 
	
  /* Preserve aspet ratio */
  min-width: 100%;
  min-height: 100%;
}
.routerlink {
  display: block;
  color: rgb(255, 255, 255);
}
</style>

<script>
export default {
  name: "NavbarSystemAdmin",
  computed: {
    userType() {
      return this.$store.getters.getUserType;
    }
  },
   methods: {
    logout() {
      this.$store.dispatch("logout");
      this.$router.push({path:"loginPage"})
      localStorage.setItem("user", null);
    }
  }
};
</script>
