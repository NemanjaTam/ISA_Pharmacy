<template>
    <div class="background">
        <TopMenuForPD/>
        <div>
            <b-form-datepicker v-model="selectedDate" v-on:input="dateSelected" class="boxDatePicker" placeholder="Please select date"></b-form-datepicker>
            <div v-if="this.show" style="text-align: center;">
                <label style="color: green;font-size:30px;"><b>{{this.showMessage}}</b></label>
            </div>
            <div v-else class="table-responsive">
                <table class="table table-hover boxTable">
                <thead class="thead-dark topLeft topRight">
                    <tr>
                        <th style="text-align: center" class="topLeft" scope="col">Type</th>
                        <th style="text-align: center" scope="col">Name of patient</th>
                        <th style="text-align: center" scope="col">Start time</th>
                        <th style="text-align: center" scope="col">End time</th>
                        <th class="topRight" style="text-align: center" scope="col">Start</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="appointment in appointments" :key="appointment.id" class="table-light" style="text-align: center">
                        <td>{{getType}}</td>
                        <td>{{appointment.patient.name}} {{appointment.patient.surname}} </td>
                        <td>{{appointment.period.startTime}}</td>
                        <td>{{appointment.period.endTime}}</td>
                        <td style="width: 210px">
                            <router-link to="/examinationCheck" class="routerlink">
                                <b-button class="button" @click="start(appointment)">Start {{getType}}</b-button>
                            </router-link>
                        </td>
                    </tr>
                </tbody>
            </table>
            </div>
        </div>
    </div>
</template>

<script>

import axios from 'axios'
import TopMenuForPD from '../components/TopMenuForPD'

export default {
    name: 'WorkCalendar',
    components: {
        TopMenuForPD
    },
    computed: {
        user() {
            return this.$store.getters.getUser
        },
        getType() {
            return this.$store.getters.getType
        },
        appointments() {
            return this.$store.getters.getAppointments
        },
        userType() {
            return this.$store.getters.getUserType
        }

    },
    data() {
        return {
            Check: {
                selectedDate: null,
                userId: null
            },
            selectedDate: null,
            show: false,
            showMessage: ''
        }
    },
    methods: {
        dateSelected() {
            if(this.selectedDate == null) return
            this.Check.userId = this.user.id
            var date = this.getDateTimeFromString(this.selectedDate, "00:00").getTime()
            this.Check.selectedDate = date
            axios.post("http://localhost:9005/api/absence/check-is-user-on-absence", this.Check)
                .then(r => {
                    if(r.data == "on_absence") {
                        this.showMessage = 'You are on vacation!'
                        this.show = true
                        return
                    }
                    else {
                        axios.post("http://localhost:9005/api/workday/check-is-user-working", this.Check)
                            .then(r => {
                                if(r.data == "not_working") {
                                    this.showMessage = 'You are not working today!'
                                    this.show = true
                                    return
                                }
                                else {
                                    this.showMessage = ''
                                    this.show = false
                                    if(this.userType == "DERMATOLOGIST") {
                                        axios.post("http://localhost:9005/api/appointment/get-all-scheduled-appointments", this.Check)
                                            .then(r => {
                                                var appointments = JSON.parse(JSON.stringify(r.data))
                                                this.$store.dispatch('updateAppointments', appointments)
                                            })
                                    }
                                    else {
                                        axios.post("http://localhost:9005/api/consultation/get-all-scheduled-consultations", this.Check)
                                            .then(r => {
                                                console.log(r.data)
                                                var appointments = JSON.parse(JSON.stringify(r.data))
                                                this.$store.dispatch('updateAppointments', appointments)
                                            })                                        
                                    }

                                }
                            })
                    }
                    
                })
        },
        // Expected yy-mm-dd and HH:mm format
        getDateTimeFromString: function(dstr, tstr) {
            let dparts = dstr.split('-');
            let tparts = tstr.split(':');
            // -1 because js counts months from 0
            return new Date(dparts[0], dparts[1] - 1, dparts[2], tparts[0], tparts[1]);
        },
        start(appointment) {
            this.$store.dispatch('updateCurrentAppointment', appointment)
        }
    }
}
</script>

<style scoped>
    .background {
        background-image: url("../assets/img/medicine.jpg");
        position: absolute; 
        top: 0; 
        left: 0; 
        min-width: 100%;
        min-height: 100%;
    } 

    .boxDatePicker {
        width: 30vw;
        margin: 0 auto;
        margin-top: 20px;
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