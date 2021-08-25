<template>
  <div>
    <b-button size="sm" @click="onshow(selected)">Edit</b-button>
    <!--  :beforeClose=false -->

    <b-modal v-model="show" ref="modal" title="Edit" hide-footer>
      <div>
              <p v-if="errors.length">
    <b class="error">Please correct the following error(s):</b>
    <ul>
      <li v-for="error in errors" :key="error" class="error">{{ error }}</li>
    </ul>
  </p>
        <form  method="post" novalidate="true" action="https://vuejs.org/">
          <label>Name</label>
          <b-form-input
            id="input-live"
            v-model="changedMedication.name"
            placeholder="Enter name"
            type="text"
            trim
          ></b-form-input>
          <label>Description</label>
          <b-form-input
            id="input-live"
            v-model="changedMedication.description"
            placeholder="Enter description"
            trim
          ></b-form-input>
          <label>Manufacturer</label>
          <b-form-input
            id="input-live"
            v-model="changedMedication.manufacturer"
            placeholder="Enter manufacturer"
            text
            trim
          ></b-form-input>
          <label>Code</label>
          <b-form-input
            id="input-live"
            v-model="changedMedication.code"
            placeholder="Enter code"
            trim
          ></b-form-input>
          <label>Contraindications</label>
          <b-form-input
            id="input-live"
            v-model="changedMedication.contraindications"
            placeholder="Enter contraindications"
            trim
          ></b-form-input>
          <label> Medication Form:</label>
          <b-form-select
            id="inline-form-custom-select-pref"
            v-model="changedMedication.medicationForm"
            aria-describedby="input-live-help input-live-feedback"
            class="mb-2 mr-sm-2 mb-sm-0"
            :options="[
              'PRASAK',
              'KAPSULA',
              'TABLETA',
              'MAST',
              'GEL',
              'RASTVOR',
              'SIRUP',
            ]"
            :value="null"
          ></b-form-select>
          <label> Type of medication:</label>
          <b-form-select
            id="inline-form-custom-select-pref"
            v-model="changedMedication.typeOfMedication"
            class="mb-2 mr-sm-2 mb-sm-0"
            :options="[
              'ANTIPYRETIC',
              'ANALGESIC',
              'ANTIBIOTIC',
              'ANTISEPTIC',
              'ANTIDEPRESSANT',
              'ANTIALERGIC',
              'BARBITURATE',
              'HORMONE',
              'CONTRACEPTIVE',
              'PROBIOTIC',
              'TRANQUILIZER',
            ]"
            :value="null"
          ></b-form-select>
          <label> Presciption regime:</label>
          <b-form-select
            id="inline-form-custom-select-pref"
            v-model="changedMedication.prescriptionRegime"
            class="mb-2 mr-sm-2 mb-sm-0"
            :options="['NA_RECEPT', 'BEZ_RECEPTA']"
            :value="null"
          ></b-form-select>
        </form>
          <p>
    <input type="submit" value="Submit" @click="checkForm">  
  </p>
      </div>
      <!-- <label for="input-live">Name:</label>
              <b-form-input
                id="input-live"
                v-model="item.name"
                aria-describedby="input-live-help input-live-feedback"
                :state="nameState"
                placeholder="Enter your name"
                trim
              ></b-form-input> -->
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "Modal",
  props: ["selected","id"],
  computed: {},
  data() {
    return {
      show: false,
      medication: "",
      changedMedication: {
        id: 0,
        name: "",
        description: "",
        manufacturer: "",
        medicationForm: "",
        prescriptionRegime: "",
        code: "",
        typeOfMedication: "",
        structure: "",
        contraindications: "",
        recommendedIntake: "",
        ratings: [],
        quantity: 0,
       
      },
      errors:[],
    };
  },
  // this.$store.getters.getSelectedMedicineForEdit
  methods: {
    editMedicine(){
     
      if(this.checkForm){
        console.log("uslo u if");
           
      }else{
        alert("Please fill in empty fields");
      }
    },
    checkForm: function(e) {
        var vm = this;
      this.errors = [];
      console.log("POZVANO")
  
      if (!this.changedMedication.name ) {
        this.errors.push("Name required.");
      }
      if (!this.changedMedication.manufacturer) {
        this.errors.push("Manufacturer required.");
      }

      if (!this.errors.length) {
       fetch(
        `http://localhost:9005/api/pharmacy/edit-medication/${vm.id}`,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
            method: "POST",
           body: JSON.stringify(vm.changedMedication),
        }
      )
        .then(function(response) {
          if (response.ok) {
            alert("Successful!");
            return response.json();
            
          } else {
            return Promise.reject(response);
          }
        }).then(function(data) {
          if(data){
          return fetch(
            `http://localhost:9005/api/pharmacy/get-medications/${vm.id}`,
            {
              headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
              },
              method: "GET",
            }
          );
        }}).then(function(response) {
          return response.json();
        })
        .then((data) => this.$store.dispatch("updateMedicines", data))
        
        .catch(function(error) {
          console.warn(error);
        });
      }

    },

    onshow(selected) {
      this.show = true;
      this.medication = selected;
      this.changedMedication.name = selected.name;
      this.changedMedication.description = selected.description;
      this.changedMedication.manufacturer = selected.manufacturer;
      this.changedMedication.code = selected.code;
      this.changedMedication.contraindications = selected.contraindications;
      this.changedMedication.medicationForm = selected.medicationForm;
      this.changedMedication.typeOfMedication = selected.typeOfMedication;
      this.changedMedication.prescriptionRegime = selected.prescriptionRegime;
      this.changedMedication.id = selected.id;
      this.changedMedication.ratings = selected.ratings;
      this.changedMedication.recommendedIntake = selected.recommendedIntake;
      this.changedMedication.quantity = selected.quantity;
       
    },
  },
};
</script>
<style scoped>
.error{
 color: rgb(255, 0, 0);
}
</style>
