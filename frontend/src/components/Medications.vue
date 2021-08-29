<template>
 <div class = "background">
 <div class="container">
    <div class="row" id="ads" v-if="medications">
 Category Card 
		 <div class="col-md-4" v-for="fp in medications" :key="fp.id">
		<div class="card rounded">
		<div class="card-image">
		<img class="img-fluid" src="assets/resources/pharmacy.png" alt="Alternate Text" />
		</div>
		<div class="card-image-overlay m-auto">
		<span>{{fp.notes}}</span>
        		</div>

		<div class="card-body text-center">
		<h5>{{fp.name}}</h5>
		<button class="btn btn-danger"><a class="moreLink" style="color:white">Click
		for more</a></button>
</div>
</div>
</div>


</div>
 </div>
</div>
</template>

<style scoped>
body {
font-family: 'Montserrat', sans-serif;
}
#ads {
margin: 30px 0 30px 0;
}
#ads .card-detail-badge {
background: #99d6ff;
text-align: center;
border-radius: 30px 30px 30px 30px;
color: #000;
padding: 5px 10px;
font-size: 14px;
}
#ads .card:hover {
background: #fff;
box-shadow: 12px 15px 20px 0px rgba(46,61,73,0.15);
border-radius: 4px;
transition: all 0.3s ease;
}
#ads .card-image-overlay {
font-size: 20px;
}
#ads .card-image-overlay span {
display: inline-block;
}
#ads .ad-btn:hover {
background-color: #ccebff;
color: #1e1717;
border: 2px solid #ccebff;
background: transparent;
transition: all 0.3s ease;
box-shadow: 12px 15px 20px 0px rgba(46,61,73,0.15);
}
#ads .ad-title h5 {
text-transform: uppercase;
font-size: 18px;
}
.moreLink {
color: black;
}
.dropdown-item {
background-color: rgba(46,61,73,0.5);
color: white;
cursor: pointer;
}
#dropdownMenuButton {
background-color: black;
color: white;
}
/*
.cls{
  margin-bottom: 40px;
}
 .background {
  background-image: url("../assets/img/medicine.jpg");
  position: cover; 
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
    margin-top: 50%;
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
} */
/* 
@import url("https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800%7CRaleway:100,200,300,400,600,700,800,900%7CMontserrat:100,200,300,400,500,600,700,800,900");
.box-shadow {
  box-shadow: 0px 0px 18px -3px rgba(143, 143, 143, 0.5);
	@@ -321,12 +369,26 @@ h6 {
.card-animate .card-title {
  font-weight: 900;
  text-transform: uppercase;
} */
</style>


<script>
import axios from 'axios';
export default {
  name: "Medications",
  computed: {
    medications () {
      return this.$store.state.medications
    }
  },
  mounted: function() {
	  axios.get("http://localhost:9005/api/medication/all").then((el)=>el.data).then((elem)=>{
		  console.log(elem);
		  this.$store.dispatch('setMedications', elem);
	  })
  }
};
</script>