<template>
 <div>
      <b-table
        :items="this.Unnaproved"
        :fields="fields"
        responsive="sm"
      >
        <template #cell(delete)="row">
          <b-button size="sm" @click="removeItem(row.item)" class="mr-2">
            Delete
          </b-button>
        </template>
        <template #cell(offer)="row">
             <ModalPurchaseOrder  :selected="row.item.offers" :id="Pharmacy" :user="userId" :purchaseOrderCreator="row.item.adminPharmacy.id" :purchaseOrder="row.item"
             :quantityMedication="row.item.quantityMedicationsPurchase"></ModalPurchaseOrder>
        </template>
        <div></div>
      </b-table>
    </div>

</template>
    
<script>
export default {
    name:"Absences",
    computed:{
        Pharmacy(){
            return this.$store.getters.getPharmacy;
        },
        Unnaproved(){
            return this.$store.getters.getUnnaprovedAbsences;
        },
        Approved(){
             return this.$store.getters.getApprovedAbsences;
        }
    },
    data(){
        return{
            fields: [
        {
          key: "name",
          label:"Name",
          sortable: true,
        },
        {
          key: "surname",
          label:"Surname",
          sortable: true,
        },
        {
          key: "endTime",
          sortable: true,
        },
        
      ],
        }
    },
    methods:{
        getUnnaproved(){
            fetch(
        `http://localhost:9005/api/pharmacy/get-all-unapproved-absences/${this.Pharmacy}`,
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
        .then((data) => this.$store.dispatch("updateUnapprovedAbsences", data))
        .catch(function(error) {
          console.warn(error);
        });
        }

    },
    mounted(){
        this.getUnnaproved();
    }
}
</script>