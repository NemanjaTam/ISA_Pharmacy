<template>
    <div>
        <div>
            <b-form-datepicker class="boxDatePicker" style="float:left" placeholder="Please select start date"></b-form-datepicker>
            <b-form-datepicker class="boxDatePickerEnd" style="float:left" placeholder="Please select end date"></b-form-datepicker>
            <b-button class="button" style="float:right">Search</b-button>
        </div>
        <div class="table-responsive">
                <table class="table table-hover boxTable">
                    <thead class="thead-dark topLeft topRight">
                        <tr>
                            <th style="text-align: center" class="topLeft" scope="col">Start time</th>
                            <th class="topRight" style="text-align: center" scope="col">End time</th>
                        </tr>
                    </thead>
                <tbody>
                    <tr class="table-light" style="text-align: center">
                        <td>Column content</td>
                        <td>Column content</td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Columnn content</td>
                        <td>Column content</td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Column content</td>
                        <td>Column content</td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Columnn content</td>
                        <td>Column content</td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Column content</td>
                        <td>Column content</td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Columnn content</td>
                        <td>Column content</td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Column content</td>
                        <td>Column content</td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Columnn content</td>
                        <td>Column content</td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Column content</td>
                        <td>Column content</td>
                    </tr>
                    <tr class="table-dark" style="text-align: center">
                        <td>Columnn content</td>
                        <td>Column content</td>
                    </tr>
                    <tr class="table-light" style="text-align: center">
                        <td>Column content</td>
                        <td>Column content</td>
                    </tr>
                    <tr class="table-dark bottomRight bottomLeft" style="text-align: center">
                        <td class="bottomLeft">Columnn content</td>
                        <td class="bottomRight" >Column content</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="center">
            <b-button class="boxDatePicker">Schedule Examination</b-button>
        </div>
    </div>
</template>

<script>
export default {
    
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