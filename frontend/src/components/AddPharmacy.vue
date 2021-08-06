<template>
<div class = "background">  <NavbarForAdminAndSupplier/>
 <div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <form class="box">
                    <div class = "cls">
                        <h1 class = "title" style="color:#4d4d4d ">Add pharmacy</h1>
                    </div>
                    <b-form-input type="text" v-model="pharmacy.name" placeholder="enter pharmacy name" style="font-style:italic" required/> 
                    <b-form-input type="text" v-model="pharmacy.address.state" placeholder="enter pharmacy state" style="font-style:italic"  aria-describedby="input-live-help input-live-feedback" :state= validation trim required/> 
                    <b-form-input type="text" v-model="pharmacy.address.city" placeholder="enter pharmacy city" style="font-style:italic" required/> 
                    <b-form-input type="text" v-model="pharmacy.address.street" placeholder="enter pharmacy street" style="font-style:italic" required/>
                    <b-form-input type="text" v-model="pharmacy.address.number" placeholder="enter pharmacy number" style="font-style:italic" required/>
                    <b-form-input type="text" v-model="pharmacy.address.postalCode" placeholder="enter pharmacy postal code" style="font-style:italic" required/>

                    <input type="submit" v-on:click="onSubmit" style="color: white" name="" value="Add pharmacy">
                    
                </form>
            </div>
        </div>
    </div>
 </div>
</div>
</template>

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

<script>
import axios from 'axios'
import NavbarForAdminAndSupplier from "../components/NavbarForAdminAndSupplier.vue"

export default {
  name: "AddPharmacy",
  components: {
    NavbarForAdminAndSupplier
  },
  data() {
    return {
      pharmacy: {
       name: "",
       address: {
        state: '',
        city: '',
        street: '',
        number: '',
        postalCode: null
      }
      },
  
      show: true,
    };
},
  methods: {
    onSubmit(event) {
      event.preventDefault();
      axios.post("http://localhost:9005/api/pharmacy/add", this.pharmacy);
      this.$router.push({path:'pharmacies'})
    }


  },
  computed: {
      validation() {
       
      }
  }}
</script>