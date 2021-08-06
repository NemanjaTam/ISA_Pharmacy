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
    userType() {
      return this.$store.getters.getUserType;
    },
    User() {
      return this.$store.getters.getUser;
    },
    isPharmacyAdmin() {
      return this.$store.getters.isPharmacyAdministrator;
    },
    isRegisteredUser() {
      return this.$store.getters.isRegisteredUser;
    },
  },
  data() {
    return {
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
        },
        {
          key: "email",
          label: "Email address",
          sortable: true,
          // Variant applies to the whole column, including the header and footer
          variant: "danger",
        },
      ],
      search: "",
      usertype: "",
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
      fetch(
        `http://localhost:9005/api/pharmacist/getpharmacybyidpharmacist/${id}`,
        {
          headers,
        }
      )
        .then((response) => response.json())
        .then((data) => this.$store.dispatch("updatePharmacy", data.id));
    },
  },
  mounted() {
    this.usertype = this.userType;
    console.log(this.usertype);
    // if (this.isPharmacyAdmin) {
    //   this.getPharmacy(this.$store.getters.getId);
    //   console.log(this.$store.getters.getPharmacy);
    //   this.getPharmacists(this.$store.getters.getPharmacy);
    // }
    // console.log("neeee");
  },
};
</script>
