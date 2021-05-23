<template>
  <div class="background">
    <NavBar/>
<div>
  <b-tabs content-class="mt-3" align="center">
    <b-tab title="Registration pharmacy admin" active> <RegistrationPharmacyAdmin/> </b-tab>
    <b-tab title="Registration dermatologist"> <RegistrationDermatologist/> </b-tab>
    <b-tab title="Registration supplier"> <RegistrationSupplier/> </b-tab>
    <b-tab title="Registration system administrator"> <RegistrationSystemAdmin/> </b-tab>
  </b-tabs>
</div>
  </div>
</template>

<style scoped>
.tabs {
  margin-top: 25px;
}

.background {
  background-image: url("medicine.jpg");
  background-size: cover;
  overflow: auto;

}

</style>

<script>
import NavBar from "../components/NavBar";
import RegistrationPharmacyAdmin from "../components/RegistrationPharmacyAdmin"
import RegistrationSupplier from "../components/RegistrationSupplier"
import RegistrationSystemAdmin from "../components/RegistrationSystemAdmin"
import RegistrationDermatologist from "../components/RegistrationDermatologist"

export default {
  name: "AdminRegistration",
  components: {
    NavBar,
    RegistrationPharmacyAdmin,
    RegistrationSupplier,
    RegistrationSystemAdmin,
    RegistrationDermatologist
  },
  data() {
      return {
    
  }
  }
};
</script>