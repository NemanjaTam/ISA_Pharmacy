<template>
  <div class = "background">
        <NavbarForAdminAndSupplier/>
      <div class="container">
          <div class="row">
              <div class="col-md-6">
                  <div class="card">
                      <form onsubmit="event.preventDefault()" class="box">
                        <div class = "cls">
                            <h1 class = "title" style="color:#4d4d4d ">Edit your profile</h1>
                        </div>
                        <b-form-input v-model="user.email" type="text" placeholder="enter email" style="font-style:italic" v-on:input="checkIsEmailValid" required trim/>
                        <b-form v-if="!emailValid" style="color:red">
                            {{this.emailMessage}}
                        </b-form >
                        <b-form v-else style="color:green">
                            Looks Good.
                        </b-form>                        
                        <b-form-input v-model="oldPassword" type="password" placeholder="enter old password" style="font-style:italic" required trim/> 
                        <b-form-input v-model="newPassword" type="password" placeholder="enter new password" style="font-style:italic"  aria-describedby="input-live-help input-live-feedback" trim/> 
                        <b-form-input v-model="confirmPassword" type="password" placeholder="confirm new password" style="font-style:italic" required trim/> 
                        <b-form-input v-model="user.name" type="text" placeholder="enter name" style="font-style:italic" required trim/>
                        <b-form-input v-model="user.surname" type="text" placeholder="enter surname" style="font-style:italic" required trim/>
                        <b-form-input v-model="user.phone" type="text" placeholder="enter phone" style="font-style:italic" required trim/>
                        <b-form-input v-model="user.address.state" type="text" placeholder="enter state" style="font-style:italic" required trim/>
                        <b-form-input v-model="user.address.city" type="text" placeholder="enter city" style="font-style:italic" required trim/>
                        <b-form-input v-model="user.address.street" type="text" placeholder="enter street" style="font-style:italic" required trim/>
                        <b-form-input v-model="user.address.number" type="text" placeholder="enter number of house/building" style="font-style:italic" required trim/>
                        <b-form-input v-model="user.address.postalCode" type="text" placeholder="enter postal code" style="font-style:italic" required trim/>
                        <b-form v-if="!formValid" style="color:red">
                                {{this.formMessage}}
                        </b-form >
                        <b-button v-on:click="updateProfile">Update</b-button>
                      </form>
                  </div>
              </div>
          </div>
      </div>
  </div>
</template>

<script>

import axios from 'axios'
import NavbarForAdminAndSupplier from '../components/NavbarForAdminAndSupplier.vue'

export default {
    name: "EditProfileForAdminAndSupplier",
    components: {
        NavbarForAdminAndSupplier
    },
    computed: {
        User() {
            return this.$store.getters.getUser
        }
    },
    data() {
        return {
            user: {
                id: null,
                name: '',
                surname: '',
                email: '',
                password: '',
                phone: '',
                address: {
                    state: '',
                    city: '',
                    street: '',
                    number: '',
                    postalCode: ''
                },
                userType: null,
                firstTimeLogging: null,
                inbox: [],
                send: []
            },
            oldPassword: '',
            newPassword: '',
            confirmPassword: '',
            emailValid: false,
            emailMessage: "",
            formValid: false,
            formMessage: ""
        }
    },
    methods: {
        checkIsEmailValid() {
            if(this.validEmail(this.user.email)) {
                axios.get("http://localhost:9005/api/user/check-email/" + this.user.email)
                .then(r => {
                if(r.data == "not_taken") {
                    this.emailValid = true;
                }
                else if(this.user.email == this.User.email) {
                    this.emailValid = true;
                }
                else {
                    this.emailValid = false;
                    this.emailMessage = "This email is already taken.";
                }
                })
            }
            else {
                this.emailValid = false;
                this.emailMessage = "Email is not valid!";
            }
        },
        validEmail (email) {
            var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        },
        updateProfile() {
            if(this.check()) {
                this.formValid = false;
                this.formMessage = "Check all input fields";
                return
            }
            if(this.oldPassword != '') {
                if(this.oldPassword != this.user.password) {
                    this.formValid = false;
                    this.formMessage = "Old password is not correct";
                    return;
                }
                if(this.newPassword != this.confirmPassword) {
                    this.formValid = false;
                    this.formMessage = "Passwords do not match";
                    return
                }
                this.user.password = this.newPassword
            }
            else {
                this.user.password = this.User.password
            }
            this.formValid = true
            console.log(this.user)
            this.$store.dispatch('updateUser', this.user)
            axios.post("http://localhost:9005/api/user/update-user", this.User)
            this.$router.push('HomePage')
        },
        check() {
            if(!this.emailValid) return true
            else if(this.user.email == "") return true
            else if(this.user.name == "") return true
            else if(this.user.email == "") return true
            else if(this.user.name == "") return true
            else if(this.user.surname == "") return true
            else if(this.user.phone == "") return true
            else if(this.user.address.state == "") return true
            else if(this.user.address.city == "") return true
            else if(this.user.address.street == "") return true
            else if(this.user.address.number == "") return true
            else if(this.user.address.postalCode == "") return true
            else return false
        }
    },
    created() {
        var user = this.$store.getters.getUser
        this.user.id = user.id
        this.user.email = user.email
        this.user.name = user.name
        this.user.surname = user.surname
        this.user.password = user.password
        this.user.phone = user.phone
        this.user.address.state = user.address.state
        this.user.address.city = user.address.city
        this.user.address.street = user.address.street
        this.user.address.number = user.address.number
        this.user.address.postalCode = user.address.postalCode
        this.user.userType = user.userType
        this.user.firstTimeLogging = user.firstTimeLogging
        this.user.send = user.send
        this.user.inbox = user.inbox
    }
}
</script>

<style scoped>
  .cls{
      margin-bottom: 40px;
  }
  .background {
      background-image: url("../assets/img/medicine.jpg");
      position: absolute; 
      top: 0; 
      left: 0; 
      min-width: 100%;
      min-height: 132%;
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
      background: linear-gradient(to right, #b92b27, #1565c0)
  }

  .card {
      margin-bottom: 20px;
      border: none;
  }

  .box {
      width: 450px;
      padding: 40px;
      position:absolute;
      top: 50%;
      left: 50%;
      background: #ffffff;
      box-shadow: 10px 4px 8px 0 rgba(0,0,0,0.2);
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
      transition: 0.25s
  }

  .box h1 {
      color: white;
      text-transform: uppercase;
      font-weight: 500
  }

  .box input[type="text"]:focus,
  .box input[type="password"]:focus {
      width: 300px;
      background-color:#ffffff;
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
      cursor: pointer
  }

  .box input[type="submit"]:hover {
      background: #595959;
      border: 2px solid #595959;
  }

  .forgot {
      text-decoration: underline
  }

</style>