<template>
  <!-- MODALNI DIJALOG ZA LEKOVE - BOJANA -->

  <div>
    <b-button @click="onshow(selected)">Refuse</b-button>
    <b-modal
      v-model="show"
      ref="modal"
      title="Please insert an explanation"
      hide-footer
    >
      <div>
        <b-card class="b-card-two">
          <b-form-textarea
            id="textarea"
            v-model="text"
            placeholder="Enter an explanation..."
            rows="3"
            max-rows="6"
          ></b-form-textarea>
          <br/>
          <b-button @click="Refuse"> OK </b-button>
        </b-card>
      </div>
    </b-modal>
  </div>
</template>

<script>
const moment = require("moment");
export default {
  name: "ModalAbsence",
  props: ["selected", "id","type"],
  computed: {},
  data() {
    return {
      show: false,
      text: "",
    };
  },
  // this.$store.getters.getSelectedMedicineForEdit
  methods: {
    onshow(selected) {
      this.show = true;
    },
    Refuse(){
        if(this.text.length < 3){
            alert("Please enter more characters into text field!");
        }else{
              var vm = this;
              console.log(vm.type)
      // absence/approve-absence/{id}
      fetch(`http://localhost:9005/api/absence/refuse-absence/${vm.selected}`, {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          usertype: vm.type,
        },
        method: "POST",
      })        .then(function(response) {
          if (response.ok) {
            return response.json();
          } else {
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
              mailContent: vm.text,
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
            `http://localhost:9005/api/pharmacy/get-all-unapproved-absences/${vm.id}`,
            {
              headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
                usertype: vm.type,
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
        }
    }
  },
};
</script>
<style scoped>
.error {
  color: rgb(255, 0, 0);
}
</style>
