<template>
  <div>
    <div 
      v-for="(listItem, index) in this.getPurchaseOrders"
      class="my-2 field card"
      :key="index"
    >
      <b-card>
        <b-card-text>
       STATUS: {{ listItem.purchaseOrderStatus }}
        </b-card-text>

        <b-card-text
          ><label>FROM: {{ listItem.startTime }} </label></b-card-text
        >
        <b-card-text
          ><label>TO: {{ listItem.endTime }} </label></b-card-text
        >
        <b-card-text
          ><label
            >MADE BY:
            {{
              listItem.adminPharmacy.name + " " + listItem.adminPharmacy.surname
            }}
          </label></b-card-text>
      
      
        <b-card-text> Select offer</b-card-text>
        <!-- <div
            class="my-5"
            v-for="(quantity, indexx) in listItem.quantityMedicationsPurchase"
            :key="indexx"
          >
          <ul id="example-1">
  <li v-for="item in quantity" :key="item.id">
    {{ item.name }}
  </li>
</ul> -->

           <!-- </div> -->
        <div class="scrollable"  >
          <p
            class="my-4"
            v-for="(offer, indexxx) in listItem.offers"
            :key="indexxx"
          >
            <b-card class="b-card-two">
              <b-card-text>Price : {{ offer.price }}</b-card-text>
              <b-card-text>Supplier: {{ offer.provider.name }}</b-card-text>
              <b-button @click="selectOffer(listItem,offer,listItem.endTime,listItem.adminPharmacy.id)" > ACCEPT </b-button>
            </b-card>
          </p>

           
     
        </div>
      </b-card>
    </div>
  </div>
</template>

<script>
const moment = require("moment");
import Modal from "../components/Modal.vue";
export default {
  name: "PurchaseOrderList",
  components: { Modal },
  computed: {
    User() {
      return this.$store.getters.getUser;
    },
    getPharmacy() {
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
      orders: [],
      current_endTime: Date(),
      current_Id: 0,
      activeKey: null,
      medication:[],
      
    };
  },

  methods: {

    addMedication(medication){
      
    },
    setCurrentStatus(endTime, id) {
      this.current_endTime = endTime;

      this.current_Id = id;
    },
    selectOffer(purchaseOrder, offer, endTime, id) {
      var today = new Date();

      var body = {
        purchaseOrderId: purchaseOrder.id,
        offerId: offer.id,
        pharmacyId: this.getPharmacy,
      };
      console.log(id);
      console.log(this.userId)
      if (
        moment(endTime).isBefore(moment(String(today)).format("YYYY-MM-DD")) &&
        id === this.userId
      ) {
        
  fetch(
        `http://localhost:9005/api/pharmacy/accept-offer`,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "POST",
           body: JSON.stringify(body),
        }
      )
        .then(function(response) {
          if (response.ok) {
            return response.json();
          } else {
            return Promise.reject(response);
          }
        })
        
        .catch(function(error) {
          console.warn(error);
        });


      } else {
        alert("You are not allowed.");
      }
    },
    toggleModal() {
      this.isShowModal = !this.isShowModal;
    },
    // selectOffer(purchaseOrder,offer, endTime, id) {
    //   var today = new Date();
    //   var newOffer = {
    //     id: offer.id,
    //     price: offer.price,
    //     provider: {
    //       id: offer.provider.id,
    //       name: offer.provider.name,
    //       info: offer.provider.info,
    //       email: offer.provider.email,
    //       inbox: offer.provider.inbox,
    //       send: offer.provider.send,
    //       accepted: true,
    //     },
    //   };
    //   var body =  {
    //     purchaseOrderId: purchaseOrder.id,
    //     offer: offer,
    //     pharmacyId: this.getPharmacy,
    //   }

    //   if (
    //     moment(endTime).isBefore(moment(String(today)).format("YYYY-MM-DD")) &&
    //     id === this.userId
    //   ) {
    //     this.$store.dispatch("updateSelectedOffer", newOffer);
    //     this.$store.dispatch("updatePurchaseOrderOffer",newOffer);
    //   } else {
    //     alert("You are not allowed.");
    //   }
    //   // fetch(
    //   //   `http://localhost:9005/api/pharmacy/get-purchase-order/${this.getPharmacy}`,
    //   //   {
    //   //     headers: {
    //   //       Accept: "application/json",
    //   //       "Content-Type": "application/json",
    //   //     },
    //   //     method: "GET",
    //   //   }
    //   // )
    //   //   .then(function(response) {
    //   //     if (response.ok) {
    //   //       return response.json();
    //   //     } else {
    //   //       return Promise.reject(response);
    //   //     }
    //   //   })
    //   //   .then((data) => this.$store.dispatch("updatePurchaseOrders", data))
    //   //   .catch(function(error) {
    //   //     console.warn(error);
    //   //   });
    // },
    getAllOrders() {
      // /get-purchase-order/{id}
      fetch(
        `http://localhost:9005/api/pharmacy/get-purchase-order/${this.getPharmacy}`,
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
  },
  mounted() {
    this.getAllOrders();
  },
};
</script>

<style scoped>
.scrollable{
  border: 5px;
  max-height: 350px ;
  overflow-y: scroll;
}
.card{
  background-color: rgb(217, 242, 230);
  /* max-height: 700px; */
  border-style: solid;
  border-color: rgb(103, 203, 155);
}
.b-card-two{
  background-color: rgb(236, 248, 242);
  max-height:150px;
}
</style>
