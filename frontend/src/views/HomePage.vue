<template>
  <div class="background">
    <TopMenuForDP/>
    <div class="container">
      <div class="container-child-1">
        <b-form>
            <label class="labelBox"><b>Name:</b></label>
            <b-form-input v-model="User.name" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>Surname:</b></label>
            <b-form-input v-model="User.surname" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>Email:</b></label>
            <b-form-input v-model="User.email" class="textBox" type="email" disabled></b-form-input>
            <label class="labelBox"><b>Phone number:</b></label>
            <b-form-input v-model="User.phone" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>State:</b></label>
            <b-form-input v-model="User.address.state" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>City:</b></label>
            <b-form-input v-model="User.address.city" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>Street:</b></label>
            <b-form-input v-model="User.address.street" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>Number of house:</b></label>
            <b-form-input v-model="User.address.number" class="textBox" disabled></b-form-input>
            <label class="labelBox"><b>Postal code:</b></label>
            <b-form-input v-model="User.address.postalCode" class="textBox" disabled></b-form-input>
        </b-form>
      </div>
      <div class="container-child-2">
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
                    <tr class="table-light" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <router-link to="/examinationCheck" class="routerlink">
                                <b-button class="button">Start examination</b-button>
                            </router-link>
                        </td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>
                            <b-button class="button">Start examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Start examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Start examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Start examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Start examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Start examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Start examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Start examination</b-button>
                        </td>
                    </tr>                    
                    <tr class="table-dark" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Start examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Start examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-dark bottomRight bottomLeft" style="text-align: center">
                        <td class="bottomLeft">Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td class="bottomRight" style="width: 180px">
                            <b-button class="button">Start examination</b-button>
                        </td>
                    </tr>
                </tbody>
            </table>
      </div>
    </div>
    <div class="container-1">
        <div class="container-child">
          <b-form-datepicker v-model="startTime" class="boxDatePicker" placeholder="Select absence beggining"></b-form-datepicker>
        </div>
        <div class="container-child">
          <b-button v-on:click="requestAbsence" class="button">Request holiday or absence</b-button>
        </div>
        <div class="container-child">
          <b-form-datepicker v-model="endTime" class="boxDatePicker" placeholder="Select absence ending"></b-form-datepicker>
          </div>
    </div>
  </div>
</template>

<script>

import axios from 'axios'
import TopMenuForDP from '../components/TopMenuForPD.vue'

export default {
    name: "HomePage",
    components: {
        TopMenuForDP
    },
    computed: {
        User() {
            return this.$store.getters.getUser
        }
    },
    data() {
        return {
            absence: {
                isApproved: false,
                isFinished: false,
                period: {
                    startTime: null,
                    endTime: null
                },
                user: null
            },
            startTime: null,
            endTime: null
        }
    },
    methods: {
        requestAbsence() {
            this.absence.user = this.User
            if(this.startTime == null || this.endTime == null) {
                alert("Please select dates for Absence/Holiday")
                return
            }
            if(this.startTime > this.endTime) {
                alert("Selected dates are invalid")
                return
            }
            console.log(this.startTime + " - " + this.endTime)
            var start = this.getDateTimeFromString(this.startTime, '00:00').getTime()
            var end = this.getDateTimeFromString(this.endTime, '00:00').getTime()
            console.log(start + " - " + end)
            this.absence.period.startTime = start
            this.absence.period.endTime = end
            axios.post("http://localhost:9005/api/absence/request-absence", this.absence)
                .then(r => {
                    console.log(r.data)
                })
        },
        // Expected yy-mm-dd and HH:mm format
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
  .background {
    background-image: url("../assets/img/medicine.jpg");
    position: absolute; 
    top: 0; 
    left: 0; 
    min-width: 100%;
    min-height: 100%;
  }

  .labelBox {
    /* margin-top:20px; */
    margin-left: 15px;
  }

  .textBox {
    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
    transition: 0.25s;
    border-radius: 20px;
    margin-bottom: 20px;
  }

  .container {
        display: flex;
        flex-direction: row;
        min-width: 100%;
        min-height: 100%;
        justify-content:flex-center;
    }

  .container-1 {
        display: flex;
        flex-direction: row;
        min-width: 100%;
        min-height: 100%;
        justify-content:flex-center;
    }

    .container-child {
        margin: 20px;
        /* flex: 2; */
    }

    .container-child-1 {
        margin: 20px;
        flex: 1;
    }

    .container-child-2 {
        margin: 20px;
        flex: 2;
    }

    .boxDatePicker {
        max-width: 30vw;
        box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
        text-align: center;
        transition: 0.25s;
        border-radius: 20px;
    }

    .button {
        width: 300px;
        transition: 0.25s;
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

    .buttonColumn {
        width: 180px;
        
    }
</style>