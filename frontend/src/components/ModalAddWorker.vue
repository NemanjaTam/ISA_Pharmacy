<template>
  <!-- MODALNI DIJALOG ZA LEKOVE - BOJANA -->

  <div>
    <b-button @click="onshow(selected)" variant="success">Add</b-button>
    <b-modal v-model="show" ref="modal" title="Add worker" hide-footer>
      <div>
        <b-form-input
          id="input-live"
          v-model="newWorker.name"
          placeholder="Enter name"
          type="text"
          required
          trim
        ></b-form-input>
        <b-form-input
          id="input-live"
          v-model="newWorker.surname"
          placeholder="Enter surname"
          type="text"
          required
          trim
        ></b-form-input>
        <b-form-input
          id="input-live"
          v-model="newWorker.email"
          @change="checkIsEmailValid"
          placeholder="Enter email"
          type="text"
          required
          trim
        ></b-form-input>
        <b-form v-if="!emailValid" style="color:red">
          {{ this.emailMessage }}
        </b-form>
        <b-form-input
          id="input-live"
          v-model="newWorker.state"
          placeholder="Enter state"
          type="text"
          required
          trim
        ></b-form-input>
        <b-form-input
          id="input-live"
          v-model="newWorker.city"
          placeholder="Enter city"
          type="text"
          required
          trim
        ></b-form-input>
        <b-form-input
          id="input-live"
          v-model="newWorker.street"
          placeholder="Enter street"
          type="text"
          required
          trim
        ></b-form-input>
        <b-form-input
          id="input-live"
          v-model="newWorker.number"
          placeholder="Enter number"
          type="text"
          required
          trim
        ></b-form-input>
        <b-form-input
          id="input-live"
          v-model="newWorker.postalCode"
          placeholder="Enter postalCode"
          type="text"
          required
          trim
        ></b-form-input>
        <b-form-input
          id="input-live"
          v-model="newWorker.phone"
          placeholder="Enter phone number"
          type="text"
          required
          trim
        ></b-form-input>
        <b-form-datepicker
          class="mb-2 datepicker_b"
          :min="minDatePicker_1"
          v-model="datepicker_1"
          :format="'dd-MM-yyyy'"
        ></b-form-datepicker>
        <b-form-datepicker
          :format="'dd-MM-yyyy'"
          :min="datepicker_1"
          :max="datepicker_1"
          class="datepicker_b"
          v-model="datepicker_2"
        ></b-form-datepicker>
        <label>Work shift start:</label>
        <b-input
          type="number"
          :min="timeMin"
          :max="timeMax2"
          v-model="startShift"
        ></b-input>
        <label>Work shift end:</label>
        <b-input
          type="number"
          :min="timeMin"
          :max="timeMax"
          v-model="endShift"
        ></b-input>
        <b-button variant="success" @click="AddWorkDay"> ADD WORKDAYS</b-button>
        <div>
          <b-list-group
            v-for="(listItem, index) in newWorker.workingDays"
            v-bind:key="index"
          >
            <b-list-group-item button @click="removeNew(listItem)">
              {{
                "From:" +
                  " " +
                  listItem.startShift +
                  " " +
                  "To:" +
                  " " +
                  listItem.endShift
              }}
            </b-list-group-item>
          </b-list-group>
        </div>
        <br />
        <b-form v-if="!formValid" style="color:red">
          {{ this.formMessage }}
        </b-form>
        <b-button @click="createNewUser">CREATE</b-button>
      </div>
    </b-modal>
  </div>
</template>

<script>
import axios from "axios";
const moment = require("moment");
export default {
  name: "ModalAddWorker",
  props: ["selected", "id", "workerType","type"],
  computed: {},
  data() {
    const now = new Date();
    const today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    const minDate = new Date(today);
    return {
      newWorker: {
        name: "",
        surname: "",
        email: "",
        password: "",
        state: "",
        city: "",
        street: "",
        number: "",
        password: "kinzo",
        postalCode: "",
        phone: "",
        workingDays: [],
        worker: this.workerType
      },
      show: false,
      emailValid: false,
      emailMessage: "",
      formMessage: "",
      formValid: false,
      datepicker_1: new Date(),
      datepicker_2: new Date(),
      minDatePicker_1: minDate,
      timeMin: 6,
      timeMax: 22,
      timeMax2: 21,
      startShift: 6,
      endShift: 22,
    };
  },
  methods: {
    onshow(selected) {
      this.show = true;
    },
    checkIsEmailValid() {
      if (this.validEmail(this.newWorker.email)) {
        axios
          .get(
            "http://localhost:9005/api/user/check-email/" + this.newWorker.email
          )
          .then((r) => {
            if (r.data == "not_taken") {
              this.emailValid = true;
            } else {
              this.emailValid = false;
              this.emailMessage = "This email is already taken.";
            }
          });
      } else {
        this.emailValid = false;
        this.emailMessage = "Email is not valid!";
      }
    },
    validEmail(email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },

    check() {
      if (!this.emailValid) return true;
      else if (this.newWorker.email == "") return true;
      else if (this.newWorker.name == "") return true;
      else if (this.newWorker.name == "") return true;
      else if (this.newWorker.surname == "") return true;
      else if (this.newWorker.phone == "") return true;
      else if (this.newWorker.state == "") return true;
      else if (this.newWorker.city == "") return true;
      else if (this.newWorker.street == "") return true;
      else if (this.newWorker.number == "") return true;
      else if (this.newWorker.postalCode == "") return true;
      else if( this.newWorker.workingDays.length <= 0) return true;
      else return false;
    },
    AddWorkDay() {
     var startFormatted = moment(this.datepicker_1).format()
          var new_string = startFormatted.slice(0, 19);
      var workingDay = {
        startTime: new_string ,
        endTime:  new_string,
        startShift: this.startShift,
        endShift: this.endShift,
      };
      this.newWorker.workingDays.push(workingDay);
    },

    removeNew(item) {
      this.newWorker.workingDays.splice(
        this.newWorker.workingDays.indexOf(item),
        1
      );
    },
    createNewUser() {
      var vm = this;
      if (this.check()) {
        this.formValid = false;
        this.formMessage = "Check all input fields";
        return;
      }
      this.formValid = true;
      var userDTO = {
 
        name: this.newWorker.name,
        surname: this.newWorker.surname,
        email: this.newWorker.email,
        password:this.newWorker.password,
        address:{
          state:this.newWorker.state,
          city: this.newWorker.city,
          street: this.newWorker.street,
          number: this.newWorker.number,
          postalCode: this.newWorker.postalCode,
      
        },
        phone: this.newWorker.phone,
        userType:"PHARMACIST",
        isFirstTimeLogging:true,
      }
      var ShiftDTO = {
        shifts: this.newWorker.workingDays,
        userType: "PHARMACIST",
      }
      //  axios.post("", );
         fetch(
          `http://localhost:9005/api/user/register-by-pharmacy-admin/${this.id}`,
          {
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
      
            },
            method: "POST",
            body: JSON.stringify(userDTO),
          }
        )
          .then(function(response) {
            if (response.ok) {
              alert("Created!");
              return response.json();
            } else {
              return Promise.reject(response);
            }
          }).then(function(data) {
            if (data > 0) {
              
            
            return fetch(
              `http://localhost:9005/api/workday/create-working-days/${vm.id}/${data}`,
              {
                headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json",
                  usertype: vm.type,
                },
                  method: "POST",
                 body: JSON.stringify(ShiftDTO),
              }
            );
          }})
    },
  },
};
</script>
