<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
            <b-navbar-brand>
                <router-link to="/homePage" class="routerlink">Dermatologist</router-link>
            </b-navbar-brand>
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
                        <router-link to="/updateBasicInformation" class="routerlink">Edit Profile</router-link>
                    </b-nav-item>
                </b-navbar-nav>
                <b-navbar-nav>
                    <b-nav-item> 
                        <router-link to="/allClients" class="routerlink">All Clients</router-link>
                    </b-nav-item>
                </b-navbar-nav>
                <b-navbar-nav>
                    <b-nav-item> 
                        <router-link to="/workCalendar" class="routerlink">Work Calendar</router-link>
                    </b-nav-item>
                </b-navbar-nav>
                <b-navbar-nav v-if="false">
                    <b-nav-item> 
                        <router-link to="/perscriptionMedication" class="routerlink">Prescription Medication</router-link>
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
    name: "TopMenuForPD",
    methods: {
        logout(){

        }
    }
}
</script>

<style scoped>
.routerlink {
  display: block;
  color: rgb(255, 255, 255);
}
</style>