import Vue from 'vue'
import VueRouter from 'vue-router'
import RegistrationPage from '../views/RegistrationPage.vue'
import Home from '../views/Home.vue'
import LoginPage from '../views/LoginPage.vue'
import HomePage from '../views/HomePage.vue'
import UpdateBasicInformation from '../views/UpdateBasicInformation'
import FirstTimeLogging from '../views/FirstTimeLogging.vue'
import WorkCalendar from '../views/WorkCalendar.vue'
import AllClients from '../views/AllClients.vue'
import ExaminationCheck from '../views/ExaminationCheck.vue'
import ScheduleExamination from '../views/ScheduleExamination.vue'
import PerscriptionMedication from '../views/PerscriptionMedication.vue'
import ExaminationProcess from '../views/ExaminationProcess.vue'
import MedicationTherapy from '../components/MedicationTherapy.vue'
import Calendar from '../components/Calendar.vue'
import PharmacyAdmin from '../views/PharmacyAdmin.vue'
import settingsPage from '../views/SettingsPage.vue'
import PharmacyAdminHome from '../views/PharmacyAdminHome.vue'


Vue.use(VueRouter)

const routes = [
  
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/registrationPage',
    name: 'RegistrationPage',
    component: RegistrationPage
  },
  {
    path: '/loginPage',
    name: 'LoginPage',
    component: LoginPage
  },
  {
    path: '/homePage',
    name: 'HomePage',
    component: HomePage
  },
  {
    path: '/updateBasicInformation',
    name: 'UpdateBasicInformation',
    component: UpdateBasicInformation
  },
  {
    path: '/firstTimeLogging',
    name: 'FirstTimeLogging',
    component: FirstTimeLogging
  },
  {
    path: '/workCalendar',
    name: 'WorkCalendar',
    component: WorkCalendar
  },
  {
    path: '/allClients',
    name: 'AllClients',
    component: AllClients
  },
  {
    path: '/examinationCheck',
    name: 'ExaminationChcek',
    component: ExaminationCheck
  },
  {
    path: '/scheduleExamination',
    name: 'ScheduleExamination',
    component: ScheduleExamination
  },
  {
    path: '/perscriptionMedication',
    name: 'PerscriptionMedication',
    component: PerscriptionMedication
  },
  {
    path: '/examinationProcess',
    name: 'ExaminationProcess',
    component: ExaminationProcess
  },
  {
    path: '/medicationTherapy',
    name: 'MedicationTherapy',
    component: MedicationTherapy
  },
  {
    path: '/calendar',
    name: 'Calendar',
    component: Calendar
  },{
    path:'/pharmacyAdminPage',
    name:'/PharmacyAdminPage',
    component: PharmacyAdmin,
    children:[
      {
        path:'settingsPage',
        name: 'settingsPage',
        component: settingsPage
      },{
        path:'homeAdmin',
        name:'homeAdmin',
        component: PharmacyAdminHome

      }
    ]
  }
]

export const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})