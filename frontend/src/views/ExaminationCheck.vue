<template>
    <div class="background">
        <TopMenuForPD/>
        <router-link to="/examinationProcess">
            <b-button class="boxDatePicker">Start {{getType}}</b-button>
        </router-link>
        <router-link to="/homePage">
            <b-button v-on:click="patientDidntShowUp" class="boxDatePicker">The patient didn't show up</b-button>
        </router-link>
    </div>
</template>

<script>

import axios from 'axios'
import TopMenuForPD from '../components/TopMenuForPD.vue'

export default {
    name: 'ExaminationCheck',
    components: {
        TopMenuForPD
    },
    computed: {
        getType() {
            return this.$store.getters.getType
        },
        appointment() {
            return this.$store.getters.getCurrentAppointment
        },
        User() {
            return this.$store.getters.getUser
        }
    },
    methods: {
        patientDidntShowUp() {
            var finish = {
                appointmentId: this.appointment.id,
                report: '',
                therapies: []
            }
            axios.post("http://localhost:9005/api/appointment/finish-appointment", finish)
            axios.get("http://localhost:9005/api/patient/add-penalty/" + this.appointment.patient.id)
            this.$store.dispatch('updateAppointments', null)
            this.$store.dispatch('updateCurrentAppointment', null)
        }
    }
}
</script>

<style scoped>
    .background {
        background-image: url("../assets/img/medicine.jpg");
        position: fixed;
        text-align: center;
        top: 0; 
        left: 0; 
        min-width: 100%;
        min-height: 100%;
    } 

    .center {
        width: 30vw;
        margin: 0 auto;
        margin-top: 20px;
        text-align: center;
        transition: 0.25s;
    }

    .boxDatePicker {
        width: 30vw;
        margin: 0 auto;
        margin: 10vw;
        box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
        text-align: center;
        transition: 0.25s;
        border-radius: 20px;
    }

    .boxTable {
        width: 80vw;
        margin: 0 auto;
        margin-top: 20px;
        /* box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px; */
        border-style:hidden;
        border-radius: 20px;
    }

    .bottomLeft {
        border-bottom-left-radius: 20px;
    }

    .bottomRight {
        border-bottom-right-radius: 20px;
    }

    .topRight {
        border-top-right-radius: 20px;
    }

    .topLeft {
        border-top-left-radius: 20px;
    }

    .buttonColumn {
        width: 180px;
        
    }

</style>