<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
             <b-form class="routerlink">{{userType}}</b-form >
            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                </b-navbar-nav>
                <b-navbar-nav>
                    <b-nav-item> 
                        <router-link to="/edit-profile" class="routerlink">Edit Profile</router-link>
                    </b-nav-item>
                </b-navbar-nav>
                <b-navbar-nav v-if="isSupplier">
                    <b-nav-item> 
                        <router-link to="/make-purchase-order-offer" class="routerlink">Add offer</router-link>
                    </b-nav-item>
                </b-navbar-nav>
                <b-navbar-nav v-if="isAdmin">
                    <b-nav-item> 
                        <router-link to="/pharmacies" class="routerlink">Pharmacies</router-link>
                    </b-nav-item>
                    <b-nav-item> 
                        <router-link to="/add-pharmacy" class="routerlink">Add pharmacy</router-link>
                    </b-nav-item>
                    <b-nav-item> 
                        <router-link to="/medications" class="routerlink">Medications</router-link> 
                    </b-nav-item>
                     <b-nav-item> 
                        <router-link to="/add-medication" class="routerlink">Add Medication</router-link>
                    </b-nav-item>
                    <b-nav-item> 
                        <router-link to="/registration-by-admin" class="routerlink">Registration</router-link>
                    </b-nav-item>
                </b-navbar-nav>
                <b-navbar-nav class="ml-auto">
                    <b-nav-item>
                        <b-button style="background-color:red" v-on:click="logout">Logout</b-button>
                    </b-nav-item>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>     
    </div>
</template>

<script>

export default {
    name: "NavbarForAdminAndSupplier",
    computed: {
        userType() {
            return this.$store.getters.getUserType
        },
        isSupplier() {
            return this.$store.getters.isSupplier
        },
        isAdmin() {
            return this.$store.getters.isAdmin
        }
    },
    methods: {
        logout(){
            this.$store.dispatch('logout')
            this.$router.push('LoginPage')
        }
    }
}
</script>

<style scoped>

.routerlink {
  display: block;
  color: rgb(255, 255, 255);
  font-size: 18px;
}
</style>