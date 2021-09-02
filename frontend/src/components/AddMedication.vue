<template>
<div class = "background">
  <NavbarSystemAdmin/>
 <div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <form class="box">
                    <div class = "cls">
                        <h1 class = "title" style="color:#4d4d4d ">Add medication</h1>
                    </div>
                    <b-form-input type="text" v-model="medication.name" placeholder="enter name" style="font-style:italic" required/> 
                    <b-form-input type="text" v-model="medication.code" placeholder="enter code" style="font-style:italic"  aria-describedby="input-live-help input-live-feedback" :state= validation trim required/> 
                    <b-form-input type="text" v-model="medication.structure" placeholder="enter medication structure" style="font-style:italic" required/>
                    <b-form-input type="text" v-model="medication.contraindications" placeholder="enter contraindications" style="font-style:italic" required/>
                    <b-form-input type="text" v-model="medication.recommendedIntake" placeholder="enter recommended intake" style="font-style:italic" required/>
                    <b-form-select size="sm" v-model="medication.medicationForm" placeholder="pick medication form" id="medicationForm" name="" style="font-style:italic" required>
                                <option value="PRASAK">POWDER</option>
                                <option value="KAPSULA">CAPSULE</option>
                                <option value="TABLETA">TABLET</option>
                                <option value="MAST">GREASE</option>
                                <option value="GEL">GEL</option>
                                <option value="RASTVOR">DILUTION</option>
                                <option value="SYRUP">SYRUP</option>
                    </b-form-select>
                    <b-form-select size="sm" v-model="medication.typeOfMedication" placeholder="pick type of medication" id="typeOfMedication" name="" style="font-style:italic" required>
                                <option value="ANTIPYRETIC">ANTIPYRETIC</option>
                                <option value="ANALGESIC">ANALGESIC</option>
                                <option value="ANTIBIOTIC">ANTIBIOTIC</option>
                                <option value="ANTISEPTIC">ANTISEPTIC</option>
                                <option value="ANTIDEPRESSANT">ANTIDEPRESSANT</option>
                                <option value="ANTIALERGIC">ANTIALERGIC</option>
                                <option value="ANTIPSYCHOTIC">ANTIPSYCHOTIC</option>
                                <option value="BARBITURATE">BARBITURATE</option>
                                <option value="HORMONE">HORMONE</option>
                                <option value="CONTRACEPTIVE">CONTRACEPTIVE</option>
                                <option value="PROBIOTIC">PROBIOTIC</option>
                                <option value="TRANQUILIZER">TRANQUILIZER</option>
                    </b-form-select> 
                    <b-form-input type="text" v-model="medication.manufacturer" placeholder="enter manufacturer" style="font-style:italic" required/>
                    <b-form-select size="sm" v-model="medication.replacementMedicationsIDs" multiple name="" style="font-style:italic" :options="options">
                               
                    </b-form-select>                    
                    <b-form-input type="text" v-model="medication.description" placeholder="enter description" style="font-style:italic" required/>
                    <b-form-select size="sm" v-model="medication.prescriptionRegime" id="prescriptionRegime" name="" style="font-style:italic" required>
                                <option value="NA_RECEPT">WITH RECEPT</option>
                                <option value="BEZ_RECEPTA">WITHOUT RECEPT</option>
                    </b-form-select> 
                    <input type="submit" v-on:click="onSubmit" style="color: white" name="" value="Add">
 
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
      medication: {
       name: "",
       code: "",
       typeOfMedication: "",
       structure: "",
       contraindications: "",
       recommendedIntake: "",
       medicationForm: "",
       manufacturer: "",
       replacementMedicationsIDs: [],
       description: "",
       prescriptionRegime: ""
      },
      options: [],

      show: true,
    };
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      if(!this.medication.name || !this.medication.code || !this.medication.typeOfMedication || !this.medication.structure || !this.medication.contraindications
      || !this.medication.recommendedIntake || !this.medication.medicationForm || !this.medication.manufacturer || !this.medication.description || !this.medication.prescriptionRegime) {
        alert("All fields are required!");
        return;
      }

      const medicationForSend = {
              name: this.medication.name,
              code: this.medication.code,
              typeOfMedication: this.medication.typeOfMedication,
              prescriptionRegime: this.medication.prescriptionRegime,
              structure: this.medication.structure,
              contraindications: this.medication.contraindications,
              recommendedIntake: this.medication.recommendedIntake,
              medicationForm: this.medication.medicationForm,
              manufacturer: this.medication.manufacturer,
              replacementMedicationsIDs: this.medication.replacementMedicationsIDs,
              description: this.medication.description
              };
      console.log(medicationForSend);
      axios.post("http://localhost:9005/api/medication/add", medicationForSend).then(res => {
        console.log(res);
         this.$store.dispatch('setMedications', res);
      });
      alert("Medication is added!")
      this.$router.push({path:'medications'})
     },
    
  }, 
  mounted: function() {
       axios.get("http://localhost:9005/api/medication/all").then((el)=>el.data).then(res => {
        console.log(res);
        this.options = res.map((el) => {
          return {
            value: el.id, 
            text: el.name,

          }
        });
      });
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