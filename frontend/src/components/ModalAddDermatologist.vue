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
      <!-- <b-form-datepicker
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
      ></b-input> -->
      <!-- <b-button variant="success" @click="AddWorkDay"> ADD WORKDAYS</b-button> -->
     <br/>
      <b-list-group
        v-for="(listItem, key) in selectedDermatologist"
        v-bind:key="key"
      >
        <b-list-group-item button @click="removeDermatologist(listItem)">
          {{ listItem.name }}
        </b-list-group-item>
      </b-list-group>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "ModalAddDermatologist",
  props: ["selected", "id", "type", "usertype"],
  computed: {},
  data() {
        const now = new Date();
    const today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    const minDate = new Date(today);
    return {
      show: false,
      text: "",
      dermatologists: [],
      startShift: 0,
      endShift: 10,
      currentId: "",
      currentDermatologist: "",
      selectedDermatologist: [],
      // datepicker_1: new Date(),
      // datepicker_2: new Data(),
       minDatePicker_1: minDate,
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
            alert("Created!");
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
  },
};
</script>
