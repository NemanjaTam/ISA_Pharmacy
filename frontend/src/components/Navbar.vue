<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
            <b-navbar-brand href="#">NavBar</b-navbar-brand>
            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item> 
                        <router-link to="/" class="routerlink">Profile</router-link>
                    </b-nav-item>
                </b-navbar-nav>
                    <b-navbar-nav class="ml-auto">
                    <b-nav-form>
                        <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                        <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
                    </b-nav-form>
                    <b-nav-item>
                        <router-link to="/loginPage" class="routerlink">Login</router-link>
                    </b-nav-item>
                    <b-nav-item>
                        <router-link to="/registrationPage" class="routerlink">Registration</router-link>
                    </b-nav-item>
                     <b-nav-item>
                        <router-link to="/medications" class="routerlink">Medications</router-link>
                    </b-nav-item>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>     
    </div>
</template>

<script>
export default {
    name: "Navbar"
}
</script>

<style scoped>
.routerlink {
    display: block;
    color: rgb(255, 255, 255);
}
</style>