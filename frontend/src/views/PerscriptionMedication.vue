<template>
    <div class="background">
        <TopMenuForPD/>
        <div style="text-align: center;">
            <b-form-input class="boxDatePicker" type="text" v-model="input" v-on:input="search" placeholder="type id of reservation" style="font-style:italic" required trim/>
            <label style="color:red;margin-top:20px;"><b>{{message}}</b></label>
        </div>
        <div class="table-responsive">
                <table class="table table-hover boxTable">
                    <thead class="thead-dark topLeft topRight">
                        <tr>
                            <th style="text-align: center" class="topLeft" scope="col">Name</th>
                            <th style="text-align: center" scope="col">Surname</th>
                            <th style="text-align: center" scope="col">Medication name</th>
                            <th style="text-align: center" scope="col">Pharmacy name</th>
                            <th style="text-align: center" scope="col">Serial number</th>
                            <th class="topRight" style="text-align: center" scope="col">Give medication</th>
                        </tr>
                    </thead>
                    <tbody v-if="show">
                        <tr class="table-light" style="text-align: center">
                            <td>{{this.reservation.patient.name}}</td>
                            <td>{{this.reservation.patient.surname}}</td>
                            <td>{{this.reservation.medication.name}}</td>
                            <td>{{this.reservation.pharmacy.name}}</td>
                            <td>{{this.reservation.serialnumber}}</td>
                            <td style="width: 210px">
                                <router-link to="/scheduleExamination" class="routerlink">
                                    <b-button class="button">Give medication</b-button>
                                </router-link>
                            </td>
                        </tr>
                    </tbody>
            </table>
            </div>
    </div>    
</template>

<script>

import axios from 'axios'
import TopMenuForPD from '../components/TopMenuForPD.vue'

export default {
    name: 'PerscriptionMedication',
    components: {
        TopMenuForPD
    },
    computed: {
        User() {
            return this.$store.getters.getUser
        }
    },
    data() {
        return {
            input: '',
            show: false,
            reservation: null,
            message: ''
        }
    },
    methods: {
        search() {
            if(this.input == '') return
            var check = {
                input: this.input,
                id: this.User.id
            }
            axios.post("http://localhost:9005/api/reservation/get-reservations", check)
                .then(r => {
                    this.reservation = JSON.parse(JSON.stringify(r.data))
                    if(this.reservation.id == null) {
                        this.show = false;
                        this.message = "Reservation with given serial number doesn not exists or it is expired"
                    }
                    else {
                        this.show = true;
                        this.message = ''
                    }
                })
        }
    }
}
</script>

<style scoped>
    .background {
        background-image: url("../assets/img/medicine.jpg");
        position: fixed; 
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