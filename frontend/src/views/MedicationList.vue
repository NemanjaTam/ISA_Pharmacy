<template>
  <div>
    <b-input v-model="filter" placeholder="Search"></b-input>
    <div>
      <b-table
        :items="this.getMedicines"
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
          <b-button size="sm" @click="removeItem(row.item)" class="mr-2">
            Delete
          </b-button>
          <Modal :selected="row.item" :id="Pharmacy"></Modal>
        </template>
        <div></div>
      </b-table>
    </div>
  </div>
</template>

<script>
import Modal from "../components/Modal.vue";

export default {
  name: "MedicationList",
  components: { Modal },
  computed: {
    Pharmacy() {
      return this.$store.getters.getPharmacy;
    },
    getMedicines() {
      return this.$store.getters.getMedicines;
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
        "edit",
      ],
      search: "",
      filter: "",
      modalData: "",
      med: {},
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

    removeItem(item) {
      var vm = this;
      fetch(
        `http://localhost:9005/api/pharmacy/delete-medication/${this.Pharmacy}`,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "POST",
          body: JSON.stringify(item.id),
        }
      )
        .then(function(response) {
          if (response.ok) {
            return response.json();
          } else {
            return Promise.reject(response);
          }
        })
        .then(function(data) {
          return fetch(
            `http://localhost:9005/api/pharmacy/get-medications/${vm.Pharmacy}`,
            {
              headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
              },
              method: "GET",
            }
          );
        })
        .then(function(response) {
          return response.json();
        })
        .then((data) => this.$store.dispatch("updateMedicines", data))

        .catch(function(error) {
          console.warn(error);
        });

      // this.$store.dispatch("removeMedicine",item);
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
  mounted() {
    this.getMedication();
  },
};
</script>
