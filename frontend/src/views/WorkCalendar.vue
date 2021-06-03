<template>
    <div class="background">
        <TopMenuForPD/>
        <div>
            <b-form-datepicker class="boxDatePicker" placeholder="Please select date"></b-form-datepicker>
            <div class="table-responsive">
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
    </div>
</template>

<script>

import TopMenuForPD from '../components/TopMenuForPD'

export default {
    name: 'WorkCalendar',
    components: {
        TopMenuForPD
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