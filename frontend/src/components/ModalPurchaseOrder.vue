<template>
  <!-- MODALNI DIJALOG ZA LEKOVE - BOJANA -->

  <div>
    <b-button size="sm" @click="onshow(selected)">Open</b-button>
    <b-modal v-model="show" ref="modal" title="Edit" hide-footer>
      <div>
        <p class="my-4" v-for="(offer, indexxx) in offerList" :key="indexxx">
          <b-card class="b-card-two">
            <b-card-text>Price : {{ offer.price }}</b-card-text>
            <b-card-text>Supplier: {{ offer.provider.name }}</b-card-text>

            <b-button
              @click="
                selectOffer(
                  purchaseOrder,
                  offer,
                  purchaseOrder.endTime,
                  id,
                  quantityMedication
                )
              "
              v-if="user === purchaseOrderCreator"
            >
              ACCEPT
            </b-button>
          </b-card>
        </p>
      </div>
    </b-modal>
  </div>
</template>

<script>
const moment = require("moment");
export default {
  name: "ModalPurchaseOrder",
  props: [
    "selected",
    "id",
    "user",
    "purchaseOrderCreator",
    "purchaseOrder",
    "quantityMedication",
  ],
  computed: {},
  data() {
    return {
      show: false,
      offerList: [],
      errors: [],
    };
  },
  // this.$store.getters.getSelectedMedicineForEdit
  methods: {
    onshow(selected) {
      this.show = true;
      this.offerList = selected; //ovde stizu offers
      console.log(this.purchaseOrderCreator);
      console.log(this.user);
    },

    selectOffer(purchaseOrder, offer, endTime, id, quantityMedication) {
      var today = new Date();
      var vm = this;
      var body = {
        purchaseOrderId: purchaseOrder.id,
        offerId: offer.id,
        pharmacyId: id,
        quantityMedicationPurchaseOrder: quantityMedication,
      };

      if (
        moment(endTime).isBefore(moment(String(today)).format("YYYY-MM-DD"))
      ) {
        fetch(`http://localhost:9005/api/pharmacy/accept-offer`, {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "POST",
          body: JSON.stringify(body),
        })
          .then(function(response) {
            if (response.ok) {
              return response.json();
            } else {
              return Promise.reject(response);
            }
          })
          .then(function(data) {
            vm.pharmacy_id = data;
            console.log(vm.pharmacy_id);
            return fetch(
              `http://localhost:9005/api/pharmacy/get-purchase-order/${vm.id}`,
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
      } else {
        alert("You are not allowed.");
      }
    },
  },
};
</script>
<style scoped>
.error {
  color: rgb(255, 0, 0);
}
</style>
