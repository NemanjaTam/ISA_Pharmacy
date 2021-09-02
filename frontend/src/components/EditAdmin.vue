<template>

  <div class="background">
    <NavbarAdmin></NavbarAdmin>
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <div class="card">
            <form onsubmit="event.preventDefault()" class="box">
              <div class="cls">
                <h1 class="title" style="color:#4d4d4d ">Edit your profile</h1>
              </div>
              <b-form-input
                v-model="userData.email"
                type="text"
                placeholder="enter email"
                style="font-style:italic"
                v-on:input="checkIsEmailValid"
                required
                trim
              />
              <b-form v-if="!emailValid" style="color:red">
                {{ this.emailMessage }}
              </b-form>
              <b-form v-else style="color:green">
                Looks Good.
              </b-form>
              <b-form-input
                @change="checkIsEmailValid"
                v-model="oldPassword"
                type="password"
                placeholder="enter old password"
                style="font-style:italic"
                required
                trim
              />
              <b-form-input
                v-model="newPassword"
                @change="checkIfEmpty"
                type="password"
                placeholder="enter new password"
                style="font-style:italic"
                aria-describedby="input-live-help input-live-feedback"
                trim
              />
              <b-form-input
                v-model="confirmPassword"
             
                type="password"
                placeholder="confirm new password"
                style="font-style:italic"
                required
                trim
              />
              <b-form-input
                v-model="userData.name"
                type="text"
                placeholder="enter name"
                style="font-style:italic"
                required
                trim
              />
              <b-form-input
                v-model="userData.surname"
                type="text"
                placeholder="enter surname"
                style="font-style:italic"
                required
                trim
              />
              <b-form-input
                v-model="userData.phone"
                type="text"
                placeholder="enter phone"
                style="font-style:italic"
                required
                trim
              />
              <b-form-input
                v-model="userData.address.state"
                type="text"
                placeholder="enter state"
                style="font-style:italic"
                required
                trim
              />
              <b-form-input
                v-model="userData.address.city"
                type="text"
                placeholder="enter city"
                style="font-style:italic"
                required
                trim
              />
              <b-form-input
                v-model="userData.address.street"
                type="text"
                placeholder="enter street"
                style="font-style:italic"
                required
                trim
              />
              <b-form-input
                v-model="userData.address.number"
                type="text"
                placeholder="enter number of house/building"
                style="font-style:italic"
                required
                trim
              />
              <b-form-input
                v-model="userData.address.postalCode"
                type="text"
                placeholder="enter postal code"
                style="font-style:italic"
                required
                trim
              />
              <b-form v-if="!formValid" style="color:red">
                {{ this.formMessage }}
              </b-form>
              <b-button v-on:click="updateProfile">Update</b-button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import NavbarAdmin from "../components/NavbarAdmin.vue"

export default {
  name: "EditAdmin",
  components: {NavbarAdmin},
  computed: {
    User() {
      return this.$store.getters.getUser;
    },
    userType() {
      return this.$store.getters.getUserType;
    },
  },
  data() {
    return {
      userData: {
        id: null,
        name: "",
        surname: "",
        email: "",
        password: "",
        phone: "",
        address: {
          state: "",
          city: "",
          street: "",
          number: "",
          postalCode: "",
        },
        userType: null,
        firstTimeLogging: null,
        inbox: [],
        send: [],
      },
      oldPassword: "",
      newPassword: "",
      confirmPassword: "",
      emailValid: false,
      emailMessage: "",
      formValid: false,
      formMessage: "",
    };
  },
  methods: {
      checkIfEmpty(){
          if(this.newPassword =="" || this.confirmPassword == ""){
              
              return true;
          }
         return false;
      },
    checkIsEmailValid() {
      if (this.validEmail(this.userData.email)) {
        axios
          .get("http://localhost:9005/api/user/check-email/" + this.userData.email)
          .then((r) => {
            if (r.data == "not_taken") {
              this.emailValid = true;
            } else if (this.userData.email == this.User.email) {
              this.emailValid = true;
            } else {
              this.emailValid = false;
              this.emailMessage = "This email is already taken.";
            }
          });
      } else {
        this.emailValid = false;
        this.emailMessage = "Email is not valid!";
      }
    },
    validEmail(email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    updateProfile() {
      if (this.check()) {
        this.formValid = false;
        this.formMessage = "Check all input fields";
        return;
      }
      if (this.oldPassword != "") {
        if (this.oldPassword.trim() != this.userData.password) {
          this.formValid = false;
          this.formMessage = "Old password is not correct";
          return;
        }if(this.newPassword == "" && this.confirmPassword==""){
                this.formValid = true;
               this.userData.password = this.User.password;
               console.log(this.User.password);
               
        }
        if (this.newPassword != this.confirmPassword) {
          this.formValid = false;
          this.formMessage = "Passwords do not match";
          return;
        }else if(this.newPassword !="" && this.confirmPassword!="" && this.newPassword.trim() == this.confirmPassword.trim()){
        this.userDatar.password = this.newPassword;}
      } else {
        this.userData.password = this.User.password;
      }
      this.formValid = true;
      console.log(this.userData);
      this.$store.dispatch("updateUser", this.userData);
      axios.post("http://localhost:9005/api/user/update-user", this.User);
      
      
    },
    check() {
      if (!this.emailValid) return true;
      else if (this.userData.email == "") return true;
      else if (this.userData.name == "") return true;
      else if (this.userData.name == "") return true;
      else if (this.userData.surname == "") return true;
      else if (this.userData.phone == "") return true;
      else if (this.userData.address.state == "") return true;
      else if (this.userData.address.city == "") return true;
      else if (this.userData.address.street == "") return true;
      else if (this.userData.address.number == "") return true;
      else if (this.userData.address.postalCode == "") return true;
      else return false;
    },
  },
  created() {
    var user = this.$store.getters.getUser;
    this.userData.id = user.id;
    this.userData.email = user.email;
    this.userData.name = user.name;
    this.userData.surname = user.surname;
    this.userData.password = user.password;
    this.userData.phone = user.phone;
    this.userData.address.state = user.address.state;
    this.userData.address.city = user.address.city;
    this.userData.address.street = user.address.street;
    this.userData.address.number = user.address.number;
    this.userData.address.postalCode = user.address.postalCode;
    this.userData.userType = user.userType;
    this.userData.firstTimeLogging = user.firstTimeLogging;
    this.userData.send = user.send;
    this.userData.inbox = user.inbox;
  },

  mounted(){
    if(this.userType != "PHARMACY_ADMINISTRATOR"){
       this.$router.push("/LoginPage").catch(()=>{});
    }
  }
};
</script>

<style scoped>
.cls {
  margin-bottom: 40px;
}
.background {
  background-image: url("../assets/img/medicine.jpg");
  position: absolute;
  top: 0;
  left: 0;
  min-width: 100%;
  min-height: 100%;
}
.title {
  font-family: fantasy;
  font-size: 30px;
  color: black;
}
.body {
  margin: 0;
  padding: 0;
  font-family: sans-serif;
  background: linear-gradient(to right, #b92b27, #1565c0);
}

.card {
  margin-bottom: 20px;
  border: none;
}

.box {
  width: 450px;
  padding: 40px;
  position: absolute;
  top: 50%;
  left: 50%;
  background: #ffffff;
  box-shadow: 10px 4px 8px 0 rgba(0, 0, 0, 0.2);
  text-align: center;
  transition: 0.25s;
  margin-top: 20px;
  border-radius: 20px;
}

.box input[type="text"],
.box input[type="password"] {
  border: 0;
  background: none;
  display: block;
  margin: 20px auto;
  text-align: center;
  border: 2px solid #3498db;
  padding: 10px 10px;
  width: 250px;
  outline: none;
  color: rgb(0, 0, 0);
  border-radius: 24px;
  transition: 0.25s;
}

.box h1 {
  color: white;
  text-transform: uppercase;
  font-weight: 500;
}

.box input[type="text"]:focus,
.box input[type="password"]:focus {
  width: 300px;
  background-color: #ffffff;
}

.box input[type="submit"] {
  border: 0;
  background: none;
  display: block;
  margin: 20px auto;
  text-align: center;
  border: 2px solid #7e7e7e;
  background-color: #7e7e7e;
  padding: 14px 40px;
  outline: none;
  color: rgb(0, 0, 0);
  border-radius: 24px;
  transition: 0.25s;
  cursor: pointer;
}

.box input[type="submit"]:hover {
  background: #595959;
  border: 2px solid #595959;
}

.forgot {
  text-decoration: underline;
}
</style>
