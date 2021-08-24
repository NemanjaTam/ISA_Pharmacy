<template>
  <div>
    
    <b-input v-model="filter" placeholder="Search"></b-input>

    <div>
      <b-table
        :items="this.getMedicines"
        :fields="fields"
        striped
        responsive="sm"
        hover
        :filter="filter"
        :filter-included-fields="[
          'name',
          'description',
          'manufacturer',
          'typeOfMedication',
          'medicationForm',
        ]"
      >
        <template #cell(show_details)="row">
          <b-button size="sm" @click="row.toggleDetails" class="mr-2">
            {{ row.detailsShowing ? "Hide" : "Show" }} Edit
          </b-button>
          
          <b-button size="sm" @click="removeItem(row.item)" class="mr-2">
            Delete
          </b-button>
          <Modal :item="row.item"></Modal>
          <!-- <modal
            v-if="modalVisible"
            @close="modalVisible = false"
            :data="modalData"
          /> -->

    
        </template>

        <template #row-details="row">
          <b-card>
            <b-row class="mb-2">
              <b-col sm="3" class="text-sm-right"><b>Age:</b></b-col>
              <b-col>{{ row.item.age }}</b-col>
            </b-row>

            <b-row class="mb-2">
              <b-col sm="3" class="text-sm-right"><b>Is Active:</b></b-col>
              <b-col>{{ row.item.isActive }}</b-col>
            </b-row>

            <b-button size="sm" @click="row.toggleDetails"
              >Hide Details</b-button
            >
          </b-card>
        </template>
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
    };
  },
  methods: {
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
