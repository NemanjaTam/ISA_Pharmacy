<template>
  <div>
    <NavbarSystemAdmin/>
     <div class = "background">
  <div>
    <b-input v-model="filter" placeholder="Search"></b-input>
    <div>
      <b-table
        :items="this.getMedications"
        :filter="filter"
        :fields="fields"
        responsive="sm"
        ref="selectableTable"
        @row-selected="onRowSelected"
        @row-unselected="onRowUnselected"
        :filter-included-fields="[
          'name',
          'description',
          'manufacturer',
          'typeOfMedication',
          'medicationForm',
        ]"
      >
        <template #cell(show_details)="row">
          <Modal2 :selected="row.item" :id="Pharmacy"></Modal2>
        </template>
        <div></div>
      </b-table>
    </div>
  </div>
</div>
</div>
</template>

<script>
import Modal2 from "../components/Modal2.vue";
import NavbarSystemAdmin from '../components/NavbarSystemAdmin';
import axios from 'axios';

export default {
  name: "Medications",
  components: { 
    Modal2,
    NavbarSystemAdmin
   },
  computed: {
      medications () {
      console.log(this.$store.state.medications)
      return this.$store.state.medications
    },
    Pharmacy() {
      return this.$store.getters.getPharmacy;
    },
    getMedicines() {
      return this.$store.getters.getMedicines;
    },
    getMedications() {
      return this.$store.getters.getMedications;
    },
    getSelectedMed() {
      return this.$store.getters.getSelectedMedicineForEdit;
    },
  },
  data() {
    return {
      fields: [
        {
          key: "name",
          sortable: true,
        },
        {
          key: "manufacturer",
          sortable: true,
        },
        {
          key: "description",
          sortable: true,
        },
        {
          key: "medicationForm",
          sortable: true,
        },
        {
          key: "typeOfMedication",
          sortable: true,
        },

        "show_details",

      ],
      search: "",
      filter: "",
      modalData: "",
      med: [{}],
      selectMode: "single",
      selected: [],
    };
  },
  methods: {
    onRowSelected(items) {
      console.log("klinkuto!!");
      this.selected = items; //u selected se nalazi
      this.$store.dispatch("updateSelectedMedicineForEdit", items[0]);
    },
    onRowUnselected() {
      this.selected = [];

      this.$store.dispatch("updateSelectedMedicineForEdit", null);
    },

    setItem(item) {
      this.med = item;
    },
    modalContextMenu(data) {
      this.modalData = data;
      this.modalShow = true;
    },
    getMedication() {
      //  /get-medications/{id}
      fetch(
        `http://localhost:9005/api/pharmacy/get-medications/${this.Pharmacy}`,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "GET",
        }
      )
        .then(function(response) {
          if (response.ok) {
            return response.json();
          } else {
            return Promise.reject(response);
          }
        })
        .then((data) => this.$store.dispatch("updateMedicines", data))
        .catch(function(error) {
          console.warn(error);
        });
    },
  },
  mounted: function() {
	  axios.get("http://localhost:9005/api/medication/all").then((el)=>el.data).then((elem)=>{
		  console.log(elem);
		  this.$store.dispatch('setMedications', elem);
	  })
  }
};
</script>