<template>
  <div>
    <div>
      <b-card no-body>
        <b-tabs card>
          <b-tab title="Order existing medicine" active>
            <div class="container_div">
              <div class="scrollable_div">
                <label>Please select medication:</label>
                <b-list-group
                  v-for="(listItem, index) in medications"
                  v-bind:key="index"
                  class="medication_list_scrollable"
                >
                  <b-list-group-item
                    button
                    @click="addMedicine(listItem.name, listItem.id)"
                  >
                    {{ listItem.name }}</b-list-group-item
                  >
                </b-list-group>
              </div>
              <div class="right_div">
                <label>Selected medication:</label>
                <br />
                <label>{{ this.currentMedication }}</label>
                <br />
                <br />
                <br />
                <label>Select quantity:</label>

                <b-form-input
                  type="number"
                  min="0"
                  v-model="currentQuantity"
                ></b-form-input>
                <br />

                <b-button
                  variant="success"
                  @click="addMedicationToPurchaseOrder"
                  >Add medicine</b-button
                >
                <br />
                <br />
                <div class="bottom_div"></div>
              </div>
              <div class="right_div margin_right">
                <br />
                <br />
                <b-icon
                  icon="arrow-right"
                  class="h1 mb-2"
                  animation="cylon"
                ></b-icon>
              </div>
              <div class="scrollable_div">
                <label>Meds: Quantity:</label>
                <b-list-group
                  v-for="(listItem, index) in medicationOrder"
                  v-bind:key="index"
                  class="medication_list_scrollable"
                >
                  <b-list-group-item>
                    {{ listItem.name }}
                    {{ listItem.quantity }}</b-list-group-item
                  >
                </b-list-group>
                <br />
              </div>

              <div class="bottom_div">
                <label>start/end date:</label>
                <b-form-datepicker
                  class="mb-2 datepicker_b"
                  :min="minDatePicker_1"
                  v-model="datepicker_1"
                  :format="'dd-MM-yyyy'"
                ></b-form-datepicker>
                <b-form-datepicker
                  :format="'dd-MM-yyyy'"
                  :min="datepicker_1"
                  class="datepicker_b"
                  v-model="datepicker_2"
                ></b-form-datepicker>
                <br />
                <b-button variant="success" @click="sendPurchaseOrder"
                  >Submit</b-button
                >
                <b-button variant="danger" @click="resetForm">Reset </b-button>
              </div>
              <!-- </div> -->
            </div>
          </b-tab>
          <b-tab title="Add new medicine" active> </b-tab>
        </b-tabs>
      </b-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "PurchaseOrder",
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
    listByBreaks() {
      return this.medicationOrder.map((e) => {
        return e.name.split("\n");
      });
    },
  },
  data() {
    const now = new Date();
    const today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    const minDate = new Date(today);
    return {
      medications: [
        {
          id: 0,
          name: "",
          description: "",
          manufacturer: "",
          medicationForm: "",
          prescriptionRegime: "",
          code: "",
          typeOfMedication: "",
          structure: "",
          contraindications: "",
          recommendedIntake: "",
          ratings: [],
          quantity: 0,
        },
      ],
      medicationOrder: [
        {
          id: 0,
          name: "",
          description: "",
          manufacturer: "",
          medicationForm: "",
          prescriptionRegime: "",
          code: "",
          typeOfMedication: "",
          structure: "",
          contraindications: "",
          recommendedIntake: "",
          ratings: [],
          quantity: 0,
        },
      ],
      pharmacy_id: 0,
      currentMedication: "",
      currentQuantity: 0,
      currentId: 0,
      datepicker_1: new Date(),
      datepicker_2: new Date(),
      minDatePicker_1: minDate,
    };
  },
  methods: {
    getPharmacyIdbyUserID(id) {
      var vm = this;
      fetch(`http://localhost:9005/api/pharmacy/getpharmacyidbyuser/${id}`, {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        method: "GET",
      })
        .then(function(response) {
          if (response.ok) {
            return response.json();
          } else {
            return Promise.reject(response);
          }
        })
        .then(function(data) {
          vm.pharmacy_id = data;
          console.log(vm.pharmacy_id);
          return fetch(
            `http://localhost:9005/api/pharmacy/get-medications/${data}`,
            {
              headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
              },
              method: "GET",
            }
          );
        })
        .then(function(response) {
          return response.json();
        })
        .then((data) => (vm.medications = data))

        .catch(function(error) {
          console.warn(error);
        });
    },
    resetForm() {
      this.currentMedication = "";
      this.currentQuantity = 0;
      this.medicationOrder = [];
      this.datepicker_1 = new Date();
      this.datepicker_2 = new Date();
    },
    addMedicine(name, id) {
      this.currentMedication = name;
      this.currentQuantity = 0;
      this.currentId = id;
    },
    filterValue(id) {
      return (item = this.medications.find((item) => item.id === id));
    },
    findById(source, id) {
      for (var i = 0; i < source.length; i++) {
        if (source[i].id === id) {
          console.log(source[i].name);
          return source[i];
        }
      }
      throw "Couldn't find object with id: " + id;
    },
    addMedicationToPurchaseOrder() {
      if (this.currentMedication != "" && this.currentQuantity != 0) {
        console.log("heeeelo");

        var filterObj = this.findById(this.medications, this.currentId);
        const medicationObject = {
          name: filterObj.name,
          id: filterObj.id,
          quantity: this.currentQuantity,
          description: filterObj.description,
          manufacturer: filterObj.manufacturer,
          medicationForm: filterObj.medicationForm,
          prescriptionRegime: filterObj.prescriptionRegime,
          code: filterObj.code,
          typeOfMedication: filterObj.typeOfMedication,
          structure: filterObj.structure,
          contraindications: filterObj.contraindications,
          recommendedIntake: filterObj.recommendedIntake,
          ratings: filterObj.ratings,
        };
        this.medicationOrder.push(medicationObject) /
          console.log(filterObj.name);
      } else {
        alert("Please enter quantity and select medication!");
      }
    },
    sendPurchaseOrder() {
      if(this.medicationOrder.length > 0){
      const body = {
        startTime: this.datepicker_1,
        endTime: this.datepicker_2,
        medicationDTO: this.medicationOrder,

        adminId: this.userId,
      };
      fetch(
        `http://localhost:9005/api/pharmacy/purchaseorder-create/${this.pharmacy_id}`,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "POST",
          body: JSON.stringify(body),
        }
      )
        .then(function(response) {
          if (response.ok) {
            alert("Purchase order made")
            return response.json();
          } else {
            return Promise.reject(response);
          }
        })
        .catch(function(error) {
          console.warn(error);
        });}else{
          alert("Please select medication!");
        }
    },
  },
  mounted() {
    this.getPharmacyIdbyUserID(this.userId);
    this.resetForm();
  },
};
</script>
<style scoped>
.container_div {
  display: flex;
  height: 400px;
  max-width: 2000px;
}
.scrollable_div {
  overflow-y: scroll;

  max-height: 350px;
  max-width: 200px;
  /* float:left */
  flex: 1;
}
.right_div {
  max-height: 350px;
  max-width: 200px;
  flex: 1;
  text-align: right;
}
.centert_div {
  max-height: 350px;
  max-width: 300px;
  flex: 1;
  text-align: left;
}
.bottom_div {
  position: relative;
  /* bottom: 30px; */
  flex: 1;
  /* text-align: right; */

  margin-left: 5px;
}
.datepicker_b {
  width: 300px;
}
.margin_right {
  margin-right: 15px;
}
</style>
