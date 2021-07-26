<template>
    <div class="container">
        <div>
            <b-form-datepicker v-model="dateFrom" class="boxDatePicker" style="float:left" placeholder="Please select start date"></b-form-datepicker>
            <b-form-datepicker v-model="dateTo" class="boxDatePickerEnd" style="float:left" placeholder="Please select end date"></b-form-datepicker>
            <b-button class="button" v-on:click="this.search" style="float:right">Search</b-button>
        </div>
        <div >
            <b-form-input
                class="search"
                type="search"
                style="font-style:italic" 
                required
                id="filter-input"
                v-model="filter"
                placeholder="Type to search by time"
            />
        </div>
        <div>
                <!-- <table class="table table-hover boxTable">
                    <thead class="thead-dark topLeft topRight">
                        <tr>
                            <th style="text-align: center" class="topLeft" scope="col">Start time</th>
                            <th class="topRight" style="text-align: center" scope="col">End time</th>
                        </tr>
                    </thead>
                </table> -->
                <b-table
                class="table-light" 
                style="margin:20px"
                head-variant="dark"
                selectable sticky-header="100%"
                select-mode="single"
                striped
                hover
                :items="termins"
                :fields="fields"
                :filter="filter"
                :filter-included-fields="filterOn"
                @row-clicked="rowClick"
            ></b-table>
        </div>
        <div class="center">
            <b-button v-on:click="schedule" class="boxDatePicker">Schedule Examination</b-button>
        </div>
    </div>
</template>

<script>

import axios from 'axios'

export default {
    name: 'Scheduling',
    computed: {
        User() {
            return this.$store.getters.getUser
        },
        Appointment() {
            return this.$store.getters.getCurrentAppointment
        },
        userType() {
            return this.$store.getters.getUserType
        }
    },
    data() {
        return {
            dateFrom: null,
            dateTo: null,
            termins: [],
            fields: [
                {key: 'period.startTime', sortable: true, label: 'Start time'},
                {key: 'period.endTime', sortable: true, label: 'End time'}
            ],
            filter: null,
            filterOn: [],
            selectedTermin: null
        }
    },
    methods: {
        search() {
            if(this.dateTo == null || this.dateFrom == null) {
                alert("Termin is not valid!")
                return
            }
            if(this.dateFrom > this.dateTo) {
                alert("Termin is not valid!")
                return
            }
            var date = this.getDateTimeFromString(this.dateTo, "00:00");
            date.setDate(date.getDate() + 1)
            var check = {
                fromDate: this.getDateTimeFromString(this.dateFrom, "00:00").getTime(),
                toDate: date.getTime(),
                pharmacyId: this.Appointment.pharmacy.id,
                patientId: this.Appointment.patient.id,
                dermatologistId: this.User.id
            }

            if(this.userType == "DERMATOLOGIST") {
                axios.post("http://localhost:9005/api/appointment/get-available-appointments", check)
                    .then(r => {
                        this.termins = JSON.parse(JSON.stringify(r.data))
                        console.log(r.data)
                    })
            }
            else {
                axios.post("http://localhost:9005/api/consultation/get-available-consultations", check)
                    .then(r => {
                        this.termins = JSON.parse(JSON.stringify(r.data))
                        console.log(r.data)
                    })
            }


        },
        schedule() {
            if(this.selectedTermin == null) {
                alert("Termin is not selected!")
                return
            }
            if(this.userType == "DERMATOLOGIST") {
                var schedule = {
                    appointmentId: this.selectedTermin.id,
                    patientId: this.Appointment.patient.id,
                    dermatologistId: this.User.id
                }
                axios.post("http://localhost:9005/api/appointment/schedule-appointment", schedule)
                    .then(r => {
                        console.log(r.data)
                    })
            }
            else {
                axios.post("http://localhost:9005/api/consultation/schedule-consultation", this.selectedTermin)
                    .then(r => {
                        console.log(r.data)
                    })
            }
        },
        rowClick(termin, index) {
            this.selectedTermin = termin
        },
        getDateTimeFromString: function(dstr, tstr) {
            let dparts = dstr.split('-');
            let tparts = tstr.split(':');
            // -1 because js counts months from 0
            return new Date(dparts[0], dparts[1] - 1, dparts[2], tparts[0], tparts[1]);
        }
    },

}
</script>

<style scoped>
    .container {
        display: flex;
        flex-flow: column;
    }
    .background {
        background-image: url("../assets/img/medicine.jpg");
        position: absolute; 
        top: 0; 
        left: 0; 
        min-width: 100%;
        min-height: 100%;
    }

    .horizontal {
        margin-left: 15vw;
        margin-right: 15vw;
    }

    .center {
        margin: 0 auto;
        margin-top: 20px;
        text-align: center;
    }

    .boxDatePicker {
        max-width: 25vw;
        margin: 0 auto;
        margin-top: 20px;
        box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
        text-align: center;
        transition: 0.25s;
        border-radius: 20px;
    }

    .boxDatePickerEnd {
        max-width: 25vw;
        margin: 0 auto;
        margin-top: 20px;
        margin-left: 20px;
        box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
        text-align: center;
        transition: 0.25s;
        border-radius: 20px;
    }

    .search {
        max-width: 300px;
        box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
        margin-top: 20px;
        text-align: center;
        transition: 0.25s;
        border-radius: 20px;
    }

    .button{
        width: 5vw;
        margin-top: 20px;
        text-align: center;
        transition: 0.25s;
        border-radius: 20px;
    }

    .boxTable {
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