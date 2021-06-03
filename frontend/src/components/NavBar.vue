<template>
<div>  
  <div class="background">
   <b-navbar toggleable="lg" type="dark" variant="dark">
            <b-navbar-brand @click="uvecaj">NavBar</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item> 
                      <router-link to="" class="routerlink">Pharmacies</router-link>
                    </b-nav-item>

                    <b-nav-item>
                           <router-link to="/medications" class="routerlink">Medications</router-link>
                        </b-nav-item>

                    <b-nav-item>
                           <router-link to="" class="routerlink">Add pharmacy</router-link>
                        </b-nav-item>
                   

                    <b-nav-item>
                           <router-link to="/add-medication" class="routerlink">Add medication</router-link>
                        </b-nav-item>
                   
                   <b-nav-item>
                           <router-link to="/registration-by-admin" class="routerlink">Registration</router-link>
                        </b-nav-item>

                    <b-nav-item>
                           <router-link to="" class="routerlink">Loyality program</router-link>
                        </b-nav-item>

                        <b-nav-item>
                           <router-link to="" class="routerlink">Complaints</router-link>
                        </b-nav-item>

                </b-navbar-nav>

                <b-navbar-nav class="ml-auto">
                    <b-nav-form v-if="userType">
                        <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                        <b-button size="sm" class="my-2 my-sm-0" type="submit">{{myCount}}</b-button>
                    </b-nav-form>
                   
                        <b-nav-item>
                           <router-link to="/login" class="routerlink">Login</router-link>
                        </b-nav-item>

                        <b-nav-item>
                           <router-link to="/registration" class="routerlink">Registration</router-link>
                        </b-nav-item>
                
                  
                </b-navbar-nav>
            </b-collapse>
           
     </b-navbar>     
    </div>
  
  </div>
</template>

<style scoped>
.background {
  background-image: url("../assets/img/medicine.jpg");
  position:fixed;
  top: 0; 
  left: 0; 
  background-repeat:repeat-y;
	
  /* Preserve aspet ratio */
  min-width: 100%;
  min-height: 100%;
}
.routerlink {
  display: block;
  color: rgb(255, 255, 255);
} 
.b-navbar {
  z-index: -10000 !important;
}
</style>

<script>
export default {
  name: "NavBar",
   data() {
    return {
       userType: false
    };
  },
  computed: {
    myCount () {
      return this.$store.state.user.count
    }
  },
  methods: {
    uvecaj() {
      // Dispatch the action to buy a TV
      this.$store.dispatch('increment');
    }
  }
  }
</script>