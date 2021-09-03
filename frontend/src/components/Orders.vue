<template>
<div>
    <NavbarSystemAdmin />
  <table
        id="dtBasicExample"
        class="table table-striped table-bordered table-sm"
        cellspacing="0"
        width="100%"
      >
        <thead>
          <tr>
            <th class="th-sm">Id</th>
            <th class="th-sm">Pharmacy Admin</th>
            <th class="th-sm">Deadline</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="o in this.orders"
            :key="o.id"
            v-on:dblclick="handleRowDblClick(o)"
          >
            <td>{{ o.id }}</td>
            <td>{{ o.adminPharmacy.name + " " + o.adminPharmacy.surname}}</td>
            <td>{{ new Date(o.period.endTime).toLocaleString() }}</td>
          </tr>
        </tbody>
      </table>
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
    orders: []
}),
methods:{
handleRowDblClick(order){
    window.location.href=`http://localhost:8080/orders/${order.id}`
}
},
mounted(){
    axios
    .get('http://localhost:9005/api/supplier/orders-on-hold')
    .then((response) => {
        this.orders = response.data; 
        console.log(this.orders);
    })
}

}

</script>

<style>

</style>