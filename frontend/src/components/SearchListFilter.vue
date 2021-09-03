<template>
  <div>
    <div v-if="this.userType=='PATIENT'">
     <PatientNavBar/>
    </div>
    <b-card no-body>
      <b-tabs card>
        <b-tab title="Pharmacists" active>
          <b-button
            variant="danger"
            @click="deletePharmacist(selected)"
            v-if="userType == 'PHARMACY_ADMINISTRATOR'"
            >DELETE</b-button
          >
          <!-- <b-button
            variant="warning"
            @click="deleteItem(selected)"
            v-if="userType == 'PHARMACY_ADMINISTRATOR'"
            >EDIT</b-button
          > -->
         
          <ModalAddWorker :selected="selectedDerm[0]" :id="Pharmacy" :workerType="'PHARMACIST'" :type="userType"  @updateparentPharmacist="changeParentPharmacist($event)"></ModalAddWorker>
           <!-- <ModalEditPharmacist :selected="selected[0]" :id="Pharmacy"></ModalEditPharmacist> -->
          <div>
            <b-table
              striped
              show-empty
              :items="filteredPharmacists"
              selectable
              :select-mode="mode"
              ref="selectableTable"
              @row-selected="onRowSelected"
            >
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
           <b-button
          
            variant="danger"
            @click="deleteDermatologist(selectedDerm)"
            v-if="userType == 'PHARMACY_ADMINISTRATOR'"
            >DELETE</b-button
          >
          <ModalAddAppointment :selected="selectedDerm[0]" :id="Pharmacy" :type="userType"></ModalAddAppointment>
          <ModalAddDermatologist :selected="selectedDerm[0]" :id="Pharmacy" :type="userType" :list="dermatologists" @updateparent="changeParent($event)"></ModalAddDermatologist>
           <!-- <ModalAddWorker :selected="selectedDerm[0]" :id="Pharmacy" :workerType="'DERMATOLOGIST'"></ModalAddWorker> -->
           <!-- <ModalEditPharmacist :selected="selectedDerm[0]" :id="Pharmacy"></ModalEditPharmacist> -->
          <div>
            <b-table striped show-empty :items="filteredDermatologist"
            selectable
            
              :select-mode="mode"
              @row-selected="onRowSelectedDerm"
              ref="selectableTableDerm">
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
      </b-tabs>
    </b-card>
  </div>
</template>
<script>
import ModalEditPharmacist from "../components/ModalEditPharmacist.vue"
import ModalAddAppointment from "../components/ModalAddAppointments.vue"
import ModalAddWorker from "../components/ModalAddWorker.vue"
import ModalAddDermatologist from "../components/ModalAddDermatologist.vue"
import PatientNavBar from "../components/PatientNavBar.vue"
export default {
  name: "SearchListFilter",
  components:{ModalEditPharmacist,ModalAddAppointment,ModalAddWorker,ModalAddDermatologist,PatientNavBar},
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
          String(pharmacist[key])
            .toLowerCase()
            .includes(this.filters[key].toLowerCase())
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
              edit: "",
            },
          ];
    },
    filteredDermatologist() {
      const filterededPharmacist = this.dermatologists.filter((pharmacist) => {
        return Object.keys(this.filters).every((key) =>
          String(pharmacist[key])
            .toLowerCase()
            .includes(this.filters[key].toLowerCase())
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
    // filteredDermatologist() {
    //   const filterededDermatologist = this.dermatologists.filter((dermatologist) => {
    //     return Object.keys(this.filtersDerm).every((key) =>
    //       String(dermatologist[key]).toLowerCase().includes(this.filtersDerm[key].toLowerCase())
    //     );
    //   });
    //   return filterededDermatologist.length > 0
    //     ? filterededDermatologist
    //     : [
    //         {
    //           name: "",
    //           surname: "",
    //           ratings: "",
    //           email: "",
    //           pharmacyName: "",
    //         },
    //       ];
    // },
  },
  data() {
    return {
      mode: "single",
      filters: {
        name: "",
        surname: "",
        ratings: "",
        email: "",
        pharmacyName: "",
        edit: "",
      },
      filtersDerm: {
        name: "",
        surname: "",
        ratings: "",
        email: "",
        pharmacyName: "",
      },

      selected: [],
      selectedDerm:[],
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
        {
          key: "edit",
          label: "Edit",
        },
      ],
      fieldsDerm: [
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
      ],
      search: "",
    };
  },
  methods: {
 changeParent(variable) {
        this.dermatologists = variable
    },
    changeParentPharmacist(variable) {
        this.pharmacists = variable
    },
    deletePharmacist(selected) {
      var vm = this;
      if (selected!=null) {
        console.log(selected[0].email);
        fetch(
          `http://localhost:9005/api/pharmacist/remove-pharmacist/${this.Pharmacy}`,
          {
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
              usertype: vm.userType,
            },
            method: "POST",
            body: JSON.stringify(selected[0].email),
          }
        )
          .then(function(response) {
            if (response.ok) {
              return response.json();
            } else {
              return Promise.reject(response);
            }
          })
          .then(function(data) {
            if (data == 0) {
              alert("Pharmacist has unfinished consultations!");
            }
            return fetch(
              `http://localhost:9005/api/pharmacist/get-pharmacist-rating/${vm.Pharmacy}`,
              {
                headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json",
                  usertype: vm.userType,
                },
                method: "GET",
              }
            );
          }) .then((response) => response.json())
          .then((data) => (vm.pharmacists = data))
          .catch();
      }else{
        alert("To delete please select row from table!")
      }
    },
     deleteDermatologist(selectedDerm) {
      var vm = this;
      if (selectedDerm!=null) {
        console.log(selectedDerm[0].email);
        fetch(
          `http://localhost:9005/api/dermatologist/remove-dermatologist/${this.Pharmacy}`,
          {
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
              usertype: vm.userType,
            },
            method: "POST",
            body: JSON.stringify(selectedDerm[0].email),
          }
        )
          .then(function(response) {
            if (response.ok) {
              return response.json();
            } else {
              return Promise.reject(response);
            }
          })
          .then(function(data) {
            if (data == 0) {
              alert("Dermatologist has unfinished appointments!");
            }
            return fetch(
              `http://localhost:9005/api/dermatologist/get-dermatologist-rating/${vm.Pharmacy}`,
              {
                headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json",
                  usertype: vm.userType,
                },
                method: "GET",
              }
            );
          }) .then((response) => response.json())
          .then((data) => (vm.dermatologists = data))
          .catch();
      }else{
        alert("To delete please select row from table!")
      }
    },


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
        fetch(`http://localhost:9005/api/pharmacist/getallpharmacists`, {
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
    onRowSelected(item) {
      this.selected = item;
    },
    onRowSelectedDerm(item){
      this.selectedDerm = item
    },
    clearSelectedDerm(){
      this.$refs.selectableTableDerm.clearSelectedDerm();
    },
    clearSelected() {
      this.$refs.selectableTable.clearSelected();
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
  mounted(){
     this.$forceUpdate();
  }
};
</script>
