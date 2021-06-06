<template>
    <div class="background">
        <TopMenuForPD/>
        <div class="test">
            <div>
                <label for="exampleTextarea" style="margin-left: 1vw" class="form-label mt-4">Description:</label>
                <textarea v-model="report" class="form-control textBox" id="exampleTextarea" rows="3"></textarea>
            </div>
            <div>
                <b-button class="button" v-on:click="showFormForScheduling">{{buttonSchedulingText}}</b-button>
                <b-button class="button" v-on:click="showFormForMedication">{{buttonMedicationText}}</b-button>
                <Scheduling class="form" v-if="showScheduling"/>
                <MedicationTherapy class="form" v-if="showMedication"/>
            </div>
           
        </div>
        <div>
            <b-button class="buttonFinish" v-on:click="finish" style="float:right">Finish examination</b-button>
        </div>
    </div>
</template>

<script>

import TopMenuForPD from '../components/TopMenuForPD.vue'
import Scheduling from '../components/Scheduling.vue'
import MedicationTherapy from '../components/MedicationTherapy.vue'
import axios from 'axios'

export default {
    name: 'ExaminationProcess',
    components: {
        TopMenuForPD,
        Scheduling,
        MedicationTherapy
    },
    computed: {
        appointment() {
            return this.$store.getters.getCurrentAppointment
        }
    },
    data() {
        return {
            showScheduling: false,
            buttonSchedulingText: 'Schedule new examination',
            showMedication: false,
            buttonMedicationText: 'Prescribe medication/therapy',
            report: ''
        }
    },
    methods: {
        showFormForScheduling() {
            if(this.showScheduling == false) {
                this.showMedication = false
                this.buttonMedicationText = "Prescribe medication/therapy"
                this.showScheduling = true
                this.buttonSchedulingText = "Hide form for scheduling"
            }
            else {
                this.showScheduling = false,
                this.buttonSchedulingText = "Schedule new examination"
            }
        },
        showFormForMedication() {
            if(this.showMedication == false) {
                this.showScheduling = false
                this.buttonSchedulingText = "Schedule new examination"
                this.showMedication = true
                this.buttonMedicationText = "Hide form for medication/therapy"
            }
            else {
                this.showMedication = false,
                this.buttonMedicationText = "Prescribe medication/therapy"
            }
        },
        finish() {
            if(this.report === '') {
                alert("Please fill some report!");
                return
            }
            var finish = {
                appointmentId: this.appointment.id,
                report: this.report,
                therapies: this.appointment.therapies
            }
            axios.post("http://localhost:9005/api/appointment/finish-appointment", finish)
            this.$router.push('HomePage')
        }
    }
}
</script>

<style scoped>
    .test {
        display: flex;
    }

    .form {
        margin-left: 2vw;
        margin-right: 2vw;
        width:65vw;
    }
    .background {
        background-image: url("../assets/img/medicine.jpg");
        position: absolute;
        top: 0; 
        left: 0; 
        min-width: 100%;
        min-height: 100%;
    }

    .div1 {
        float: left;
    }

    .button {
        margin-top: 2.9vw;
        margin-left: 2vw;
        transition: 0.25s;
        border-radius: 20px;
    }

    .buttonFinish {
        margin-right: 20px;
        transition: 0.25s;
        border-radius: 20px;
    }

    .center {
        width: 30vw;
        margin: 0 auto;
        margin-top: 20px;
        text-align: center;
        transition: 0.25s;
    }

    .textBox {
        width: 30vw;
        height: 48vw;
        margin-left: 0.5vw;
        box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
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

</style>