import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import RegistrationPage from '../views/RegistrationPage'
import Login from '../views/Login'
import AdminPage from '../views/AdminPage'
import AddPharmacy from '../views/AddPharmacy'
import AddMedication from '../views/AddMedication'
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
    name: 'Login',
    component: Login
  },
  {
  path: '/pharmacies',
  name: 'AdminPage',
  component: AdminPage
  },
  {
    path: '/addPharmacy',
    name: 'AddPharmacy',
    component: AddPharmacy
  },
  {
    path: '/addMedication',
    name: 'AddMedication',
    component: AddMedication
  }
]

const router = new VueRouter({
  routes // short for `routes: routes`
})

export default router
