<template>
  <div>
    <div
      v-for="(listItem, index) in this.getPurchaseOrders"
      class="my-2 field"
      :key="index"
    >
      <b-card>
        <b-card-text>
          <label>STATUS: {{ listItem.purchaseOrderStatus }} </label>
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
          </label></b-card-text
        >
        <b-card-text
          v-for="(medication, indexx) in listItem.quantityMedicationsPurchase
            .medication"
          :key="indexx"
        >
          {{ medication.name }}
        </b-card-text>
        <b-button v-b-modal.modal-1>Offers</b-button>

        <b-modal id="modal-1" title="Offers:" class="modal" hide-footer v-on="setCurrentStatus(listItem.endTime,listItem.adminPharmacy.id)">
          <p
            class="my-4"
            v-for="(offer, indexxx) in listItem.offers"
            :key="indexxx"
          >
            <b-card>
              <b-card-text>Price : {{ offer.price }}</b-card-text>
              <b-card-text>Supplier: {{ offer.provider.name }}</b-card-text>
              <b-button @click="selectOffer(offer,listItem.endTime,listItem.adminPharmacy.id)" > SELECT</b-button>
            </b-card>
          </p>
        </b-modal>
        <!-- <a href="#" class="card-link">CANCEL</a>
    <b-link href="#" class="card-link">Another link</b-link> -->
      </b-card>
    </div>
  </div>
</template>

<script>
const moment= require('moment') 
export default {
    
  name: "PurchaseOrderList",

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
      
    };
  },

  methods: {
    setCurrentStatus(endTime,id){
        this.current_endTime = endTime;
        
        this.current_Id = id;
    },
    selectOffer(offer,endTime,id) {
     var today = new Date();
     console.log("pharmacy admin id :" + id)
     console.log(this.getUserId)
     console.log(endTime);
     console.log(moment(String(today)).format('YYYY-MM-DD'))
        
        if(moment(endTime).isBefore(moment(String(today)).format('YYYY-MM-DD')) && id===this.userId){
            this.$store.dispatch("updateSelectedOffer", offer);
           
        }else{ alert("You are not allowed.")}
       
    
      
    },
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
