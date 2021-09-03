<template>
  <div>
    <b-button size="sm" @click="onshow(selected)">Specification</b-button>

    <b-modal v-model="show" ref="modal" title="Specification" hide-footer>
      <div>
              <p v-if="errors.length">
    <b class="error">Please correct the following error(s):</b>
    <ul>
      <li v-for="error in errors" :key="error" class="error">{{ error }}</li>
    </ul>
  </p>
        <form  method="post" novalidate="true" action="https://vuejs.org/">
            <h6>Structure: {{medication.structure}}</h6>
            <h6>Contraindications: {{medication.contraindications}}</h6>
            <h6>Recommended intake: {{medication.recommendedIntake}}</h6>
        </form>
          <p>
  </p>
      </div>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "Modal2",
  props: ["selected","id"],
  computed: {
      medications () {
      console.log(this.$store.state.medications)
      return this.$store.state.medications
    }

  },
  data() {
    return {
      show: false,
      medication: "",
      errors:[],
    };
  },
  methods: {
    checkForm: function(e) {
        var vm = this;
      this.errors = [];

  

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
