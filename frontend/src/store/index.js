import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      count: 0
    },
    medications: [ ]
  },
  mutations: {
    setUserType (state) {
      state.user.count++;
    },
    setMedications (state, medications) {
      state.medications = medications;
    } 
  },
  actions: {
    increment (context) {
      context.commit('setUserType')
    },
    setMedications (context, medications) {
      context.commit('setMedications', medications);
    }
  },
  modules: {
  }
})
