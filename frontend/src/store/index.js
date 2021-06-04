import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  //states (data) can be edided only through mutations
  strict: true,
  // this is like data:
  state: {
    user: {
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
    }
  },
  //always on components dispatch action which commit some mutations. Never commit mutations from component because of async
  actions: {
    updateUser(context, user) {
      context.commit('updateUser', user)
    },
    updatePassword(context, password) {
      context.commit('updatePassword', password)
    }
  }
})
