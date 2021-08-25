<template>
  <div>
    <b-form-select
      id="inline-form-custom-select-pref"
      v-model="filter"
      aria-describedby="input-live-help input-live-feedback"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="['CEKA_PONUDE', 'OBRADJENA']"
      :value="null"
    ></b-form-select>
    <div>
      <b-table
        :items="this.getPurchaseOrders"
        :filter="filter"
        :fields="fields"
        responsive="sm"
      >
        <template #cell(delete)="row">
          <b-button size="sm" @click="removeItem(row.item)" class="mr-2">
            Delete
          </b-button>
        </template>
        <template #cell(edit)="row">
             <ModalPurchaseOrder  :selected="row.item.offers" :id="Pharmacy" :user="userId" :purchaseOrderCreator="row.item.adminPharmacy.id" :purchaseOrder="row.item"
             :quantityMedication="row.item.quantityMedicationsPurchase"></ModalPurchaseOrder>
        </template>
        <div></div>
      </b-table>
    </div>
  </div>
</template>

<script>
import ModalPurchaseOrder from "../components/ModalPurchaseOrder.vue"
export default {
  name: "ListOfPurchaseOrders",
  components:{ModalPurchaseOrder},
  computed: {
    Pharmacy() {
      return this.$store.getters.getPharmacy;
    },
    userId() {
      return this.$store.getters.getId;
    },
    getPurchaseOrders() {
      return this.$store.getters.getPurchaseOrders;
    },
  },
  data() {
    return {
      fields: [
        {
          key: "startTime",
          sortable: true,
        },
        {
          key: "endTime",
          sortable: true,
        },
        {
          key: "purchaseOrderStatus",
          label: "Status",
          sortable: true,
        },
        {
          key: "adminPharmacy.name",
          label: "Name",
          sortable: true,
        },
        {
          key: "adminPharmacy.surname",
          label: "Surname",
          sortable: true,
        },
        "delete",
        "edit",
        "offer",
      ],
      filter: "",
    };
  },
  methods: {
    getAllOrders() {
      fetch(
        `http://localhost:9005/api/pharmacy/get-purchase-order/${this.Pharmacy}`,
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
        .then((data) => this.$store.dispatch("updatePurchaseOrders", data))
        .catch(function(error) {
          console.warn(error);
        });
    },

      removeItem(item) {
      var vm = this;
      fetch(
        `http://localhost:9005/api/pharmacy/delete-purchase-order/${this.Pharmacy}`,
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
            `http://localhost:9005/api/pharmacy/get-purchase-order/${vm.Pharmacy}`,
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
           .then((data) => this.$store.dispatch("updatePurchaseOrders", data))

        .catch(function(error) {
          console.warn(error);
        });

      // this.$store.dispatch("removeMedicine",item);
    },
  },
  mounted(){
      this.getAllOrders();
  }
};
</script>
<style scoped></style>
