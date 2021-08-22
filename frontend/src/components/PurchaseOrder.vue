<template>
  <div>
    <div>
      <b-card no-body>
        <b-tabs card>
          <b-tab title="Order existing medication" active>
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
          <b-tab
            title="Order new medication"
            active
           
          >
            <div role="group">
              <label for="input-live">Name:</label>
              <b-form-input
                id="input-live"
                v-model="newMedication.name"
                aria-describedby="input-live-help input-live-feedback"
                :state="nameState"
                placeholder="Enter your name"
                trim
              ></b-form-input>

              <label for="input-live">Quantity:</label>
              <b-form-input
                id="input-live-quantity"
                v-model="newMedication.quantity"
                type="number"
                :min="1"
                aria-describedby="input-live-help input-live-quantity2"
                :state="quantityState"
                placeholder="Enter your name"
                trim
              ></b-form-input>

              <label> Medication Form:</label>
              <b-form-select
                id="inline-form-custom-select-pref"
                v-model="newMedication.medicationForm"
                aria-describedby="input-live-help input-live-feedback"
                :state="medicationFormState"
                class="mb-2 mr-sm-2 mb-sm-0"
                :options="[
                  'PRASAK',
                  'KAPSULA',
                  'TABLETA',
                  'MAST',
                  'GEL',
                  'RASTVOR',
                  'SIRUP',
                ]"
                :value="null"
              ></b-form-select>

              <label> Type of medication:</label>
              <b-form-select
                id="inline-form-custom-select-pref"
                v-model="newMedication.typeOfMedication"
                :state="typeOfMedicationState"
                class="mb-2 mr-sm-2 mb-sm-0"
                :options="[
                  'ANTIPYRETIC',
                  'ANALGESIC',
                  'ANTIBIOTIC',
                  'ANTISEPTIC',
                  'ANTIDEPRESSANT',
                  'ANTIALERGIC',
                  'BARBITURATE',
                  'HORMONE',
                  'CONTRACEPTIVE',
                  'PROBIOTIC',
                  'TRANQUILIZER',
                ]"
                :value="null"
              ></b-form-select>
              <label> Presciption regime:</label>
              <b-form-select
                id="inline-form-custom-select-pref"
                v-model="newMedication.prescriptionRegime"
                :state="prescriptionRegimeState"
                class="mb-2 mr-sm-2 mb-sm-0"
                :options="['NA_RECEPT', 'BEZ_RECEPTA']"
                :value="null"
              ></b-form-select>
              <label for="input-live">Manufacturer:</label>
              <b-form-input
                id="input-live"
                v-model="newMedication.manufacturer"
                aria-describedby="input-live-help input-live-feedback"
                placeholder="Enter manufacturer"
                :state="manufacturerState"
                trim
              ></b-form-input>
              <label for="input-live">Decription:</label>
              <b-form-input
                id="input-live"
                v-model="newMedication.description"
                aria-describedby="input-live-help input-live-feedback"
                placeholder="Enter description"
                :state="descriptionState"
                trim
              ></b-form-input>

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
              <b-button variant="success" @click="addNewMedication"
                >Add medicine</b-button
              >
              <b-button variant="warning" @click="clearField"
                >Clear fields</b-button
              >
              <b-button variant="danger" @click="resetFormNewMedication">Reset </b-button>
              <b-button variant="success" @click="sendPurchaseOrderAndCreateMedication"
                >Submit</b-button
              >
              <br />
              <label>Meds: Quantity:</label>
              <b-list-group
                v-for="(listItem, index) in newMedicationOrder"
                v-bind:key="index"
                class="medication_list_scrollable"
              >
                <b-list-group-item>
                  {{ listItem.name }}
                  {{ listItem.quantity }}</b-list-group-item
                >
              </b-list-group>
            </div>
          </b-tab>
          <b-tab title="Order existing medication">
           
          </b-tab>
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
    nameState() {
      return this.newMedication.name.length > 2 ? true : false;
    },
    medicationFormState() {
      return this.newMedication.medicationForm.length > 1 ? true : false;
    },
    typeOfMedicationState() {
      return this.newMedication.typeOfMedication.length > 1 ? true : false;
    },
    manufacturerState() {
      return this.newMedication.manufacturer.length > 3 ? true : false;
    },
    descriptionState() {
      return this.newMedication.description.length > 3 ? true : false;
    },
    quantityState() {
      this.newMedication.quantity > 0 ? true : false;
    },
    prescriptionRegimeState() {
      return this.newMedication.prescriptionRegime.length > 1 ? true : false;
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
      newMedicationOrder:[],
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
      newMedication: {
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
        quantity: 1,
      },
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
    resetFormNewMedication(){
      this.newMedicationOrder = []
    },
    clearField() {
      this.newMedication.id = 0;
      this.newMedication.name = "";
      this.newMedication.description = "";
      this.newMedication.manufacturer = "";
      this.newMedication.medicationForm = "";
      this.newMedication.prescriptionRegime = "";
      this.newMedication.code = "";
      this.newMedication.typeOfMedication = "";
      this.newMedication.structure = "";
      this.newMedication.contraindications = "";
      this.newMedication.recommendedIntake = "";
      this.newMedication.ratings = [];
      this.newMedication.quantity = 1;
    },
    addMedicine(name, id) {
      this.currentMedication = name;
      this.currentQuantity = 0;
      this.currentId = id;
    },
    resetMedicationOrder() {
      this.medicationOrder = [];
      this.currentMedication = "";
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

    addNewMedication() {
      if (
        this.nameState &&
        this.medicationFormState &&
        this.typeOfMedicationState &&
        this.descriptionState &&
        this.manufacturerState &&
        this.prescriptionRegimeState
      ) {
        var medication = {
          id: this.newMedication.id,
          name: this.newMedication.name,
          description: this.newMedication.description,
          manufacturer: this.newMedication.manufacturer,
          medicationForm: this.newMedication.medicationForm,
          prescriptionRegime: this.newMedication.prescriptionRegime,
          code: this.newMedication.code,
          typeOfMedication: this.newMedication.typeOfMedication,
          structure: this.newMedication.structure,
          contraindications: this.newMedication.contraindications,
          recommendedIntake: this.newMedication.recommendedIntake,
          ratings: this.newMedication.ratings,
          quantity: this.newMedication.quantity,
        };
        this.newMedicationOrder.push(medication);
      } else {
        alert("Please enter medication data!");
      }
    },
    sendPurchaseOrderAndCreateMedication() {
      var vm = this;
                  const body = {
        startTime: vm.datepicker_1,
        endTime: vm.datepicker_2,
        medicationDTO: this.newMedicationOrder,

        adminId: vm.userId,
      };

            fetch(
            `http://localhost:9005/api/pharmacy/purchaseorder-create-new-medication/${vm.pharmacy_id}`,
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
            alert("Purchase order made");
            return response.json();
          } else {
            return Promise.reject(response);
          }
        })


      // fetch(`http://localhost:9005/api/medication/addMultiple`, {
      //   headers: {
      //     Accept: "application/json",
      //     "Content-Type": "application/json",
      //   },
      //   method: "POST",
      //   body: JSON.stringify(vm.newMedicationOrder),
      // })
      //   .then(function(response) {
      //     if (response.ok) {
      //       return response.json();
      //     } else {
      //       return Promise.reject(response);
      //     }
      //   })
      //   .then(function(data) {
      //             const body = {
      //   startTime: vm.datepicker_1,
      //   endTime: vm.datepicker_2,
      //   medicationDTO: data,

      //   adminId: vm.userId,
      // };
      //     return fetch(
      //       `http://localhost:9005/api/pharmacy/purchaseorder-create/${vm.pharmacy_id}`,
      //       {
      //         headers: {
      //           Accept: "application/json",
      //           "Content-Type": "application/json",
      //         },
      //         method: "POST",
      //         body: JSON.stringify(body),
      //       }
      //     );
      //   })
      //   .then(function(response) {
      //     if (response.ok) {
      //       alert("Purchase order made");
      //       return response.json();
      //     } else {
      //       return Promise.reject(response);
      //     }
      //   })

      //   .catch(function(error) {
      //     console.warn(error);
      //   });
    },

    sendPurchaseOrder() {
      if (this.medicationOrder.length > 0) {
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
              alert("Purchase order made");
              return response.json();
            } else {
              return Promise.reject(response);
            }
          })
          .catch(function(error) {
            console.warn(error);
          });
      } else {
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
