<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item> 
                        <router-link to="/medications" class="routerlink">Medications</router-link>
                    </b-nav-item>
                </b-navbar-nav>
                    <b-navbar-nav class="ml-auto">
                    <b-nav-item>
                        <router-link to="/loginPage" class="routerlink">Login</router-link>
                    </b-nav-item>
                    <b-nav-item>
                        <router-link to="/registrationPage" class="routerlink">Registration</router-link>
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