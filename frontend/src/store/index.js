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
    medicines:[{}],
    //ne menjaj ovo
    selectedOffer: {},
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
    getMedicines(state){
      return state.medicines
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
    getOffer(state){
      return state.purchaseOrders.offers;
    }
  },
  //methods for changing date (state)
  mutations: {
    updateUser(state, user) {
      state.user = user;
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
      const index = state.getPurchaseOrders.findIndex((item) => item.id === payload.id);
      if (index !== -1) {
        Vue.set(state.getPurchaseOrders, index, payload);
      }
    },
    updateMedicines(state,medicines){
      state.medicines = medicines;
    },
    updatePurchaseOrderOffer(state,payload){
     state.purchaseOrders.forEach(element => {
      const index = element.offers.findIndex((item) => item.id === payload.id);
      if (index !== -1) {
        Vue.set(element.offers, index, payload);
      }
     });
    },
    removeMedicine(state,medicine){
      state.medicines.splice(state.medicines.indexOf(medicine), 1);
    }
  },
  //always on components dispatch action which commit some mutations. Never commit mutations from component because of async
  actions: {
    updateUser(context, user) {
      context.commit("updateUser", user);
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
    updateCurrentAppointment(context, appointment) {
      context.commit("updateCurrentAppointment", appointment);
    },
    addTherapy(context, therapy) {
      context.commit("addTherapy", therapy);
    },
    addReport(context, report) {
      context.commit("addReport", report);
    },
    updatePurchaseOrderElement(context,elemet){
      context.commit("updatePurchaseOrderElement",elemet);
    },
    updatePurchaseOrderOffer(context,element){
      context.commit("updatePurchaseOrderOffer",element);
    },
    updateMedicines(context,element){
      context.commit("updateMedicines",element);
    },
    removeMedicine(context,medicine){
      context.commit("removeMedicine",medicine);
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
