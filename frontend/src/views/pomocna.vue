<template>
  <div> 
  <div class = "card" style=width:30rem;  >
  
    <p class="title">Sign up with us</p>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show" class = "foo2">   <!--forma ce se prikazati samo ukoliko je show = true -->
      <b-form-group  
        class = "foo"
        id="input-group-1"
        label-for="input-1"
      >
         <b-form-input 
          id="input-1"
          v-model="user.email"
          type="email"
          placeholder= "Enter email"
          style="font-style:italic"
          label-cols-lg="7"
          label-cols="4"
          required
        ></b-form-input> 
      </b-form-group>

      <b-form-group class = "foo2" id="input-group-2" label-for="input-2">
        <b-form-input
          id="input-2"
          type = "password"
          v-model="user.password"
          placeholder="Enter password"
          style="font-style:italic"
          :state= validation
          aria-describedby="input-live-help input-live-feedback"
          trim
          required
        ></b-form-input>
        <b-form-invalid-feedback id="input-live-feedback">
      Enter at least 7 characters.
    </b-form-invalid-feedback>
      </b-form-group>

    <b-form-group class = "foo2" id="input-group-2" label-for="input-2">
        <b-form-input
          id="input-2.2"
          type = "password" 
          v-model="user.confirmPassword"
          placeholder="Confirm password"
          style="font-style:italic"
          required
        ></b-form-input>
      </b-form-group>

  <b-form-group class = "foo2"  id="input-group-3" label-for="input-3">
        <b-form-input
          id="input-3"
          v-model="user.name"
          placeholder="Enter name"
          style="font-style:italic"
          required
        ></b-form-input>
      </b-form-group>

  <b-form-group class = "foo2"  id="input-group-4" label-for="input-4">
        <b-form-input
          id="input-4"
          v-model="user.surname"
          placeholder="Enter surname"
          style="font-style:italic"
          required
        ></b-form-input>
      </b-form-group>

  <b-form-group class = "foo2" id="input-group-5" label-for="input-5">
        <b-form-input
          id="input-5"
          v-model="user.address.state"
          placeholder="Enter state"
          style="font-style:italic"
          required
        ></b-form-input>
      </b-form-group>

  <b-form-group class = "foo2" id="input-group-6" label-for="input-6">
        <b-form-input
          id="input-6"
          v-model="user.address.city"
          placeholder="Enter city"
          style="font-style:italic"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group class = "foo2"  id="input-group-7" label-for="input-7">
        <b-form-input
          id="input-7"
          v-model="user.address.postalCode"
          placeholder="Enter postal code"
          style="font-style:italic"
          required
        ></b-form-input>
      </b-form-group>

  <b-form-group class = "foo2" id="input-group-8" label-for="input-8">
        <b-form-input
          id="input-8"
          v-model="user.address.street"
          placeholder="Enter street"
          style="font-style:italic"
          required
        ></b-form-input>
   </b-form-group>

 <b-form-group class = "foo2"  id="input-group-9" label-for="input-9">
        <b-form-input
          id="input-9"
          v-model="user.address.number"
          placeholder="Enter number of house/building"
          style="font-style:italic"
          required
        ></b-form-input>
   </b-form-group>

   <b-form-group class = "foo2"  id="input-group-10" label-for="input-10">
        <b-form-input
          id="input-10"
          v-model="user.phone"
          placeholder="Enter phone number"
          style="font-style:italic"
          required
        ></b-form-input>
      </b-form-group>

      <b-button class = "foo3" type="submit" variant="primary">Register</b-button>
      <b-button class = "foo4" type="reset" variant="danger">Cancel</b-button>
    </b-form>
  </div>
  </div>
</template>

<style scoped>
.card {
  box-shadow: 10px 4px 8px 0 rgba(0,0,0,0.2);
  transition: 15s;
  width: 30%;
  border-radius: 20px; 
  margin-top: 40px; 
  margin-bottom: 50px; 
  margin-right: 400px;
  margin-left: 520px;
  width: 570px;
}

.foo{
  padding-top: 5px;
  padding-left: 30px;
  padding-right: 30px;
     }
.foo2{
  padding-right: 30px;
  padding-left: 30px;
     }
.foo3 {
  margin-left: 130px;
  margin-top: 20px;
  margin-bottom: 20px;
}
.foo4 {
  margin-left: 10px;
  margin-top: 20px;
  margin-bottom: 20px;
}
.title{
  text-align: center;
  font-family: fantasy;
  font-size: 30px;
  margin-top: 10px;
}

</style>

<script>
import axios from 'axios'

export default {
  name: "RegistrationPage",
  data() {
    return {
      user: {
       email: "",
       password: "",
       confirmPassword: "",
       name: "",
       surname: "",
       usertype: "PATIENT",
       address: {
            state: "",
            city: "",
            postalCode: "",
            street: "",
            number: ""
       },
       isFirstTimeLogging: false
      },
  
      show: true,
    };
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      if(this.user.password !== this.user.confirmPassword)
      {
        alert("Passwords don't match!");
        return;
      }
      axios.post("http://localhost:9005/api/user/register", this.user);

    },
    onReset(event) {
      event.preventDefault();
      console.log("reset");
    }


  },
  computed: {
      validation() {
        return this.user.password.length > 7 ? true : false
      }
    } 
    /*mounted() {
    axios.get("http://localhost:9005/api/user/all").then((response)=>{
      console.log(response);
      return response.json();
    }).then((responseJSON)=>{
      console.log(responseJSON);
    }) 
  }*/

 
};
</script>