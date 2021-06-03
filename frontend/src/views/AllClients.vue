<template>
    <div class="background">
        <TopMenuForPD/>
        <div>
            <b-form-input class="boxDatePicker" type="text" placeholder="type something to search" style="font-style:italic" required/>
            <b-form-group class="center" v-slot="{ ariaDescribedby }">
                <b-form-radio-group
                    v-model="selected"
                    :options="options"
                    :aria-describedby="ariaDescribedby"
                    name="radio-inline"
                ></b-form-radio-group>
            </b-form-group>
        </div>
        <div class="table-responsive">
                <table class="table table-hover boxTable">
                <thead class="thead-dark topLeft topRight">
                    <tr>
                        <th style="text-align: center" class="topLeft" scope="col">Name</th>
                        <th style="text-align: center" scope="col">Surname</th>
                        <th style="text-align: center" scope="col">Birthday</th>
                        <th style="text-align: center" scope="col">Phone number</th>
                        <th class="topRight" style="text-align: center" scope="col">Schedule</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="table-light" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 210px">
                            <router-link to="/scheduleExamination" class="routerlink">
                                <b-button class="button">Schedule examination</b-button>
                            </router-link>
                        </td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>
                            <b-button class="button">Schedule examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Schedule examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Schedule examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Schedule examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Schedule examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Schedule examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Schedule examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Schedule examination</b-button>
                        </td>
                    </tr>                    
                    <tr class="table-dark" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Schedule examination</b-button>
                        </td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td style="width: 180px">
                            <b-button class="button">Schedule examination</b-button>
                        </td>
                    </tr> 
                    <tr class="table-dark bottomRight bottomLeft" style="text-align: center">
                        <td class="bottomLeft">Dark</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td class="bottomRight" style="width: 180px">
                            <b-button class="button">Schedule examination</b-button>
                        </td>
                    </tr>
                </tbody>
            </table>
            </div>
    </div>    
</template>

<script>

import TopMenuForPD from '../components/TopMenuForPD.vue'

export default {
    name: 'AllClients',
    components: {
        TopMenuForPD
    },
    data() {
        return {
            selected: 'name',
            options: [
                { text: 'By name', value: 'name' },
                { text: 'By surname', value: 'surname' },
                { text: 'By phone number', value: 'phonenubmer' }
            ]
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