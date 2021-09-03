<template>
  <div>
    <NavbarSystemAdmin />
    <input type="checkbox" id="acc" value="ACCEPTED" v-model="checkedOptions">
    <label for="acc">Accepted</label>
    <input type="checkbox" id="wfa" value="WAITING_FOR_APPROVAL" v-model="checkedOptions">
    <label for="wfa">Waiting for approval</label>
    <input type="checkbox" id="dec" value="DECLINED" v-model="checkedOptions">
    <label for="dec">Declined</label>
    <b-button variant="outline-primary" @click="applyFilter" id="applyFilter">Apply Filter</b-button>
    <br>
    <hr>
  <table
        id="dtBasicExample"
        class="table table-striped table-bordered table-sm"
        cellspacing="0"
        width="75%"
      >
        <thead>
          <tr>
            <th class="th-sm">Id</th>
            <th class="th-sm">Status</th>
            <th class="th-sm">Price</th>
            <th class="th-sm">Deadline</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="o in this.offers"
            :key="o.id"
            v-on:dblclick="handleRowDblClick(o)"
          >
            <td>{{ o.purchaseOrderId }}</td>
            <td>{{ o.status }}</td>
            <td>{{ o.offer }}</td>
            <td>{{ new Date(o.deliveryDeadline).toLocaleString() }}</td>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<script>
import axios from 'axios'
import NavbarSystemAdmin from '../components/NavbarSystemAdmin.vue'
export default {
  components: {
        NavbarSystemAdmin
    },
data: () => ({
    offers: [],
    checkedOptions: [],
}),
methods: {
    applyFilter(){
        console.log(this.checkedOptions);
        axios
        .post(`http://localhost:9005/api/supplier/filtered-offers/${this.$store.getters.getUser.id}`, this.checkedOptions)
        .then((response) => {
            this.offers = response.data
        })
    },
    handleRowDblClick(offer){
        window.location.href=`http://localhost:8080/edit-offer/${offer.purchaseOrderId}`
    }
},
mounted(){
    axios
    .get(`http://localhost:9005/api/supplier/all-offers/${this.$store.getters.getUser.id}`)
    .then((response) =>{
        this.offers = response.data
})
}
}
</script>

<style>
label{
    margin-right: 80px;
}
</style>