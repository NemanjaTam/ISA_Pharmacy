<template>
  <!-- MODALNI DIJALOG ZA LEKOVE - BOJANA -->

  <div>
    <b-button @click="onshow(selected, prices)">Edit</b-button>
    <b-modal
      v-model="show"
      ref="modal"
      title="Edit price list for medication"
      hide-footer
    >
      <div>
        <label>Valid from:</label>
        <b-form-datepicker
          class="mb-2 datepicker_b"
          :min="minDatePicker_1"
          v-model="datepicker_1"
          :format="'dd-MM-yyyy'"
        ></b-form-datepicker>
      </div>

      <b-table
        :items="this.pricesList.medicationPrices"
        :fields="fields"
        responsive="sm"
      >
        <template #cell(new_price)="row">
          <b-input type="number" :min="1" v-model="row.item.price"></b-input>
        </template>
      </b-table>
      <b-button @click="editPriceList">OK</b-button>
    </b-modal>
  </div>
</template>

<script>
const moment = require("moment");
export default {
  name: "ModalPriceList",
  props: ["selected", "id", "type", "prices"],
  computed: {},
  data() {
    const now = new Date();
    const today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    const minDate = new Date(today);
    return {
      datepicker_1: new Date(),
      show: false,
      text: "",
      momentFormatted: "",
      pricesList: {
        medicationPrices: [
          {
            price: 0,
            id: "",
            medication: {
              id: "",
              code: "",
              contraindications: "",
              description: "",
              manufacturer: "",
              medicationForm: "",
              name: "",
              prescriptionRegime: "",
              ratings: [],
              recommendedIntake: "",
              structure: "",
              typeOfMedication: "",
            },
          },
        ],
        startTime: "",
        status: "",
      },
      newPriceList: [],
      fields: [
        {
          key: "medication.name",
          sortable: true,
          label: "Name",
        },
        {
          key: "price",
          label: "Price",
          sortable: true,
        },
        "new_price",
      ],
      minDatePicker_1: minDate,
    };
  },
  // this.$store.getters.getSelectedMedicineForEdit
  methods: {

    editPriceList(){
        this.momentFormatted = moment(this.datepicker_1).format();
         var new_string  =  this.momentFormatted.slice(0, 19) 
        var editPriceList = {
            id:this.selected.id,
            startTime: new_string,
            medicationPrices: this.pricesList.medicationPrices,
            status: this.selected.status,
            
        }
        console.log(this.momentFormatted);


        fetch(`http://localhost:9005/api/pricelist/edit/${this.id}`, {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          usertype: this.type,
        },
        method: "POST",
        body: JSON.stringify(editPriceList),
      })
        .then(function(response) {
          if (response.ok) {
              alert("Succesful!");
            return response.json();
          } else {
            return Promise.reject(response);
          }
        }).then((data)=> this.$store.dispatch("updatePriceListActive", data))
        .catch();

    },
    clearList() {
      var md = [
        {
          price: 0,
          id: "",
          medication: {
            id: "",
            code: "",
            contraindications: "",
            description: "",
            manufacturer: "",
            medicationForm: "",
            name: "",
            prescriptionRegime: "",
            ratings: [],
            recommendedIntake: "",
            structure: "",
            typeOfMedication: "",
          },
        },
      ];

      this.pricesList.id = " ";
      this.pricesList.startTime = " ";
      this.pricesList.status = "";
      this.pricesList.medicationPrices = md;
    },
    onshow(selected, prices) {
      this.show = true;
      this.clearList();
      this.pricesList.id = selected.id;
      console.log(this.pricesList.id);
      for (var i = 0; i < prices.length; i++) {
        var obj = {
          price: prices[i].price,
          id: prices[i].id,
          medication: {
            id: prices[i].medication.id,
            code: prices[i].medication.code,
            contraindications: prices[i].medication.contraindications,
            description: prices[i].medication.description,
            manufacturer: prices[i].medication.manufacturer,
            medicationForm: prices[i].medication.medicationForm,
            name: prices[i].medication.name,
            prescriptionRegime: prices[i].medication.prescriptionRegime,
            ratings: prices[i].medication.ratings,
            recommendedIntake: prices[i].medication.recommendedIntake,
            structure: prices[i].medication.structure,
            typeOfMedication: prices[i].medication.typeOfMedication,
          },
        };
        this.pricesList.medicationPrices.push(obj);
      }
      this.pricesList.medicationPrices.shift();
      this.pricesList.startTime = selected.startTime;
      this.pricesList.status = selected.status;
      console.log(this.datepicker_1);
    },
  },
};
</script>
<style scoped>
.error {
  color: rgb(255, 0, 0);
}
</style>
