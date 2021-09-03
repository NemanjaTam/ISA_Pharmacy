<template>
  <div>
    <NavbarSystemAdmin />
  <table
        id="dtBasicExample"
        class="table table-striped table-bordered table-sm"
        cellspacing="0"
        width="75%"
      >
        <thead>
          <tr>
            <th class="th-sm">Medicine</th>
            <th class="th-sm">Quantity</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="p in this.pairs"
            :key="p.id"
          >
            <td>{{ p.medication.name }}</td>
            <td>{{ p.quantity }}</td>
          </tr>
        </tbody>
      </table>
      <input v-model="price" placeholder="Price" type="number" id="numberbox">
    <b-form-datepicker v-model="date" locale="en" id="datepicker"></b-form-datepicker>
    <b-button variant="outline-primary" @click="saveOffer" id="saveButton">Make Offer</b-button>
  </div>
</template>

<script>
import axios from "axios";
import NavbarSystemAdmin from '../components/NavbarSystemAdmin.vue'
export default {
components: {
    NavbarSystemAdmin
},
data: () => ({
    pairs: [],
    price: null,
    date: null,

}),
methods: {
    saveOffer(){
        var saveObj = {id: 1, supplierId: this.$store.getters.getUser.id, purchaseOrderId: this.$route.params.id, offer: this.price, status: 'x', deliveryDeadline: this.date};
        if (this.price <= 0 || this.date == null || new Date(this.date) < new Date()){
            alert("Check if input values are valid!")
            return;
        }
        axios
        .post(`http://localhost:9005/api/supplier/make-offer`, saveObj)
        .then((response) => {
            alert("Successfully made an offer!");
            window.location.href=`http://localhost:8080/orders`
        })
    }
},
mounted(){
    console.log(this.$route.params.id)
    axios
    .get(`http://localhost:9005/api/supplier/pairs/${this.$route.params.id}`)
    .then((response) =>{
        this.pairs = response.data
        console.log(response.data)
    })
}
}
</script>

<style>

#numberbox, #datepicker{
    float:center; display:inline; 
}
#numberbox{
    margin-bottom: 10px;
}
#saveButton{
    margin: auto;
}
</style>