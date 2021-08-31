<template>
  <div class="background-absence">
    <label>Absences</label>
    <b-table :items="this.Unnaproved" :fields="fields" responsive="sm">
      <template #cell(Approve)="row">
        <b-button @click="Approve(row.item)">Approve</b-button>
      </template>
      <template #cell(Refuse)="row">
        <ModalAbsence :selected="row.item.id" :id="Pharmacy" :type="userType"></ModalAbsence>
      </template>
      <div></div>
    </b-table>
  </div>
</template>

<script>
import ModalAbsence from "../components/ModalAbsences.vue";
export default {
  name: "Absences",
  components: { ModalAbsence },
  computed: {
    userType() {
      return this.$store.getters.getUserType;
    },
    Pharmacy() {
      return this.$store.getters.getPharmacy;
    },
    Unnaproved() {
      return this.$store.getters.getUnnaprovedAbsences;
    },
    Approved() {
      return this.$store.getters.getApprovedAbsences;
    },
  },
  data() {
    return {
      fields: [
        {
          key: "name",
          label: "Name",
          sortable: true,
        },
        {
          key: "surname",
          label: "Surname",
          sortable: true,
        },
        {
          key: "startTime",
          label: "From",
          sortable: true,
        },
        {
          key: "endTime",
          label: "To",
          sortable: true,
        },

        {
          key: "type",
          label: "Status",
          sortable: true,
        },
        "Approve",
        "Refuse",
      ],
    
    };
  },
  methods: {
    Approve(item) {
        var vm = this;
      // absence/approve-absence/{id}
      fetch(`http://localhost:9005/api/absence/approve-absence/${item.id}`, {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          usertype: this.userType,
        },
        method: "POST",
      })        .then(function(response) {
          if (response.ok) {
            alert("Successful!");
            return response.json();
          } else {
            alert("Unsuccessful!");
            return Promise.reject(response);
          }
        })
        .then(function(data) {
          console.log(data.email)
          if (data.email != null) {
            const mail = {
              mailFrom: "ursaminor1777@gmail.com",
              mailTo: data.email,
              mailCc: "",
              mailBcc: "",
              mailSubject: "ABSENCE REQUEST",
              mailContent: "Dear pharmacist, Your absence is approved!",
              contentType: "",
              attachments: [],
            };

            return fetch(`http://localhost:9005/api/email/email-sent/`, {
              headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
              },
              method: "POST",
              body: JSON.stringify(mail),
            });
          }
        })

        .then(function(response) {
          if (response.ok) {
            return response.json();
          } else {
            return Promise.reject(response);
          }
        })
        .then(function(data) {
          return fetch(
            `http://localhost:9005/api/pharmacy/get-all-unapproved-absences/${vm.Pharmacy}`,
            {
              headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
                usertype: vm.userType,
              },
              method: "GET",
            }
          );
        })
        .then(function(response) {
          return response.json();
        })
        .then((data) => vm.$store.dispatch("updateUnapprovedAbsences", data))
        .catch(function(error) {
          console.warn(error);
        });
    },

    getUnnaproved() {
      fetch(
        `http://localhost:9005/api/pharmacy/get-all-unapproved-absences/${this.Pharmacy}`,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            usertype: this.userType,
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
    },
  },
  mounted() {
    this.getUnnaproved();
  },
};
</script>
<style scoped>
.background-absence {
  background-color: aliceblue;
}
</style>
