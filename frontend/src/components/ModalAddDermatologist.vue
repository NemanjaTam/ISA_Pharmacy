<template>
  <!-- MODALNI DIJALOG ZA LEKOVE - BOJANA -->

  <div>
    <b-button @click="onshow(selected)" variant="success">Add</b-button>
    <b-modal v-model="show" ref="modal" title="Add dermatologist" hide-footer>
      <b-list-group
        v-for="(listItem, index) in dermatologists"
        v-bind:key="index + 'derm'"
      >
        <b-list-group-item
          button
          @click="
            addDermatologist(listItem.name, listItem.id, listItem.surname)
          "
        >
          {{ listItem.name + " " + listItem.surname }}
        </b-list-group-item>
      </b-list-group>

      <div>
        <b-form-group>
          <b-form-datepicker
            class="mb-2 datepicker_b"
            :min="minDatePicker_1"
            v-model="datepicker_1"
            :format="'dd-MM-yyyy'"
          ></b-form-datepicker>
          <label>Work shift start:</label>
          <b-input type="number" :min="timeMin" v-model="startShift"></b-input>
          <label>Work shift end:</label>
          <b-input
            type="number"
            :min="timeMin"
            :max="timeMax"
            v-model="endShift"
          ></b-input>

          <b-button variant="success" @click="AddWorkDay">
            ADD WORKDAYS</b-button
          >
        </b-form-group>
        <label>Working days:</label>
        <b-list-group
          v-for="(listItem, index) in workingDays"
          v-bind:key="index + 'wd'"
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
      <label> Dermatologist selected:</label>
      <b-list-group
        v-for="(listItem, key) in selectedDermatologist"
        v-bind:key="key"
      >
        <b-list-group-item button @click="removeDermatologist(listItem)">
          {{ listItem.name }}
        </b-list-group-item>
      </b-list-group>
      <br/>
      <b-button variant="success" @click="createNewWorkingDays">CREATE</b-button>
    </b-modal>
  </div>
</template>

<script>
const moment = require("moment");
export default {
  name: "ModalAddDermatologist",
  props: ["selected", "id", "type", "usertype","list"],
  computed: {},
  data() {
    const now = new Date();
    const today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    const minDate = new Date(today);
    return {
      show: false,
      text: "",
      dermatologists: [],
      startShift: 6,
      endShift: 22,
      currentId: "",
      currentDermatologist: "",
      selectedDermatologist: [],
      datepicker_1: new Date(),
      datepicker_2: new Date(),
      minDatePicker_1: minDate,
      timeMin: 6,
      timeMax: 22,
      workingDays:[],
      respons :null,
      derm:[],
    };
  },
  methods: {



    addDermatologist(name, id, surname) {
      if (this.selectedDermatologist.length < 1) {
        this.currentDermatologist = name + " " + surname;
        this.currentId = id;
        var derm = {
          name: this.currentDermatologist,
          id: this.currentId,
        };
        this.selectedDermatologist.push(derm);
      }
    },
    removeDermatologist(item) {
      for (let i = this.selectedDermatologist.length; i > 0; i--) {
        this.selectedDermatologist.pop();
      }
    },
    onshow(selected) {
      this.show = true;
      fetch(
        `http://localhost:9005/api/dermatologist/get-all-not-in-pharmacy/${this.id}`,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            userType: this.type,
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
        .then((data) => {
          this.dermatologists = data;
        })
        .catch();
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
    var same = false;
    for(var i = 0;i < this.workingDays.length;i++){

    if(this.workingDays[i].startTime == new_string){
      
        same = true;
    }
  }
   if(!same){  
      this.workingDays.push(workingDay);}else{
      alert("The date is already chosen, please choose another! ")
   }

   
    },
  
    removeNew(item) {
      this.workingDays.splice(
        this.workingDays.indexOf(item),
        1
      );
    },
    createNewWorkingDays(){
      var vm = this
            var ShiftDTO = {
        shifts: this.workingDays,
        userType: "DERMATOLOGIST",
      }
      fetch(
              `http://localhost:9005/api/workday/create-working-days/${vm.id}/${this.selectedDermatologist[0].id}`,
              {
                headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json",
                  usertype: vm.type,
                },
                  method: "POST",
                 body: JSON.stringify(ShiftDTO),
              }
            ).then(function(data) {
              vm.respons = data;
              return   fetch(
        `http://localhost:9005/api/dermatologist/get-all-not-in-pharmacy/${vm.id}`,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            userType: vm.type,
          },
          method: "GET",
        }
      )
         }).then((response) => response.json())
          .then(function(data) {
         
            return fetch(
              `http://localhost:9005/api/dermatologist/get-dermatologist-rating/${vm.id}`,
              {
                headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json",
                  usertype: vm.type,
                },
                method: "GET",
              }
            );
          }) .then((response) => response.json())
          .then((data) => {   vm.$emit('updateparent', data)})
          .catch();
         
         
         
         
        //  .then((response) => response.json())
        //  .then((data) => vm.dermatologists = data)
        //  if(vm.respons == 0){
        //    alert("Dermatologist is working!");
        //  }else{
        //    alert("Dermatologist added!");
       
        //    this.show = false;
        //  }
          }
    
  },
};
</script>
