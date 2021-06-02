<template>
  <div class = "background">
      <TopMenuForDP/>
      <div class="container">
          <div class="row">
              <div class="col-md-6">
                  <div class="card">
                      <form onsubmit="event.preventDefault()" class="box">
                        <div class = "cls">
                            <h1 class = "title" style="color:#4d4d4d ">Edit your profile</h1>
                        </div>
                        <b-form-input type="text" placeholder="enter email" style="font-style:italic" required/>
                        <b-form-input type="text" placeholder="enter username" style="font-style:italic" required/>
                        <b-form-input type="password" placeholder="enter old password" style="font-style:italic" required/> 
                        <b-form-input type="password" placeholder="enter new password" style="font-style:italic"  aria-describedby="input-live-help input-live-feedback" :state= validation trim required/> 
                        <b-form-invalid-feedback id="input-live-feedback" style="font-style:italic">
                                Enter at least 7 characters. 
                        </b-form-invalid-feedback>
                        <b-form-input type="password" placeholder="confirm new password" style="font-style:italic" required/> 
                        <b-form-input type="text" placeholder="enter name" style="font-style:italic" required/>
                        <b-form-input type="text" placeholder="enter surname" style="font-style:italic" required/>
                        <b-form-input type="text" placeholder="enter phone" style="font-style:italic" required/>
                        <b-form-input type="text" placeholder="enter state" style="font-style:italic" required/>
                        <b-form-input type="text" placeholder="enter city" style="font-style:italic" required/>
                        <b-form-input type="text" placeholder="enter street" style="font-style:italic" required/>
                        <b-form-input type="text" placeholder="enter number of house/building" style="font-style:italic" required/>
                        <b-form-input type="text" placeholder="enter postal code" style="font-style:italic" required/>
                        <b-button>Update</b-button>
                      </form>
                  </div>
              </div>
          </div>
      </div>
  </div>
</template>

<script>

import TopMenuForDP from '../components/TopMenuForPD'

export default {
    name: "UpdateBasicInformation",
    components: {
        TopMenuForDP
    },
    computed: {
      validation() {
        // return this.user.password.length > 7 ? true : false
      }
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