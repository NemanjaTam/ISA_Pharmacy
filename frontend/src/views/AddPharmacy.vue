<template>
      <div class = "background">
          <NavBarAdmin/>
    <div class="card">
        <div class="card-content white-text">
          <p class="title">Add pharmacy</p>
          <b-form @submit="onSubmit" v-if="show">   <!--forma ce se prikazati samo ukoliko je show = true -->
      <b-form-group  
        class = "foo"
        id="input-group-1"
        label-for="input-1"
        
      >
         <b-form-input 
          id="input-1"
          v-model="pharmacy.name"
          type="text"
          placeholder= "Enter pharmacy name"
          style="font-style:italic"
          label-cols-lg="7"
          label-cols="4"
          required
        ></b-form-input> 
      </b-form-group>

      <b-form-group  
        class = "foo"
        id="input-group-1"
        label-for="input-1"
        
      >
         <b-form-input 
          id="input-1"
          v-model="pharmacy.address"
          type="text"
          placeholder= "Enter pharmacy address"
          style="font-style:italic"
          label-cols-lg="7"
          label-cols="4"
          required
        ></b-form-input> 
      </b-form-group>
          </b-form>
       <b-button  variant="primary" class = "foo3" type="submit" >Add</b-button>

  </div>
    </div>
  </div>
</template>

<style scoped>

.background{
  background-image: url("medicine.jpg");
  background-size: cover;
  overflow: auto;
}
.card {
  box-shadow: 10px 4px 8px 0 rgba(0, 0, 0, 0.2);
  transition: 15s;
  width: 50%;
  border-radius: 20px;
  margin-top: 40px;
  margin-bottom: 413px;
  margin-right: 500px;
  margin-left: 500px;
  width: 570px;
}

.foo{
  padding-top: 5px;
  padding-left: 70px;
  padding-right: 90px;
}
.foo3 {
  margin-left: 70px;
  margin-right: 200px;
  margin-top: 10px;
  margin-bottom: 20px;
  width:410px;
}

 .title{
  text-align: center;
  font-family: cursive;
  font-size: 25px;
  margin-top: 10px;
}    
</style>

<script>
import NavBarAdmin from "../components/NavBarAdmin";
export default {
  name: "AddPharmacy",
  components: {
    NavBarAdmin,
  },
  data() {
    return {
      pharmacy: {
        name: "",
        address: "",
      },
      show: true,
    };
  },
  methods:
  {
    onSubmit() {
      
      console.log("pera");
    }
  }
};
</script>
