<template>
<div class = "background">
  <NavbarSystemAdmin/>
 <div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <form class="box" @submit.prevent="addType">
                    <div class = "cls">
                        <h1 class = "title" style="color:#4d4d4d ">Add Loyalty</h1>
                    </div>
                    <b-form-input type="text" v-model="loyalty.naziv" placeholder="enter name" style="font-style:italic" required/> 
                    <b-form-input type="number" v-model="loyalty.bodovi" placeholder="enter number of points required (must be an integer)" style="font-style:italic"  min="0" step="1" required/> 
                    <b-form-input type="number" v-model="loyalty.popust" placeholder="enter discount percentage (must be between 0 and 100)" style="font-style:italic"  min="0" max="99" required/> 
                    <button type="submit" class="btn btn-primary">Submit</button>
 
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
      min-height: 140%;
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

<script>
import axios from 'axios'
import NavbarSystemAdmin from '../components/NavbarSystemAdmin'

export default {
  name: "AddMedication",
  components: {
        NavbarSystemAdmin
    },
  data() {
    return {
      loyalty: {
       bodovi:0,
       naziv:"",
       popust:0.0
      },
  
      show: true,
    };
  },
  methods: {
    addType() {
         console.log(parseInt(this.loyalty.bodovi) + " " + this.loyalty.naziv);
         axios.post("http://localhost:9005/api/loyaltyCategory/add", this.loyalty)
         .then(res => {
             console.log(res);
         })
         this.loyalty = {
              bodovi:0,
              naziv:"",
              popust:0.0
         }
         alert("Loyalty is successfully added!")
     }
    
  }, 
  computed: {
      validation() {
       // return this.user.password.length > 7 ? true : false
      },
      exsistingMedications() {
        //axios.get(medications).then(med=>{
//          return med;
    //    })
      }
  }}
</script>