import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

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
    pharmacy: null,
  },
  //methods that return data (state)
  getters: {
    getUser(state) {
      return state.user;
    },
    getPharmacy(state) {
      return state.pharmacy;
    },
    getFullName(state) {
      return state.user.name + " " + state.user.surname;
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
  },
  //methods for changing date (state)
  mutations: {
    updateUser(state, user) {
      state.user = user;
    },
    updatePassword(state, password) {
      state.user.password = password;
      if (state.user.firstTimeLogging) {
        state.user.firstTimeLogging = false;
      }
    },
    updatePharamcy(state, pharmacy) {
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
  },
  //always on components dispatch action which commit some mutations. Never commit mutations from component because of async
  actions: {
    updateUser(context, user) {
      context.commit("updateUser", user);
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
    updatePharmacy(context, pharmacy) {
      context.commit("updatePharmacy", pharmacy);
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
      context.commit("updateUser", user);
    },
  },
});
