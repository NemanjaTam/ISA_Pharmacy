<template>
  <div class="background">
    <navbar />
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <div class="card">
            <form onsubmit="event.preventDefault()" class="box">
              <div class="cls">
                <h1 class="title" style="color:#4d4d4d ">Log in</h1>
                <p class="text-muted">Please enter your email and password!</p>
              </div>
              <input
                type="text"
                v-model="Login.email"
                placeholder="email"
                style="font-style:italic"
                required
              />
              <input
                type="password"
                v-model="Login.password"
                placeholder="password"
                style="font-style:italic"
                required
              />
              <a class="forgot text-muted" href="#">Forgot password?</a>
              <br />
              <label v-if="show" style="color: red;">{{ message }}</label>
              <label v-else style="color: green;">{{ message }}</label>
              <br />
              <b-button v-on:click="login">Log in</b-button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Navbar from "../components/Navbar.vue";

export default {
  name: "LoginPage",
  components: {
    Navbar,
  },
  data() {
    return {
      Login: {
        email: "",
        password: "",
      },
      show: false,
      message: "",
    };
  },
  methods: {
    login() {
      axios
        .post("http://localhost:9005/api/user/login-user", this.Login)
        .then((r) => {
          var user = JSON.parse(JSON.stringify(r.data));
          if (user.id == -2) {
            this.show = true;
            this.message = "Email is not valid";
            return;
          } else if (user.id == -1) {
            this.show = true;
            this.message = "Password is not valid";
          } else {
            this.show = false;
            this.message = "";
            if (user.userType == "DERMATOLOGIST") {
              if (user.firstTimeLogging) {
                this.$store.dispatch("updateUser", user);
                this.$router.push({ name: "FirstTimeLogging" });
              } else {
                this.$store.dispatch("updateUser", user);
                this.$router.push({ name: "HomePage" });
              }
            } else if (user.userType == "PHARMACY_ADMINISTRATOR") {
              console.log("ADMIN");
              console.log(user);
              this.getPharmacyIdbyUserId(user.id);
             
              if (user.firstTimeLogging) {
                this.$store.dispatch("updateUser", user);
                this.$router.push({ name: "FirstTimeLogging" });
              } else {
                this.$store.dispatch("updateUser", user);
                localStorage.setItem("user", user);
                this.$router.push({ name: "PharmacyAdminPage" });
              }
            } 
            else if (user.userType == "SYSTEM_ADMINISTRATOR") {
              if (user.firstTimeLogging) {
                this.$store.dispatch("updateUser", user);
                this.$router.push({ name: "FirstTimeLogging" });
              } else {
                this.$store.dispatch("updateUser", user);
                this.$router.push({ name: "SystemAdminHome" });
              }
            } else if (user.userType == "PATIENT") {
              if (user.firstTimeLogging) {
                this.$store.dispatch("updateUser", user);
                this.$router.push({ name: "FirstTimeLogging" });
              } else {
                this.$store.dispatch("updateUser", user);
                this.$router.push({ name: "PatientHomePage" });
              }
            } 
            else {
              if (user.firstTimeLogging) {
                this.$store.dispatch("updateUser", user);
                this.$router.push({ name: "FirstTimeLogging" });
              } else {
                this.$store.dispatch("updateUser", user);
                this.$router.push({ name: "SupplierHome" });
              }
            } /*else {
              if (user.firstTimeLogging) {
                this.$store.dispatch("updateUser", user);
                this.$router.push({ name: "FirstTimeLogging" });
              } else {
                this.$store.dispatch("updateUser", user);
                this.$router.push({ name: "HomePage" });
              }
              // alert("Pharmacist profile is still under development!")
              return;
            } 
          }*/
          }
        });
    },

    getPharmacyIdbyUserId(id) {
      fetch(`http://localhost:9005/api/pharmacy/getpharmacyidbyuser/${id}`, {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        method: "GET",
      })
        .then(function(response) {
          if (response.ok) {
            return response.json();
          } else {
            return Promise.reject(response);
          }
        })
        .then((data) => ( this.$store.dispatch("updatePharmacy",data)))
        .catch(function(error) {
          console.warn(error);
        });
    },

    onReset(event) {
      event.preventDefault();
      console.log("reset");
      this.getPharmacyIdbyUserId();
    },
  },
};
</script>

<style scoped>
.cls {
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
