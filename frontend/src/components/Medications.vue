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
/*
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
*/
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
} 

@import url("https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800%7CRaleway:100,200,300,400,600,700,800,900%7CMontserrat:100,200,300,400,500,600,700,800,900");
.box-shadow {
  box-shadow: 0px 0px 18px -3px rgba(143, 143, 143, 0.5);
}

.mrg {
  margin-top: 50px;
}
.card:hover {
  box-shadow: 0px 0px 20px -3px rgba(143, 143, 143, 0.75);
}

.box-shadow-dark {
  box-shadow: 3px 3px 11px -3px rgba(0, 0, 0, 0.25);
}

body {
  background-color: yellow;
  font-family: "Open Sans", Arial, Verdana;
}

section {
  padding-top: 2rem;
  padding-bottom: 2rem;
}

h6 {
  color: #990000;
}

.font-weight-bolder {
  font-weight: 900;
}

.display-1,
.display-2,
.display-3,
.display-4 {
  font-weight: 900;
}

.heading-big {
  font-weight: 800;
  text-transform: uppercase;
  color: red;
  margin: 0;
  padding: 0.15rem 0;
  line-height: 1.2;
  font-size: 2rem;
  transition: color 0.5s ease-in, border-color 0.5s ease-in-out;
}
.heading-big-square {
  padding: 1rem 1rem;
  border: 10px solid red;
  margin: 0.5rem auto 1rem auto;
}
.heading-big:hover {
  color: #ff9900;
  border-color: #ff9900;
}

.card {
  transition: 0.5s ease;
  border-radius: 0;
  transform: perspective(3em) rotateX(0deg) rotateY(-1.175deg) rotateZ(-1deg);
}
.card-header {
  border-radius: 0;
}
.card-footer {
  padding-top: 1rem;
  padding-bottom: 1rem;
}
.card-header, .card-footer {
  transition: 0.5s ease;
}
.card:hover {
  border-color: #ffc107;
  transform: perspective(0em) rotateX(0deg) rotateY(0deg) rotateZ(0deg);
}
.card:hover .card-header,
.card:hover .card-footer {
  color: #ba8b00;
  border-color: #ffc107;
  background-color: #ffeeba;
}
.card:hover .promotion-promo {
  transform: scale(1.175) translateY(2.5px);
  transform-origin: left center;
}
.card:hover .promotion-price {
  transform: translate(2.5px, 17.5px) scale(1.15);
  transform-origin: center right;
}
.card-body {
  position: relative;
  z-index: 0;
  overflow: hidden;
  padding-top: 2rem;
  padding-bottom: 2rem;
}
.card .btn {
  font-weight: bold;
  text-transform: uppercase;
}

.promotion-promo {
  font-weight: 700;
  font-size: 1.15rem;
  color: #ffc107;
  font-family: "Montserrat", sans-serif;
  text-decoration: line-through;
  transition: 0.25s linear;
}
.promotion-price {
  position: absolute;
  bottom: 0;
  right: 0;
  background: #ffc107;
  width: 92px;
  height: 92px;
  padding-bottom: 0rem;
  padding-top: 1.25rem;
  text-align: center;
  font-weight: 700;
  font-family: "Montserrat", sans-serif;
  font-size: 1.1rem;
  border-radius: 5rem;
  color: #fff;
  z-index: -1;
  transform: translate(5px, 27.5px) rotate(-9deg);
  border: 1px dashed #fff;
  transition: 0.25s linear;
  line-height: 1.15;
}
.promotion-price-desc {
  padding: 0;
  margin: 0 auto;
  text-transform: uppercase;
  font-size: 0.9rem;
  display: block;
}
.promotion-price-text {
  padding: 0;
  margin: 0 auto;
  font-weight: 900;
}

.card-animate {
  counter-reset: section;
}
.card-animate .card-body:before {
  transition: 0.5s ease;
  counter-increment: section;
  content: "" counter(section) "";
  display: block;
  font-size: 15rem;
  font-weight: 900;
  position: absolute;
  bottom: 5rem;
  line-height: 0;
  left: -0.85rem;
  padding: 0;
  margin: 0;
  color: rgba(0, 0, 0, 0.1);
  z-index: 0;
}
.card-animate .card:hover .card-body:before {
  transform: translate(10px, -10px);
}
.card-animate .card-text {
  margin-top: 2rem;
  margin-bottom: 2rem;
}
.card-animate .card-title {
  font-weight: 900;
  text-transform: uppercase;
} 
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