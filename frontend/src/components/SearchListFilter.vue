<template>
  <div>
    <div v-if="isRegisteredUser">
      <Navbar />
    </div>
    <div v-else>
      Now you don't
    </div>
    <b-card no-body>
      <b-tabs card>
        <!-- <b-input v-model="filter" placeholder="Search"></b-input> -->
        <b-tab title="Pharmacists" active>
          <b-input v-model="filter" placeholder="Search"></b-input>
          <div>
            <b-table
              striped
              hover
              :filter="filter"
              :filter-included-fields="[
                'name',
                'surname',
                'email',
                'pharmacyName',
              ]"
              :items="pharmacists"
              :fields="fields"
            ></b-table>
          </div>
        </b-tab>
        <b-tab title="Dermatologists" active>
          <b-input v-model="filter" placeholder="Search"></b-input>
          <div>
            <b-table
              striped
              hover
              :filter="filter"
              :filter-included-fields="[
                'name',
                'surname',
                'email',
                'pharmacies',
              ]"
              :items="dermatologists"
              :fields="fieldsDerm"
            ></b-table>
          </div>
        </b-tab>
      </b-tabs>
    </b-card>
  </div>
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
    isRegisteredUser() {
      return this.$store.getters.isRegistered;
    },
  },
  data() {
    return {
      filter: "",
      pharmacy: null,
      dermatologists: [
        {
          id: "",
          name: "",
          surname: "",
          email: "",
          pharmacies: [
            {
              pharmName: "",
            },
          ],
        },
      ],
      pharmacists: [
        {
          id: "",
          name: "",
          surname: "",
          email: "",
          pharmacyName: "",
        },
      ],
      fields: [
        {
          key: "surname",
          sortable: true,
        },
        {
          key: "description",

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
      fieldsDerm: [
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
          key: "pharmacies.pharmName",
          label: "Pharmacies",
          sortable: true,
          // Variant applies to the whole column, including the header and footer
          variant: "danger",
        },
      ],
      search: "",
    };
  },
  methods: {
    getPharmacists(id, type) {
      const headers = {
        "Content-Type": "application/json",
      };
      headers["usertype"] = type;
      if (this.userType == "PHARMACY_ADMINISTRATOR") {
        fetch(`http://localhost:9005/api/pharmacist/getallpharmacists/${id}`, {
          headers,
        })
          .then((response) => response.json())
          .then((data) => (this.pharmacists = data));
      } else if (this.userType == "PATIENT") {
        fetch(`http://localhost:9005/api/pharmacist/getallpharmacists/`, {
          headers,
        })
          .then((response) => response.json())
          .then((data) => (this.pharmacists = data));
      }
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
    getDermatologist(id, type) {
      const headers = {
        "Content-Type": "application/json",
      };
      headers["usertype"] = type;
      if (this.userType == "PHARMACY_ADMINISTRATOR") {
        fetch(
          `http://localhost:9005/api/dermatologist/getalldermatologist/${id}`,
          {
            headers,
          }
        )
          .then((response) => response.json())
          .then((data) => (this.dermatologists = data));
      } else if (this.userType == "PATIENT") {
        fetch(
          `http://localhost:9005/api/dermatologist/getdermatologistsandpharmacynames/`,
          {
            headers,
          }
        )
          .then((response) => response.json())
          .then((data) => ((this.dermatologists = data), console.log(data)))
          .catch((response) => {
            console.log("proba");
            // alert("You are not authorized!");
            // this.$router.push("/loginPage");
          });
      }
    },
  },

  async created() {
    if (this.userType == "PHARMACY_ADMINISTRATOR") {
      await this.getPharmacy(this.userId);
      await this.getPharmacists(this.pharmacy, this.userType);
      await this.getDermatologist(this.pharmacy, this.userType);
    } else if (this.userType == "PATIENT") {
      this.getPharmacists(null, this.userType);
      this.getDermatologist(null, this.userType);
    } else {
      this.$router.push("/loginPage");
    }
    // this.getPharmacists(this.pharmacy);
    console.log("pharmacy id:");
    console.log(this.pharmacy);
  },
};
</script>
