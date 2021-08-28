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
import AddMedication from '../components/AddMedication.vue'
import Medications from '../components/Medications.vue'
import RegistrationPharmacyAdmin from '../views/RegistrationPharmacyAdmin.vue'
import RegistrationDermatologist from '../views/RegistrationDermatologist.vue'
import RegistrationSupplier from '../views/RegistrationSupplier.vue'
import RegistrationSystemAdmin from '../views/RegistrationSystemAdmin.vue'
import RegistrationByAdmin from '../components/RegistrationByAdmin.vue'
import AddPharmacy from '../components/AddPharmacy.vue'
import MakePurchaseOrderOffer from '../components/MakePurchaseOrderOffer.vue'
import NavbarForAdminAndSupplier from '../components/NavbarForAdminAndSupplier.vue'
import EditProfileForAdminAndSupplier from '../views/EditProfileForAdminAndSupplier.vue'
import PharmacyAdmin from "../views/PharmacyAdmin.vue";
import settingsPage from "../views/SettingsPage.vue";
import PharmacyAdminHome from "../views/PharmacyAdminHome.vue";
import Pharmacy from "../views/Pharmacy.vue";
import SearchListFilter from "../components/SearchListFilter.vue";
import Action from "../components/Action.vue";
import PurchaseOrder from "../components/PurchaseOrder.vue"
import Medication from "../views/MedicationList.vue"
import Absences from "../components/Absences.vue"
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/registrationPage",
    name: "RegistrationPage",
    component: RegistrationPage,
  },
  {
    path: "/loginPage",
    name: "LoginPage",
    component: LoginPage,
  },
  {
    path: "/homePage",
    name: "HomePage",
    component: HomePage,
  },
  {
    path: "/updateBasicInformation",
    name: "UpdateBasicInformation",
    component: UpdateBasicInformation,
  },
  {
    path: "/firstTimeLogging",
    name: "FirstTimeLogging",
    component: FirstTimeLogging,
  },
  {
    path: "/workCalendar",
    name: "WorkCalendar",
    component: WorkCalendar,
  },
  {
    path: "/allClients",
    name: "AllClients",
    component: AllClients,
  },
  {
    path: "/examinationCheck",
    name: "ExaminationChcek",
    component: ExaminationCheck,
  },
  {
    path: "/scheduleExamination",
    name: "ScheduleExamination",
    component: ScheduleExamination,
  },
  {
    path: "/perscriptionMedication",
    name: "PerscriptionMedication",
    component: PerscriptionMedication,
  },
  {
    path: "/examinationProcess",
    name: "ExaminationProcess",
    component: ExaminationProcess,
  },
  {
    path: "/medicationTherapy",
    name: "MedicationTherapy",
    component: MedicationTherapy,
  },
  {
    path: '/calendar',
    name: 'Calendar',
    component: Calendar
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
    path: '/registration-pharmacy-admin',
    name: 'RegistrationPharmacyAdmin',
    component: RegistrationPharmacyAdmin
  },
  { 
    path: '/registration-dermatologist',
    name: 'RegistrationDermatologist',
    component: RegistrationDermatologist
  },
  { 
    path: '/registration-supplier',
    name: 'RegistrationSupplier',
    component: RegistrationSupplier
  },
  { 
    path: '/registration-system-admin',
    name: 'RegistrationSystemAdmin',
    component: RegistrationSystemAdmin
  },
  { 
    path: '/registration-by-admin',
    name: 'RegistrationByAdmin',
    component: RegistrationByAdmin
  },
  {
    path: '/add-pharmacy',
    name: 'AddPharmacy',
    component: AddPharmacy
  },
  {
    path: '/make-purchase-order-offer',
    name: 'MakePurchaseOrderOffer',
    component: MakePurchaseOrderOffer
  },
  {
    path: '/navbar-for-admin-and-supplier',
    name: 'NavbarForAdminAndSupplier',
    component: NavbarForAdminAndSupplier
  },
  {
    path: '/edit-profile',
    name: 'EditProfileForAdminAndSupplier',
    component: EditProfileForAdminAndSupplier
  },
  {
    path: "/pharmacy/:id",
    name: "Pharmacy",
    component: Pharmacy,
  },
  {
    path: "/pharmacyAdminPage",
    name: "PharmacyAdminPage",
    component: PharmacyAdmin,
    children: [
      {
        path: "settingsPage",
        name: "settingsPage",
        component: settingsPage,
      },
      {
        path: "homeAdmin",
        name: "homeAdmin",
        component: PharmacyAdminHome,
      },
      {
        path: "list",
        name: "list",
        component: SearchListFilter,
      },
      {
        path: "action",
        name: "action",
        component: Action,
      },
      {
        path:"purchaseorder",
        name:"purchaseOrder",
        component: PurchaseOrder,
      },{
        path:"medication",
        name:"medication",
        component: Medication,
      },{
        path:"absences",
        name:"absences",
        component: Absences,
      },
    ],
  },
];


export const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

// router.beforeEach((to, from, next) => {
//   if (to.matched.some((record) => record.meta.requiresAuth)) {
//     if (store.getters.isAuthenticated) {
//       next();
//       return;
//     }
//     next("/login");
//   } else {
//     next();
//   }
// });
