<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
            <b-navbar-brand href="#">NavBar</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item href="#">Primer</b-nav-item>
                </b-navbar-nav>

                
                <b-navbar-nav class="ml-auto">
                    <b-nav-form>
                        <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                        <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
                    </b-nav-form>

                    <b-nav-item href="/login">Log in</b-nav-item>
                    <b-nav-item href="/registration">Sign up</b-nav-item>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
          <!-- <Link to ="/">Registration</Link>
                    <Link to="/pharmacies">Pharmacies</Link>
                    <Link to="/addPharmacy">Add pharmacy</Link>
                    <Link to="/">Medications</Link>
                    <Link to="/addMedication">Add medication</Link>
                    <Link to="/">Loyality program</Link>
                    <Link to="/">Complaints</Link> -->
              
    </div>
</template>

<script>
export default {
    name: "NavBar"
}
</script>

