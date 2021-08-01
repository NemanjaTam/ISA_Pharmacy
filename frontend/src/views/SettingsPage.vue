<template>
<div>
    <p>
        hola todos
    </p>
  
</div>

</template>
<script>

import NavbarAdmin from '../components/NavbarAdmin.vue'
import UpdateProfile from '../components/NavbarAdmin.vue'
export default({
 name: "settingsPage",
 components:{

 }
  
})

</script>
<style scoped>
.background {
    background-image: url("../assets/img/medicine.jpg");
    position: absolute; 
    top: 0; 
    left: 0; 
    min-width: 100%;
    min-height: 100%;
  }

.info{
    background:white;
    border-radius: 25px;
    border: 5px solid #343a40;
    padding: 20px; 

}


* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Style the header */
.header {
  grid-area: header;
  padding: 0px;

  text-align: center;
  font-size: 15px;
}

/* The grid container */
.grid-container {
    background-image: url("../assets/img/medicine.jpg");
    position: absolute; 
    top: 0; 
    left: 0; 
    min-width: 100%;
    min-height: 100%;
  display: grid;
  grid-template-areas: 
    'header header header header header header' 
    'left middle middle middle middle right' 
    'footer footer footer footer footer footer';
  /* grid-column-gap: 10px; - if you want gap between the columns */
} 

.left,
.middle,
.right {
  padding: 25px;
  /*height: 500px; /* Should be removed. Only for demonstration */
}

/* Style the left column */
.left {
  grid-area: left;
}

/* Style the middle column */
.middle {
  grid-area: middle;
}

/* Style the right column */
.right {
  grid-area: right;
}

/* Style the footer */
.footer {
  grid-area: footer;

  padding: 10px;
  opacity:0.5;
  text-align: center;
}

/* Responsive layout - makes the three columns stack on top of each other instead of next to each other */
@media (max-width: 600px) {
  .grid-container  {
    grid-template-areas: 
      'header header header header header header' 
      'left left left left left left' 
      'middle middle middle middle middle middle' 
      'right right right right right right' 
      'footer footer footer footer footer footer';
  }
}



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

