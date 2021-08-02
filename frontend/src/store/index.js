import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  //states (data) can be edided only through mutations
  strict: true,
  // this is like data:
  state: {
    user: {
      id: 1,
      name: '',
      surname: '',
      email: '',
      password: '',
      phone: '',
      address: {
        state: '',
        city: '',
        street: '',
        number: '',
        postalCode: null
      },
      userType: null,
      firstTimeLogging: null,
      inbox: [],
      send: [],
      medications: []
    },
    appointments: [],
    appointmentProcess: null
  },
  //methods that return data (state)
  getters: {
    getUser(state) {
        return state.user
    },
    getFullName(state) {
       return state.user.name + " " + state.user.surname
    },
    isUserLogged(state) {
      if(state.user.id != null) {
        return true
      }
      else {
        return false
      }
    },
    getUserType(state) {
      return state.user.userType
    },
    isDermatologist(state) {
      if(state.user.userType == "DERMATOLOGIST") return true
      else return false
    },
    getType(state) {
      if(state.user.userType == "DERMATOLOGIST") return "Examination"
      else return "Consultation"
    },
    getAppointments(state) {
      return state.appointments
    },
    getCurrentAppointment(state) {
      return state.appointmentProcess
    }
  },
  //methods for changing date (state)
  mutations: {
    updateUser(state, user) {
      state.user = user;
    },
    updatePassword(state, password) {
      state.user.password = password;
      if(state.user.firstTimeLogging) {
        state.user.firstTimeLogging = false
      }
    },
    updateAppointments(state, appointments) {
      state.appointments = appointments;
    },
    updateCurrentAppointment(state, appointment) {
      state.appointmentProcess = appointment;
    },
    addTherapy(state, therapy) {
      state.appointmentProcess.therapies.push(therapy)
    },
    addReport(state, report) {
      state.appointmentProcess.report = report
    },
    setMedications(state, medications) {
      state.medications = medications;
    }
  },
  //always on components dispatch action which commit some mutations. Never commit mutations from component because of async
  actions: {
    updateUser(context, user) {
      context.commit('updateUser', user)
    },
    updatePassword(context, password) {
      context.commit('updatePassword', password)
    },
    updateAppointments(context, appointments) {
      context.commit('updateAppointments', appointments)
    },
    updateCurrentAppointment(context, appointment) {
      context.commit('updateCurrentAppointment', appointment)
    },
    addTherapy(context, therapy) {
      context.commit('addTherapy', therapy)
    },
    addReport(context, report) {
      context.commit('addReport', report)
    },
    logout(context) {
      var user = {
        id: null,
        name: '',
        surname: '',
        email: '',
        password: '',
        phone: '',
        address: {
          state: '',
          city: '',
          street: '',
          number: '',
          postalCode: null
        },
        userType: null,
        firstTimeLogging: null,
        inbox: [],
        send: []
      }
      context.commit('updateUser', user)
    },
    setMedications(context, medications) {
      context.commit('setMedications', medications);
    }
  }
})
