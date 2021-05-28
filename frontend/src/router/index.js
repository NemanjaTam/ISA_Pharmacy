import Vue from 'vue'
import VueRouter from 'vue-router'
import RegistrationPage from '../views/RegistrationPage.vue'
import Home from '../views/Home.vue'
import LoginPage from '../views/LoginPage.vue'
import AddMedication from '../components/AddMedication.vue'
import Medications from '../components/Medications.vue'
import RegistrationByAdmin from '../views/RegistrationByAdmin.vue'

Vue.use(VueRouter)

const routes = [
  
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/registration',
    name: 'RegistrationPage',
    component: RegistrationPage
  },
  {
    path: '/login',
    name: 'LoginPage',
    component: LoginPage
  },
  {
    path: '/add-medication',
    name: 'AddMedication',
    component: AddMedication
  },
  {
    path: '/medications',
    name: 'Medications',
    component: Medications
  },
  {
    path: '/registration-by-admin',
    name: 'RegistrationByAdmin',
    component: RegistrationByAdmin
  }
  

  // /pharmacies          -komponenta koja ce da prikazuje sve apoteke
  // /pharmacies/{:id}    -komponenta koja prikazuje konkretnu apoteku
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
