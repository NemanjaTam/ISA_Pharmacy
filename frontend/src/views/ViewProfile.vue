<template>
  <div class="background">
    <NavbarSystemAdmin/>
    <div class="container">
      <div class="container-child-1">
        <b-form>
            <label class="labelBox"><b>Name:</b></label>
            <b-form-input v-model="User.name" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>Surname:</b></label>
            <b-form-input v-model="User.surname" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>Email:</b></label>
            <b-form-input v-model="User.email" class="textBox" type="email" disabled></b-form-input>
            <label class="labelBox"><b>Phone number:</b></label>
            <b-form-input v-model="User.phone" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>State:</b></label>
            <b-form-input v-model="User.address.state" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>City:</b></label>
            <b-form-input v-model="User.address.city" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>Street:</b></label>
            <b-form-input v-model="User.address.street" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>Number of house:</b></label>
            <b-form-input v-model="User.address.number" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>Postal code:</b></label>
            <b-form-input v-model="User.address.postalCode" class="textBox" disabled></b-form-input>
        </b-form>
      </div>
  </div>
</div>
</template>

<script>

import NavbarSystemAdmin from '../components/NavbarSystemAdmin.vue'

export default {
    name: "ViewProfile",
    components: {
        NavbarSystemAdmin
    },
    computed: {
        User() {
            return this.$store.getters.getUser
        },
        getType() {
            return this.$store.getters.getType
        }
    }
}
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

  .labelBox {
    /* margin-top:20px; */
    margin-left: 15px;
  }

  .textBox {
    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
    transition: 0.25s;
    border-radius: 20px;
    margin-bottom: 20px;
  }

  .container {
        display: flex;
        flex-direction: row;
        min-width: 100%;
        min-height: 100%;
        justify-content:flex-center;
    }

  .container-1 {
        display: flex;
        flex-direction: row;
        min-width: 100%;
        min-height: 100%;
        justify-content:flex-center;
    }

    .container-child {
        margin: 20px;
        /* flex: 2; */
    }

    .container-child-1 {
        margin: 20px;
        flex: 1;
    }

    .container-child-2 {
        margin: 20px;
        flex: 2;
    }

    .boxDatePicker {
        max-width: 30vw;
        box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
        text-align: center;
        transition: 0.25s;
        border-radius: 20px;
    }

    .button {
        width: 300px;
        transition: 0.25s;
        border-radius: 20px;
    }

    .boxTable {
        /* box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px; */
        border-style:hidden;
        border-radius: 20px;
    }

    .bottomLeft {
        border-bottom-left-radius: 20px;
    }

    .bottomRight {
        border-bottom-right-radius: 20px;
    }

    .topRight {
        border-top-right-radius: 20px;
    }

    .topLeft {
        border-top-left-radius: 20px;
    }

    .buttonColumn {
        width: 180px;
        
    }
</style>