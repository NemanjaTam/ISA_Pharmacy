<template>
    <div class = "background">
      <div class="container">
          <div class="row">
              <div class="col-md-6">
                  <div class="card">
                      <form onsubmit="event.preventDefault()" class="box">
                        <div class = "cls">
                            <h1 class = "title" style="color:#4d4d4d ">Please change your password</h1>
                        </div>
                        <b-form-input v-model="password" type="password" placeholder="enter new password" style="font-style:italic" required/> 
                        <b-form-input v-model="passwordConfirm" type="password" placeholder="confirm new password" style="font-style:italic"  aria-describedby="input-live-help input-live-feedback" trim required/> 
                        <br>
                        <label v-if="this.show" style="color:red"><b>{{message}}</b></label>
                        <br>
                        <b-button v-on:click="changePassword">Change password</b-button>
                      </form>
                  </div>
              </div>
          </div>
      </div>
  </div>
</template>

<script>

import axios from 'axios'

export default {
    name: 'FirstTimeLogging',
    computed: {
        User() {
            return this.$store.getters.getUser
        }
    },
    data() {
        return {
            password: '',
            passwordConfirm: '',
            show: false,
            message: 'Passwords do not match'
        }
    },
    methods: {
        changePassword() {
            if(this.password == '') {
                this.show = true
            }
            else if(this.password != this.passwordConfirm) {
                this.show = true
            }
            else {
                this.show = false
                this.$store.dispatch('updatePassword', this.password)
                console.log(this.User)
                axios.post("http://localhost:9005/api/user/update-user", this.User)
                if(this.User.userType == "PHARMACY_ADMINISTRATOR"){
                    this.$router.push({name: 'PharmacyAdminPage'})
                }else{
                this.$router.push({name: 'HomePage'})}
            }
        }
    },
    mounted() {
        alert("This is the first time you loged in. Please update your password to continue.")
    }
    
}
</script>

<style scoped>
  .cls{
      margin-bottom: 40px;
  }
  .background {
      background-image: url("../assets/img/medicine.jpg");
      position: fixed; 
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
      margin-top: 100px;
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