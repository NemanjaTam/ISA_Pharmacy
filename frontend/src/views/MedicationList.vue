<template>
  <div class="background-list-medss">
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
        <template #cell(delete)="row">
          <b-button size="sm" @click="removeItem(row.item)" class="mr-2">
            Delete
          </b-button>
          </template>
        
         <template #cell(edit)="row">
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
    userType() {
      return this.$store.getters.getUserType;
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

        "delete",
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
        `http://localhost:9005/api/pharmacy/is-medication-reserved/${this.Pharmacy}`,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            usertype: this.userType,
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
          console.log(data);
          if (!data) {
            return fetch(
              `http://localhost:9005/api/pharmacy/delete-medication/${vm.Pharmacy}`,
              {
                headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json",
                },
                method: "POST",
                body: JSON.stringify(item.id),
              }
            )  .then(function(response) {
          return response.json();
        });
          } else {
            alert("Medication reserved!");
          }
        })
      
        .then(function(data) {
          console.log(data);
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
<style scoped>
.background-list-medss{
  background-color: white;
}
</style>
