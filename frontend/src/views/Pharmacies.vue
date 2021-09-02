<template>
 <div class = "background">
    <NavbarSystemAdmin/>
    <div>
      <b-table
        :items="this.getPharmacies"
        :filter="filter"
        :fields="fields"
        responsive="sm"
        ref="selectableTable"
        @row-selected="onRowSelected"
        @row-unselected="onRowUnselected"
        :filter-included-fields="[
          'name',
          'State'
        ]"
      >
        <div></div>
      </b-table>
    </div>
  </div>
</template>

<script>
import NavbarSystemAdmin from '../components/NavbarSystemAdmin';
import axios from 'axios';

export default {
  name: "Pharmacies",
  components: { 
    NavbarSystemAdmin
   },
  computed: {
      pharmacies () {
      console.log(this.$store.state.pharmacies)
      return this.$store.state.pharmacies
    },
    Pharmacy() {
      return this.$store.getters.getPharmacy;
    },
    getPharmacies() {
      return this.$store.getters.getPharmacies;
    }
  },
  data() {
    return {
      fields: [
        {
          key: "name",
          sortable: true,
        },
         {
          key: "address.state",
          label: "State",
          sortable: true,
        },
         {
          key: "address.city",
          label: "City",
          sortable: true,
        },
         {
          key: "address.postalCode",
          label: "Postal code",
          sortable: true,
        },
        {
          key: "address.street",
          label: "Street",
          sortable: true,
        },
         {
          key: "address.number",
          label: "Number of building",
          sortable: true,
        },
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
  },
  mounted: function() {
	  axios.get("http://localhost:9005/api/pharmacy/all").then((el)=>el.data).then((elem)=>{
		  console.log(elem);
		  this.$store.dispatch('setPharmacies', elem);
	  })
  }
};
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
</style>