<template>
  <div>
    <b-navbar class="first" toggleable="lg" type="dark" variant="dark">
      <b-navbar-nav>
        <b-nav-item
          ><router-link to="/pharmacyAdminPage/homeAdmin" class="routerlink"
            >Home</router-link
          ></b-nav-item
        >

        <!-- Navbar dropdowns -->
        <b-nav-item-dropdown class="dropdaun" text="Pharmacy" left>
          <b-dropdown-item class="dropdaun" href="#"
            >Appointments</b-dropdown-item
          >
          <b-dropdown-item class="dropdaun" href="#"
            ><router-link to="/pharmacyAdminPage/list" class="routerlink"
              >Workers</router-link
            ></b-dropdown-item
          >
          <b-dropdown-item class="dropdaun" href="#"
            ><router-link to="/pharmacyAdminPage/medication" class="routerlink"
              >Medication</router-link
            ></b-dropdown-item
          >
          <b-dropdown-item class="dropdaun" href="#"
            ><router-link to="/pharmacyAdminPage/absences" class="routerlink"
              >Absences</router-link
            ></b-dropdown-item
          >
        </b-nav-item-dropdown>

        <b-nav-item-dropdown text="More" left>
          <b-dropdown-item class="dropdaun" href="#"
            ><router-link
              to="/pharmacyAdminPage/purchaseorder"
              class="routerlink"
              >Purchase order</router-link
            >
          </b-dropdown-item>
          <b-dropdown-item class="dropdaun" href="#"
            ><router-link to="/pharmacyAdminPage/action" class="routerlink"
              >Promotion</router-link
            ></b-dropdown-item
          >
          <b-dropdown-item class="dropdaun" href="#"
            ><router-link to="/pharmacyAdminPage/pricelist" class="routerlink"
              >Price list</router-link
            ></b-dropdown-item
          >
                    <b-dropdown-item class="dropdaun" href="#"
            ><router-link to="/pharmacyAdminPage/report" class="routerlink"
              >Report</router-link
            ></b-dropdown-item
          >
        </b-nav-item-dropdown>

        <b-nav-item-dropdown text="User" class="ml-auto" href="#" right>
          <b-dropdown-item class="dropdaun">
            <router-link to="/pharmacyAdminPage/edit-profile-admin" class="routerlink"
              >Edit profile</router-link
            ></b-dropdown-item
          >

        </b-nav-item-dropdown>
                   <b-navbar-nav class="ml-auto">
          <b-nav-item>
            <b-button style="background-color:red" @click="logout"
              >Logout</b-button
            >
          </b-nav-item>
        </b-navbar-nav>
        <!-- 
     <b-nav-item class="ml-auto"> <b-nav-form>
      <b-form-input class="mr-sm-2" placeholder="Search"></b-form-input>
      <b-button variant="outline-success" class="my-2 my-sm-0" type="submit">Search</b-button>
      </b-nav-form></b-nav-item> -->
      </b-navbar-nav> </b-navbar
    >
    <div></div>
  </div>
</template>

<script>
export default {
  name: "NavbarAdmin",

   methods: {
    logout() {
    this.$store.dispatch("logout");
    
      localStorage.setItem("user", null);
      this.$router.push("/LoginPage").catch(()=>{});

    },
  },
};
</script>
<style scoped>
.dropdaun {
  border: 2px solid #343a40;
  background: #343a40;
}

.routerlink {
  display: block;
  color: rgb(255, 255, 255);
  font-size: 18px;
}
</style>
