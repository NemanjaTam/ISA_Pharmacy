<template>
  <div>
    <div>
      <b-input v-model="filter" placeholder="Search"></b-input>
      <b-table
        striped
        hover

        :items="orders"
        :fields="fields"
      ></b-table>
    </div>
    <!-- <div>
      <b-input v-model="filter" placeholder="Search"></b-input>
      <b-table
        striped
        hover
        :filter="filter"
        :filter-included-fields="['name', 'surname', 'email', 'pharmacyName']"
        :items="pharmacists"
        :fields="fields"
      ></b-table>
    </div>
  </div> -->
</template>

<script>


export default {
   name: "PurchaseOrderList",

    computed: {
        User() {
            return this.$store.getters.getUser;
        },
        getPharmacy(){
            return this.$store.getters.getPharmacy;
        }
    },
    data() {
        return {
         orders:[],
          fields: [
        {
          key: "surname",
          sortable: true,
        },
        {
          key: "name",
          sortable: false,
          sortable: true,
        },
        {
          key: "email",
          label: "Email address",
          sortable: true,
        },
        {
          key: "pharmacyName",
          label: "Pharmacy",
          sortable: true,
          // Variant applies to the whole column, including the header and footer
          variant: "danger",
        },
        ,
      ],
        }
    },

    methods:{
        getAllOrders(){
            // /get-purchase-order/{id}
            fetch(
          `http://localhost:9005/api/pharmacy//get-purchase-order/${this.getPharmacy}`,
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
              alert("Purchase order made");
              return response.json();
            } else {
              return Promise.reject(response);
            }
          })
          .then((data) =>(this.order = data))
          .catch(function(error) {
            console.warn(error);
          });
        }
    }
}
</script>
