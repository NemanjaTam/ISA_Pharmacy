<template>
  <div class="background-price-list">
      

      <b-table
        :items="this.getPriceListActive"
        :fields="fields"
        responsive="sm"
      >
                <template #cell(edit)="row">
                  <ModalPriceList :selected="row.item" :id="Pharmacy" :prices="row.item.medicationPrices" :type="userType"></ModalPriceList>
            
        </template>

        <div></div>
      </b-table>
  </div>
</template>
<script>
import ModalPriceList from "../components/ModalPriceList.vue"
export default {
  name: "PriceList",
  components: {ModalPriceList},
  computed: {
    userType() {
      return this.$store.getters.getUserType;
    },
    userId() {
      return this.$store.getters.getId;
    },
    Pharmacy() {
      return this.$store.getters.getPharmacy;
    },
    getPriceListActive(){
        return this.$store.getters.getPriceListActive;
    },
    PriceListInactive(){
        return this.$store.getters.getPriceListInactive;
    }
  },
  data() {
    return {
         fields: [
        {
          key: "startTime",
          sortable: true,
        },
        {
          key: "status",
          label: "Status",
          sortable: true,
        },
        "edit"

        
    ],
  }},
  methods:{
      getActivePriceList(){
        fetch(
            `http://localhost:9005/api/pricelist/get-active-pricelist/${this.Pharmacy}`,
            {
              headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
                usertype: this.userType,
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
        }).then((data)=> this.$store.dispatch("updatePriceListActive", data))
        .catch();
      }
  },
  mounted(){
        this.getActivePriceList();
  }
};
</script>
<style scoped>
.background-price-list {
  background-color: white;
}
</style>
