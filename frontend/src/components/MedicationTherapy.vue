<template>
<div>
    <div class="container1">
        <div class="container">
                <b-form-datepicker v-model="fromDate" class="datepicker" placeholder="therapy start at" ></b-form-datepicker>
                <b-form-datepicker v-model="toDate" class="datepicker" placeholder="therapy end at" ></b-form-datepicker>
        </div>
        <div>
        <div class="container-child" style="float:left;">
            <label class="label"><b>Medications:</b></label>
            <b-table 
                class="table-light" 
                selectable sticky-header="60vh" 
                hover 
                :striped=true 
                head-variant="dark" 
                @row-clicked="medicationTableEvent" 
                :items="medications" 
                :fields="fields"
                select-mode="single"
                style="min-width: 32vw"
                >
            </b-table>
            <div v-if="showMedication" style="text-align: center">
                <label style="font-size:18px; color:red">{{this.messageMedication}}</label>
            </div>
        </div>
        <div v-if="this.show" class="container-child">
            <label class="label"><b>Replacement medications:</b></label>
            <b-table 
                class="table-light" 
                selectable sticky-header="64vh" 
                hover 
                :striped=true 
                head-variant="dark" 
                @row-clicked="replacementTableEvent" 
                :items="replacementMedications" 
                :fields="fields"
                select-mode="single"
                >
            </b-table>
            <div v-if="showReplacement" style="text-align: center">
                <label style="font-size:18px; color:red">{{this.messageReplacement}}</label>
            </div>
        </div>
        </div>
    </div>
    <div style="text-align: center">
        <b-button v-on:click="prescribe" class="button">Prescribe medication/therapy</b-button>
    </div>
</div>
</template>

<script>

import axios from 'axios'

export default {
    name: "MedicationTherapy",
    computed: {
        appointment() {
            return this.$store.getters.getCurrentAppointment
        }
    },
    data() {
        return {
            fields: ['name', 'manufacturer'],
            medications: [],
            replacementMedications: [],
            showMedication: false,
            messageMedication: '',
            showReplacement: false,
            messageReplacement: '',
            show: false,
            selectedMedication: null,
            fromDate: null,
            toDate: null
        }
    },
    created() {
        axios.get("http://localhost:9005/api/pharmacy/get-medications/" + this.appointment.pharmacy.id)
            .then(r => {
                this.medications = JSON.parse(JSON.stringify(r.data))
            })
    },
    methods: {
        async isPatientAllerged(medication) {
            var check = {
                patientId: this.appointment.patient.id,
                medicationId: medication.id
            }
            var response = await axios.post("http://localhost:9005/api/patient/is-allerged-on-medication", check)
                .then(r => {
                    if(r.data == "allerged") {
                        return true
                    }
                    else {
                        this.show = false
                        this.showMedication = false
                        return false
                    }
                })
                console.log(response)
            return response
        },
        async isMedicationAvailable(medication) {
            var check = {
                patientId: this.appointment.pharmacy.id,
                medicationId: medication.id
            }
            var response = await axios.post("http://localhost:9005/api/pharmacy/is-medication-available", check)
                .then(r => {
                    if(r.data == "available") {
                        return true
                    }
                    else {
                        this.show = false
                        this.showMedication = false
                        return false
                    }
                })
            return response
        },
        async medicationTableEvent(medication, index) {
            this.selectedMedication = medication
            var check = {
                patientId: this.appointment.pharmacy.id,
                medicationId: medication.id
            }
            var response = await axios.post("http://localhost:9005/api/pharmacy/is-medication-available", check)
                .then(r => {
                    if(r.data == "available") {
                        return true
                    }
                    else {
                        this.show = false
                        this.showMedication = false
                        return false
                    }
                })

            console.log(response)
            if(!response) {
                this.show = true
                this.showMedication = true
                this.messageMedication = 'This medication currently is not in stock!'
                this.selectedMedication = null
                check = {
                    patientId: this.appointment.pharmacy.id,
                    medicationId: medication.id
                }
                await axios.post("http://localhost:9005/api/medication/get-replacement-medication", check)
                    .then(r => {
                        console.log(r);
                    })
                return
            }

            check = {
                patientId: this.appointment.patient.id,
                medicationId: medication.id
            }
            response = await axios.post("http://localhost:9005/api/patient/is-allerged-on-medication", check)
                .then(r => {
                    if(r.data == "allerged") {
                        return true
                    }
                    else {
                        this.show = false
                        this.showMedication = false
                        return false
                    }
                })

            console.log(response)
            if(response) {
                this.show = true
                this.showMedication = true
                this.messageMedication = 'Patient is allerged on this medication!'
                this.selectedMedication = null
                check = {
                    patientId: this.appointment.pharmacy.id,
                    medicationId: medication.id
                }
                await axios.post("http://localhost:9005/api/medication/get-replacement-medications", check)
                    .then(r => {
                        this.replacementMedications = JSON.parse(JSON.stringify(r.data))
                    })
                return
            }

        },
        async replacementTableEvent(medication, index) {
            this.selectedMedication = medication
            var check = {
                patientId: this.appointment.pharmacy.id,
                medicationId: medication.id
            }
            var response = await axios.post("http://localhost:9005/api/pharmacy/is-medication-available", check)
                .then(r => {
                    if(r.data == "available") {
                        return true
                    }
                    else {
                        return false
                    }
                })

            console.log(response)
            if(!response) {
                this.show = true
                this.showReplacement = true
                this.messageReplacement = 'This medication currently is not in stock!'
                this.selectedMedication = null
                return
            }

            check = {
                patientId: this.appointment.patient.id,
                medicationId: medication.id
            }
            response = await axios.post("http://localhost:9005/api/patient/is-allerged-on-medication", check)
                .then(r => {
                    if(r.data == "allerged") {
                        return true
                    }
                    else {
                        return false
                    }
                })

            console.log(response)
            if(response) {
                this.show = true
                this.showReplacement = true
                this.messageReplacement = 'Patient is allerged on this medication!'
                this.selectedMedication = null
                return
            }
        },
        prescribe() {
            if(this.selectedMedication == null) {
                alert("Check all input fields again!")
                return
            }
            if(this.fromDate == null || this.toDate == null) {
                alert("Selected period for therapy is invalid")
                return
            }
            if(this.fromDate > this.toDate) {
                alert("Selected period for therapy is invalid")
                return
            }
            
            var start = this.getDateTimeFromString(this.fromDate,"00:00").getTime()
            var end = this.getDateTimeFromString(this.toDate,"00:00").getTime()
            var therapy = {
                period: {
                    startTime: start,
                    endTime: end
                },
                medication: this.selectedMedication
            }

            this.$store.dispatch('addTherapy', therapy)
            
        },
        getDateTimeFromString: function(dstr, tstr) {
            let dparts = dstr.split('-');
            let tparts = tstr.split(':');
            // -1 because js counts months from 0
            return new Date(dparts[0], dparts[1] - 1, dparts[2], tparts[0], tparts[1]);
        }
    }
}
</script>

<style scoped>

    .container {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        margin-top: 20px;
    }

    .container1 {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .container-child {
        /* display: flex; */
        /* flex-direction: column; */
        margin: 20px;
        flex: 1;
    }

    .label {
        font-size: 20px;
        margin-left: 10px;
    }
    .button{
        width: 300px;
        margin-top: 20px;
        text-align: center;
        transition: 0.25s;
        border-radius: 20px;
    }

    .datepicker {
        width: 200px;
        border-radius: 20px;
    }

    .boxTable {
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

</style>