<template>
  
  <div class = "background">
    <NavBarAdmin/>
  <div class = "card"  >
  
    <p class="title">Add medication</p>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">   <!--forma ce se prikazati samo ukoliko je show = true -->
      <b-form-group  
        class = "foo"
        id="input-group-1"
        label-for="input-1"
        
      >
         <b-form-input 
          id="input-1"
          v-model= "medication.name"
          placeholder= "Enter medication name"
          style="font-style:italic"
          label-cols-lg="7"
          label-cols="4"
          required
        ></b-form-input> 
      </b-form-group>

      <b-form-group class = "foo2" id="input-group-2" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="medication.id"
          placeholder="Enter medication code"
          style="font-style:italic"
          :state= validation
          aria-describedby="input-live-help input-live-feedback"
          trim
          required
        ></b-form-input>
      </b-form-group>

    <b-form-group class = "foo2" id="input-group-2" label-for="input-2">
        <b-form-input
          id="input-2.2"
          v-model="medication.type"
          placeholder="Enter medication type"
          style="font-style:italic"
          required
        ></b-form-input>
      </b-form-group>

    <p class = "class"> <b> Specifications of medication: </b> </p>

  <b-form-group class = "foo2"  id="input-group-3" label-for="input-3">
        <b-form-input
          id="input-3"
          v-model="medication.contraindication"
          placeholder="Enter medication contraindication"
          style="font-style:italic"
          required
        ></b-form-input>
      </b-form-group>

  <b-form-group class = "foo2"  id="input-group-4" label-for="input-4">
        <b-form-input
          id="input-4"
          v-model="medication.composition"
          placeholder="Enter medication composition"
          style="font-style:italic"
          required
        ></b-form-input>
      </b-form-group>

  <b-form-group class = "foo2" id="input-group-5" label-for="input-5">
        <b-form-input
          id="input-5"
          v-model="medication.therapy"
          placeholder="Enter recommended daily intake of therapy"
          style="font-style:italic"
          required
        ></b-form-input>
      </b-form-group>

  <b-form-group class = "foo2" id="input-group-6" label-for="input-6">
        <b-form-input
          id="input-6"
          v-model="medication.replacement"
          placeholder="Enter replacement medication"
          style="font-style:italic"
         
        ></b-form-input>
      </b-form-group>

      <b-button class = "foo3" type="submit" variant="primary">Add</b-button>
      <b-button class = "foo4" type="reset" variant="danger">Cancel</b-button>
    </b-form>
  </div>
  </div>
</template>

<style scoped>
.class {
    margin-left: 70px;
    margin-top: 50px;
}
.background{
  background-image: url("medicine.jpg");
  background-size: cover;
  overflow: auto;
}
.card {

  box-shadow: 10px 4px 8px 0 rgba(0,0,0,0.2);
  transition: 15s;
  width: 50%;
  border-radius: 20px; 
  margin-top: 40px; 
  margin-bottom: 50px; 
  margin-right: 500px;
  margin-left: 500px;
  width: 570px;
}

.foo{
  padding-top: 5px;
  padding-left: 70px;
  padding-right: 90px;
     }
.foo2{
  padding-left: 70px;
  padding-right: 90px;
     }
.foo3 {
  margin-left: 330px;
  margin-top: 20px;
  margin-bottom: 20px;
}
.foo4 {
  margin-left: 10px;
  margin-top: 20px;
  margin-bottom: 20px;
}
.title{
  text-align: center;
  font-family: fantasy;
  font-size: 30px;
  margin-top: 10px;
}

</style>

<script>
import NavBarAdmin from '../components/NavBarAdmin'

export default {
  name: "AddMedication",
  components: {
    NavBarAdmin
  },
  data() {
    return {
      medication: {
       name: "",
       id: "",
       type: "",
       contraindication: "",
       composition: "",
       therapy: "",
       replacement: ""
      },
  
      show: true,
    };
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
     
    },
    onReset(event) {
      event.preventDefault();
      console.log("reset");
    }

    },

  
};
</script>