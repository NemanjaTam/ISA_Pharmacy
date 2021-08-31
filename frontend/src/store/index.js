import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);
import createPersistedState from "vuex-persistedstate";

export const store = new Vuex.Store({
  //states (data) can be edided only through mutations
  strict: true,
  // this is like data:
  state: {
    user: {
      id: 1,
      name: "",
      surname: "",
      email: "",
      password: "",
      phone: "",
      address: {
        state: "",
        city: "",
        street: "",
        number: "",
        postalCode: null,
      },
      userType: null,
      firstTimeLogging: null,
      inbox: [],
      send: [],
    },
    appointments: [],
    appointmentProcess: null,
    pharmacy: 0, //ne menjati ovo se koristi vec!
    //ne menjaj ovo
    purchaseOrders: [
      {
        id: 0,
        quantityMedicationPurchase: [],
        adminPharmacy: {},
        purchaseOrderStatus: "",
        startTime: "",
        endTime: "",
        pharmacy: {},
        offers: [],
      },
    ],
    //ne menjati nista
    medicines: [{}],
    //ne menjaj ovo
    selectedOffer: {},
    seletedMedicineForEdit: {},
    unnaprovedAbsences: [],
    approvedAbsences: [],
    priceListActive: [],
    priceListInactive: [],
    medications: [],
  },
  //methods that return data (state)
  getters: {
    getUser(state) {
      return state.user;
    },
    getPurchaseOrders(state) {
      return state.purchaseOrders;
    },
    getPharmacy(state) {
      //ne menjati ovo se koristi vec!
      return state.pharmacy;
    },
    getFullName(state) {
      return state.user.name + " " + state.user.surname;
    },
    getSelectedOfer(state) {
      return state.selectedOffer;
    },
    getSelectedMedicineForEdit(state) {
      return state.seletedMedicineForEdit;
    },
    getMedicines(state) {
      return state.medicines;
    },
    isUserLogged(state) {
      if (state.user.id != null) {
        return true;
      } else {
        return false;
      }
    },
    getUserType(state) {
      return state.user.userType;
    },
    isDermatologist(state) {
      if (state.user.userType == "DERMATOLOGIST") return true;
      else return false;
    },
    isRegisteredUser(state) {
      if (state.user.userType == "PATIENT") return true;
      else return false;
    },
    isPharmacyAdministrator(state) {
      if (state.user.userType == "PHARMACY_ADMINISTRATOR") return true;
      else return false;
    },
    getType(state) {
      if (state.user.userType == "DERMATOLOGIST") return "Examination";
      else return "Consultation";
    },
    getAppointments(state) {
      return state.appointments;
    },
    getCurrentAppointment(state) {
      return state.appointmentProcess;
    },
    getId(state) {
      return state.user.id;
    },
    getOffer(state) {
      return state.purchaseOrders.offers;
    },
    getUnnaprovedAbsences(state) {
      return state.unnaprovedAbsences;
    },
    getApprovedAbsences(state) {
      return state.approvedAbsences;
    },
    getPriceListActive(state) {
      return state.priceListActive;
    },
    getPriceListInactive(state) {
      return state.priceListInactive;
    },
  },
  //methods for changing date (state)
  mutations: {
    updateUser(state, user) {
      state.user = user;
    },
    updateName(state, name) {
      state.user.name = name;
    },
    updateEmail(state, surname) {
      state.user.surname = surname;
    },
    updatePassword(state, password) {
      state.user.password = password;
    },
    updatePhone(state, phone) {
      state.user.phone = phone;
    },
    updateCity(state, city) {
      state.user.address.city = city;
    },
    updateState(state, stat) {
      state.user.address.state = stat;
    },
    updateNumber(state, number) {
      state.user.address.number = number;
    },
    updatePostalCode(state, postalCode) {
      state.user.address.postalCode = postalCode;
    },
    updatePriceListActive(state, pricelist) {
      state.priceListActive = pricelist;
    },
    updatePriceListInactive(state, pricelist) {
      state.priceListInactive = pricelist;
    },
    updateSelectedOffer(state, selectedOffer) {
      state.selectedOffer = selectedOffer;
    },
    updateSelectedOffer(state, selectedOffer) {
      state.selectedOffer = selectedOffer;
    },
    updatePassword(state, password) {
      state.user.password = password;
      if (state.user.firstTimeLogging) {
        state.user.firstTimeLogging = false;
      }
    },
    updatePurchaseOrders(state, purchaseOrders) {
      state.purchaseOrders = purchaseOrders;
    },
    updateSelectedMedicineForEdit(state, edit) {
      state.seletedMedicineForEdit = edit;
    },
    updatePharmacy(state, pharmacy) {
      //ne menjati ovo se koristi vec!
      state.pharmacy = pharmacy;
    },
    updateAppointments(state, appointments) {
      state.appointments = appointments;
    },
    updateCurrentAppointment(state, appointment) {
      state.appointmentProcess = appointment;
    },
    addTherapy(state, therapy) {
      state.appointmentProcess.therapies.push(therapy);
    },
    addReport(state, report) {
      state.appointmentProcess.report = report;
    },
    updateUserType(state, report) {
      state.user.userType = report;
    },
    updatePurchaseOrderElement(state, payload) {
      const index = state.getPurchaseOrders.findIndex(
        (item) => item.id === payload.id
      );
      if (index !== -1) {
        Vue.set(state.getPurchaseOrders, index, payload);
      }
    },
    updateMedicines(state, medicines) {
      state.medicines = medicines;
    },
    updatePurchaseOrderOffer(state, payload) {
      state.purchaseOrders.forEach((element) => {
        const index = element.offers.findIndex(
          (item) => item.id === payload.id
        );
        if (index !== -1) {
          Vue.set(element.offers, index, payload);
        }
      });
    },
    removeMedicine(state, medicine) {
      state.medicines.splice(state.medicines.indexOf(medicine), 1);
    },
    updateUnapprovedAbsences(state, absence) {
      state.unnaprovedAbsences = absence;
    },
    updateApprovedAbsences(state, absence) {
      state.approvedAbsences = absence;
    },
    setMedications(state, medications) {
      state.medications = medications;
    },
  },
  //always on components dispatch action which commit some mutations. Never commit mutations from component because of async
  actions: {
    updateUser(context, user) {
      context.commit("updateUser", user);
    },
    updateName(context, name) {
      context.commit("updateName", name);
    },
    updateSurname(context, surname) {
      context.commit("updateSurname", surname);
    },
    updateEmail(context, email) {
      context.commit("updateEmail", email);
    },
    updatePhone(context, phone) {
      context.commit("updatePhone", phone);
    },

    updatePassword(context, password) {
      context.commit("updatePassword", password);
    },

    updateState(context, state) {
      context.commit("updateState", state);
    },
    updateCity(context, city) {
      context.commit("updateCity", city);
    },
    updateStreet(context, street) {
      context.commit("updateStreet", street);
    },
    updateNumber(context, number) {
      context.commit("updateNumber", number);
    },
    updatePostalCode(context, postalCode) {
      context.commit("updatePostalCode", postalCode);
    },


    updatePriceListActive(context, price) {
      context.commit("updatePriceListActive", price);
    },
    updatePriceListInactive(context, price) {
      context.commit("updatePriceListInactive", price);
    },
    updatePharmacy(context, pharmacy) {
      //ne menjati ovo se koristi vec!
      context.commit("updatePharmacy", pharmacy);
    },
    updateSelectedOffer(context, selectedOffer) {
      context.commit("updateSelectedOffer", selectedOffer);
    },
    updatePurchaseOrders(context, purchaseOrders) {
      context.commit("updatePurchaseOrders", purchaseOrders);
    },
    updatePassword(context, password) {
      context.commit("updatePassword", password);
    },
    updateUserType(context, type) {
      context.commit("updateType", type);
    },
    updateAppointments(context, appointments) {
      context.commit("updateAppointments", appointments);
    },
    updateSelectedMedicineForEdit(context, edit) {
      context.commit("updateSelectedMedicineForEdit", edit);
    },
    updateCurrentAppointment(context, appointment) {
      context.commit("updateCurrentAppointment", appointment);
    },
    addTherapy(context, therapy) {
      context.commit("addTherapy", therapy);
    },
    addReport(context, report) {
      context.commit("addReport", report);
    },
    updatePurchaseOrderElement(context, elemet) {
      context.commit("updatePurchaseOrderElement", elemet);
    },
    updatePurchaseOrderOffer(context, element) {
      context.commit("updatePurchaseOrderOffer", element);
    },
    updateMedicines(context, element) {
      context.commit("updateMedicines", element);
    },
    removeMedicine(context, medicine) {
      context.commit("removeMedicine", medicine);
    },
    updateUnapprovedAbsences(context, absence) {
      context.commit("updateUnapprovedAbsences", absence);
    },
    updateApprovedAbsences(context, absence) {
      context.commit("updateApprovedAbsences", absence);
    },
    setMedications(context, medications) {
      context.commit("setMedications", medications);
    },

    logout(context) {
      var user = {
        id: null,
        name: "",
        surname: "",
        email: "",
        password: "",
        phone: "",
        address: {
          state: "",
          city: "",
          street: "",
          number: "",
          postalCode: null,
        },
        userType: null,
        firstTimeLogging: null,
        inbox: [],
        send: [],
      };
      var pharmacy = 0;
      var selectedOffer = {};
      context.commit("updateUser", user);
      context.commit("updatePharmacy", pharmacy);
      context.commit("updateSelectedOffer", selectedOffer);
    },
  },
  plugins: [createPersistedState()],
});
