import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      count: 0
    },
  },
  mutations: {
    setUserType (state) {
      state.user.count++;
    }
  },
  actions: {
    increment (context) {
      context.commit('setUserType')
    }
  },
  modules: {
  }
})
