<template>
  <div>
    <b-button size="sm" @click="onshow(selected)">Edit</b-button>
<!-- @ok="handleOk" :beforeClose=false -->
    <b-modal v-model="show" ref="modal" title="Edit" >
      <div>
     

        {{this.medication.name + " " + "is name"}}
         <b-form-input
                id="input-live"
                v-model="changedMedication.name"
                trim
              ></b-form-input> 
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
  props: ["selected"],
  computed:{
 
  },
  data() {
    return {
      show: false,
      medication: "",
      changedMedication:{
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
    };
  },
  // this.$store.getters.getSelectedMedicineForEdit
  methods:{
    onshow(selected){
      this.show = true;
      this.medication = selected;
      this.changedMedication.id = selected.id;
       this.changedMedication.name = selected.name;
        this.changedMedication.id = selected.id;
         this.changedMedication.id = selected.id;
        
  }

  }
};
</script>
