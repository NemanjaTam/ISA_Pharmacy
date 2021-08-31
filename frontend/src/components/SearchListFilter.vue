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
        <b-tab title="Pharmacists" active>
          <!-- <b-form-input
            id="filter-input"
            v-model="filter.name"
            type="search"
            placeholder="Type to Search"
          ></b-form-input> -->

          <b-form-input
            id="filter-input"
            type="search"
            placeholder="Type to Search"
          ></b-form-input>
          <!-- <b-input v-model="filter" placeholder="Search"></b-input> -->
          <div>
            <b-table striped show-empty :items="filteredPharmacists">
              <template slot="top-row" slot-scope="{ fields }">
                <td v-for="field in fields" :key="field.key">
                  <input
                    v-model="filters[field.key]"
                    :placeholder="field.label"
                  />
                </td>
              </template>
            </b-table>
          </div>
        </b-tab>
        <b-tab title="Dermatologists" active>
          <!-- <b-input v-model="filter" placeholder="Search"></b-input> -->
          <div>
            <!-- <b-table
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
            ></b-table> -->
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
    filteredPharmacists() {
      const filterededPharmacist = this.pharmacists.filter((pharmacist) => {
        return Object.keys(this.filters).every((key) =>
          String(pharmacist[key]).toLowerCase().includes(this.filters[key].toLowerCase())
        );
      });
      return filterededPharmacist.length > 0
        ? filterededPharmacist
        : [
            {
              name: "",
              surname: "",
              ratings: "",
              email: "",
              pharmacyName: "",
            },
          ];
    },
  },
  data() {
    return {
      filters: {
        name: "",
        surname: "",
        ratings: "",
        email: "",
        pharmacyName: "",
      },


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
          key: "name",

          sortable: true,
        },
        {
          key: "ratings",
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
          key: "pharmacyName",
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
        fetch(
          `http://localhost:9005/api/pharmacist/get-pharmacist-rating/${id}`,
          {
            headers,
          }
        )
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
          `http://localhost:9005/api/dermatologist/get-dermatologist-rating/${id}`,
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
    filterTable: function(tableRow, filter) {
      if (filter[0] !== null && filter[1] !== null) {
        //both filters set
        return (
          tableRow.columnOne == filter[0] && tableRow.columnTwo == filter[1]
        );
      } else {
        return (
          tableRow.columnOne == filter[0] || tableRow.columnTwo == filter[1]
        );
      }
    },
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
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
