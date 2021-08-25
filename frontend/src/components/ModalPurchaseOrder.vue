<template>
<!-- MODALNI DIJALOG ZA LEKOVE - BOJANA -->

  <div>
    <b-button size="sm" @click="onshow(selected)">Open</b-button>
    <b-modal v-model="show" ref="modal" title="Edit" hide-footer>
      <div>
        
      
   
    <input type="submit" value="Submit" @click="checkForm">  
 
      </div>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "ModalPurchaseOrder",
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