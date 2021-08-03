<template>
  <b-card no-body>
    <b-tabs card>
      <b-tab title="Pharmacists" active>
        <div>
          <b-table
            striped
            hover
            :items="pharmacists"
            :fields="fields"
          ></b-table>
        </div>
      </b-tab>
      <b-tab title="Dermatologist"> </b-tab>
    </b-tabs>
  </b-card>
</template>
<script>
export default {
  name: "SearchListFilter",
  computed: {
    User() {
      this.$store.getters.getUser;
    },
    userType() {
      return this.$store.getters.getUserType;
    },
    userId() {
      return this.$store.getters.getId;
    },
    Pharmacy() {
      return this.$store.getters.getPharmacy;
    },
  },
  data() {
    return {
      pharmacy: null,
      pharmacists: [
        {
          id: "",
          name: "",
          surname: "",
          email: "",
        },
      ],
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
          key: "pharmacy",
          label: "Pharmacy",
          sortable: true,
          // Variant applies to the whole column, including the header and footer
          variant: "danger",
        },
      ],
      search: "",
    };
  },
  methods: {
    getPharmacists(id) {
      const headers = { "Content-Type": "application/json" };
      fetch(`http://localhost:9005/api/pharmacist/getallpharmacists/${id}`, {
        headers,
      })
        .then((response) => response.json())
        .then((data) => (this.pharmacists = data));
    },
    getPharmacy(id) {
      const headers = { "Content-Type": "application/json" };
      return fetch(
        `http://localhost:9005/api/pharmacyAdministrator/getpharmacy/${id}`,
        {
          headers,
        }
      )
        .then((response) => response.json())
        .then((data) => ((this.pharmacy = data.id), console.log(data.id)));
    },
  },

  async created() {
    if (this.userType == "PHARMACY_ADMINISTRATOR") {
      await this.getPharmacy(this.userId);
      await this.getPharmacists(this.pharmacy);
    }
    // this.getPharmacists(this.pharmacy);
    console.log("pharmacy id:");
    console.log(this.pharmacy);
  },
};
</script>
