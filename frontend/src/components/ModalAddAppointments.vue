<template>
  <!-- MODALNI DIJALOG ZA LEKOVE - BOJANA -->

  <div>
    <b-button variant="warning" @click="onshow(selected)"
      >APPOINTMENTS</b-button
    >
    <b-modal v-model="show" ref="modal" title="Edit user" hide-footer>
      <div>
        <b-table
          :items="WorkingDays"
          selectable
          :select-mode="mode"
          :fields="fields"
          @row-selected="onRowSelectedAppointment"
          ref="selectableTableAppointments"
        >
          >
        </b-table>
      </div>
      <div>
        <b-form>
          <label>Select time:</label>
          <b-input type="number" :min="timeMin" :max="timeMax" v-model="hours"></b-input>
          <label>Select duration:</label>
          <b-form-select
            v-model="duration"
            class="mb-2 mr-sm-2 mb-sm-0"
            :options="['15', '30', '35', '40', '45', '60']"
          ></b-form-select>
        </b-form>
             <b-button variant="success" @click="createAppointment">CREATE</b-button>
      </div>
 
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "ModalAddAppointment",
  props: ["selected", "id", "type"],
  computed: {
    WorkingDays() {
      return this.$store.getters.getWorkingDays;
    },
  },
  data() {
    return {
      show: false,
      mode: "single",
      idDerm: 0,
      duration: "",
        hours:0,
      timeMin:0,
      timeMax:0,
      selectedAppointment: "",
      workdays: [],
      fields: [
        {
          key: "startTime",
          label: "Start time",
          sortable: true,
        },
      ],
    };
  },
  methods: {
    onshow(selected) {
      var vm = this;
      this.show = true;
      if (selected == null) {
        this.show = false;
      } else {
        fetch(
          `http://localhost:9005/api/dermatologist/get-by-email/${this.id}`,
          {
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
              usertype: this.type,
            },
            method: "POST",
            body: JSON.stringify(selected.email),
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
            vm.idDerm = data;
            return fetch(
              `http://localhost:9005/api/workday/get-working-non-absence-days/${vm.id}/${vm.idDerm}`,
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
          .then((response) => response.json())
          .then((data) => {
            this.$store.dispatch("updateWorkingDays", data);
          })
          .catch();
      }
    },
    onRowSelectedAppointment(item) {
      this.selectedAppointment = item[0];

      var minimum = 0;
      var maximum = 0;
      var startHours = this.selectedAppointment.startTime.split("T");
      var extractedHours = startHours[1].split(":");
      var nesto = extractedHours[0]
      if(nesto[0] == "0"){
          minimum = parseInt(nesto[1]);
      
      }else{
          minimum = parseInt(nesto);
        
      }


     var endHours = this.selectedAppointment.endTime.split("T");
      var extractedHoursEnd = endHours[1].split(":");
      var nestoEnd = extractedHoursEnd[0]
      if(nestoEnd[0] == "0"){
          maximum = parseInt(nestoEnd[1]);
        
      }else{
          maximum = parseInt(nestoEnd);
       
      }
      this.hours = minimum;
      this.timeMin = minimum;
      this.timeMax = maximum - 1;
    },

    clearSelected() {
      this.$refs.selectableTableAppointments.clearSelected();
    },
  },
};
</script>
<style scoped></style>
