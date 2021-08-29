import Vue from "vue";
import VueRouter from "vue-router";
import RegistrationPage from "../views/RegistrationPage.vue";
import Home from "../views/Home.vue";
import LoginPage from "../views/LoginPage.vue";
import HomePage from "../views/HomePage.vue";
import UpdateBasicInformation from "../views/UpdateBasicInformation";
import FirstTimeLogging from "../views/FirstTimeLogging.vue";
import WorkCalendar from "../views/WorkCalendar.vue";
import AllClients from "../views/AllClients.vue";
import ExaminationCheck from "../views/ExaminationCheck.vue";
import ScheduleExamination from "../views/ScheduleExamination.vue";
import PerscriptionMedication from "../views/PerscriptionMedication.vue";
import ExaminationProcess from "../views/ExaminationProcess.vue";
import MedicationTherapy from "../components/MedicationTherapy.vue";
import Calendar from "../components/Calendar.vue";
import PharmacyAdmin from "../views/PharmacyAdmin.vue";
import settingsPage from "../views/SettingsPage.vue";
import PharmacyAdminHome from "../views/PharmacyAdminHome.vue";
import Pharmacy from "../views/Pharmacy.vue";
import SearchListFilter from "../components/SearchListFilter.vue";
import Action from "../components/Action.vue";
import PurchaseOrder from "../components/PurchaseOrder.vue"
import Medication from "../views/MedicationList.vue"
import Absences from "../components/Absences.vue"
import PriceList from "../components/PriceList.vue"
import Medications from "../components/Medications.vue"
import RegistrationSystemAdmin from "../components/RegistrationSystemAdmin.vue"
import AddMedication from "../components/AddMedication.vue"

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
    path: "/calendar",
    name: "Calendar",
    component: Calendar,
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
      },{
        path:"pricelist",
        name:"pricelist",
        component: PriceList,
      }
    ],
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
        path: '/registration-by-system-admin',
        name: 'RegistrationSystemAdmin',
        component: RegistrationSystemAdmin
      }
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
