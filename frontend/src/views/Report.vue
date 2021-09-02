<template>
  <div>
    <b-button variant="success" @click="showReport">APPOINTMENT DATA</b-button>

    <br />
    <div>
      <div class="div-color-report" v-if="show">
        <label>Appointment data:</label>
        <LineChart
          v-if="this.loadedYearlyAppointments"
          class="chart-size"
          :chartData="this.yearly"
          :dates="yearlyDates"
          :options="this.chartOptions"
          :label="labelYearly"
          :chartColors="yearlyAppointmentColors"
        ></LineChart>
        <LineChart
          v-if="this.loadedQuartalAppointments"
          class="chart-size"
          :chartData="this.quartal"
          :dates="quartalDates"
          :options="this.chartOptions"
          :label="labelQuartal"
          :chartColors="quartalColors"
        ></LineChart>
        <LineChart
          v-if="this.loadedMonthlyApointments"
          class="chart-size"
          :chartData="this.month"
          :dates="monthlyDates"
          :options="this.chartOptions"
          :label="labelMonthly"
          :chartColors="monthlyColors"
        ></LineChart>
      </div>
      <br />
      <b-button variant="success" @click="showReportMedication">MEDICATION DATA</b-button>
      <div class="div-color-report" v-if="showMedication">
        <label>Medication data</label>
        <BarChart
          v-if="this.loadedYearlyMedication"
          class="chart-size"
          :chartData="yearlyMedication"
          :dates="yearlyDatesMedication"
          :options="this.chartOptions"
          :label="labelYearlyMedication"
          :chartColors="yearlyAppointmentColors"
        ></BarChart>
        <BarChart
          v-if="this.loadedQuartalMedication"
          class="chart-size"
          :chartData="this.quartalMedication"
          :dates="quartalDatesMedication"
          :options="this.chartOptions"
          :label="labelQuartalMedication"
          :chartColors="quartalColors"
        ></BarChart>
        <BarChart
          v-if="this.loadedMonthlyMedication"
          class="chart-size"
          :chartData="this.monthMedication"
          :dates="monthlyDatesMedication"
          :options="this.chartOptions"
          :label="labelMonthly"
          :chartColors="monthlyColors"
        ></BarChart>
      </div>
      <br/>
      <br>
      <b-button variant="success" @click="showPharmacyData">RATINGS DATA</b-button>
      <br/>
      <div v-if="showRating">
        <div class="div-color-report">
          <label>Pharmacy</label>
          <b-table
            striped
            hover
            :filter-included-fields="['name', 'rating']"
            :items="pharmacyRating"
            :fields="fieldsPharmacy"
          ></b-table>
        </div>
        <div class="div-color-report">
          <label>Pharmacists</label>
          <b-table
            striped
            hover
            :filter-included-fields="[
              'name',
              'surname',
              'rating',
              'pharmacies',
            ]"
            :items="pharmacists"
            :fields="fields"
          ></b-table>
        </div>
        <br />
        <div class="div-color-report">
          <label>Dermatologists</label>
          <b-table
            striped
            hover
            :filter-included-fields="[
              'name',
              'surname',
              'rating',
              'pharmacies',
            ]"
            :items="dermatologists"
            :fields="fields"
          ></b-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LineChart from "../components/Line.vue";
import PieChart from "../components/Pie.vue";
import BarChart from "../components/Bar.vue";
export default {
  name: "Report",
  components: { LineChart, PieChart, BarChart },
  computed: {
    Pharmacy() {
      return this.$store.getters.getPharmacy;
    },
    userType() {
      return this.$store.getters.getUserType;
    },
  },
  data() {
    return {
      pharmacyRating: [],
      dermatologists: [],
      pharmacists: [],
      show: false,
      showMedication: false,
      showRating: false,
      mapa: "",
      yearly: [],
      yearlyDates: [],
      loadedYearlyAppointments: false,
      labelYearly: "Yearly appointments made",

      quartal: [],
      quartalDates: [],
      loadedQuartalAppointments: false,
      labelQuartal: "Appointments made by quartal",

      month: [],
      monthlyDates: [],
      loadedMonthlyApointments: false,
      labelMonthly: "Appointments made for current month",

      yearlyMedication: [],
      yearlyDatesMedication: [],
      loadedYearlyMedication: false,
      labelYearlyMedication: "Yearly medication usage",

      quartalMedication: [],
      quartalDatesMedication: [],
      loadedQuartalMedication: false,
      labelQuartalMedication: "Medication usage by quartal",

      monthMedication: [],
      monthlyDatesMedication: [],
      loadedMonthlyMedication: false,
      labelMonthlyMedication: "Medication usage by current month",

      chartOptions: {
        responsive: true,
        maintainAspectRatio: false,
      },
      yearlyAppointmentColors: {
        borderColor: "#4E5E66",
        pointBorderColor: "#4E5E66",
        pointBackgroundColor: "#31E981",
        backgroundColor: "#31E981",
      },
      quartalColors: {
        borderColor: "#784F41",
        pointBorderColor: "#784F41",
        pointBackgroundColor: "#BBE5ED",
        backgroundColor: "#BBE5ED",
      },

      monthlyColors: {
        borderColor: "#D2691E",
        pointBorderColor: "#D2691E",
        pointBackgroundColor: "#0000CD",
        backgroundColor: "#0000CD",
      },

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
      ],
      fieldsPharmacy: [
        {
          key: "name",
          sortable: true,
        },
        {
          key: "ratings",

          sortable: true,
        },
      ],
    };
  },
  methods: {
    getYearlyAppointments() {
      var vm = this;
      fetch(
        `http://localhost:9005/api/appointment/get-report-for-year/${this.Pharmacy}`,
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
        .then((data) => {
          var map = data;
          vm.yearlyDates = Object.keys(map);
          vm.yearly = Object.values(map);
          vm.loadedYearlyAppointments = true;
        })
        .catch();
    },

    getQuartalAppointments() {
      var vm = this;
      fetch(
        `http://localhost:9005/api/appointment/get-report-for-quartal/${this.Pharmacy}`,
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
        .then((data) => {
          var map = data;
          vm.quartalDates = Object.keys(map);
          vm.quartal = Object.values(map);
          vm.loadedQuartalAppointments = true;
        })
        .catch();
    },

    getMonthlyAppointments() {
      var vm = this;
      fetch(
        `http://localhost:9005/api/appointment/get-report-for-month/${this.Pharmacy}`,
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
        .then((data) => {
          var map = data;
          vm.monthlyDates = Object.keys(map);
          vm.month = Object.values(map);
          vm.loadedMonthlyApointments = true;
        })
        .catch();
    },

    async getYearlyMedicaton() {
      var vm = this;
      fetch(
        `http://localhost:9005/api/reservation/get-report-for-year/${this.Pharmacy}`,
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
        .then((data) => {
          var map = data;
          vm.yearlyDatesMedication = Object.keys(map);
          vm.yearlyMedication = Object.values(map);
          vm.loadedYearlyMedication = true;
        })
        .catch();
    },

    async getQuartalMedicaton() {
      var vm = this;
      fetch(
        `http://localhost:9005/api/reservation/get-report-for-quartal/${this.Pharmacy}`,
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
        .then((data) => {
          var map = data;
          vm.quartalDatesMedication = Object.keys(map);
          vm.quartalMedication = Object.values(map);
          vm.loadedQuartalMedication = true;
        })
        .catch();
    },

    async getMonthlyMedicaton() {
      var vm = this;
      fetch(
        `http://localhost:9005/api/reservation/get-report-for-month/${this.Pharmacy}`,
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
        .then((data) => {
          var map = data;
          vm.monthlyDatesMedication = Object.keys(map);
          vm.monthMedication = Object.values(map);
          vm.loadedMonthlyMedication = true;
        })
        .catch();
    },
    showReport() {
      if (!this.show) {
        this.show = true;
        this.getYearlyAppointments();
        this.getQuartalAppointments();
        this.getMonthlyAppointments();
      } else {
        this.show = false;
      }
    },
    showReportMedication() {
      if (!this.showMedication) {
        this.showMedication = true;
        this.getYearlyMedicaton();
        this.getQuartalMedicaton();
        this.getMonthlyMedicaton();
      } else {
        this.showMedication = false;
      }
    },
    showPharmacyData() {
      if (!this.showRating) {
        this.getPharmacists();
        this.getDermatologist();
        this.getPharmacyRatings();
        this.showRating = true;
      } else {
        this.showRating = false;
      }
    },
    getPharmacyRatings() {
      fetch(
        `http://localhost:9005/api/pharmacy/get-pharmacy-rating/${this.Pharmacy}`,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            usertype: this.userType,
          },
          method: "GET",
        }
      )
        .then((response) => response.json())
        .then((data) => {
          this.pharmacyRating = data;
        })
        .catch();
    },

    getPharmacists() {
      fetch(
        `http://localhost:9005/api/pharmacist/get-pharmacist-rating/${this.Pharmacy}`,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            usertype: this.userType,
          },
          method: "GET",
        }
      )
        .then((response) => response.json())
        .then((data) => {
          this.pharmacists = data;
        })
        .catch();
    },

    getDermatologist() {
      fetch(
        `http://localhost:9005/api/dermatologist/get-dermatologist-rating/${this.Pharmacy}`,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            usertype: this.userType,
          },
          method: "GET",
        }
      )
        .then((response) => response.json())
        .then((data) => {
          this.dermatologists = data;
        });
    },
  },

  async created() {},
  mounted(){
         if(this.userType != "PHARMACY_ADMINISTRATOR"){
       this.$router.push("/LoginPage").catch(()=>{});
    }
  }
};
</script>
<style scoped>
.div-color-report {
  background-color: white;
}
.chart-size {
  height: 200px;
}
.medication-data {
  background-color: #dda0dd;
}
</style>
