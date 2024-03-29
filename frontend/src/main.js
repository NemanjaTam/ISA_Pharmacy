import Vue from 'vue'
import App from './App.vue'
import { router } from './router'
import { store } from './store'
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import * as VueGoogleMaps from 'vue2-google-maps'

Vue.use(require('vue-moment'));
Vue.config.productionTip = false
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)




Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyAutFBlxBxeE1RhyTQNzZtrbaM5W2xiNkc",
    libraries: "places"
  }
});

const app = new Vue({
  router: router,
  store: store,
  render: h => h(App)
}).$mount('#app')
