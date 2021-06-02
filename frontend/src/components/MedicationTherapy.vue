<template>
<div>
    <div class="container">
        <div class="container-child" style="float:left;">
            <label class="label"><b>Medications:</b></label>
            <table class="table table-hover boxTable">
                    <thead class="thead-dark topLeft topRight">
                        <tr>
                            <th style="text-align: center" class="topLeft" scope="col">Medication</th>
                            <th class="topRight" style="text-align: center" scope="col">Manufacturer</th>
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
            <div style="text-align: center">
                <label style="font-size:18px; color:red">This medication isn't available!</label>
            </div>
        </div>
        <div class="container-child">
            <label class="label"><b>Replacement medications:</b></label>
            <table class="table table-hover boxTable">
                <thead class="thead-dark topLeft topRight">
                    <tr>
                            <th style="text-align: center" class="topLeft" scope="col">Medication</th>
                            <th class="topRight" style="text-align: center" scope="col">Manufacturer</th>
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
            <div style="text-align: center">
                <label style="font-size:18px; color:red">Patient is allerged on this medication!</label>
            </div>
        </div>
    </div>
    <div style="text-align: center">
        <b-button class="button">Prescribe medication/therapy</b-button>
    </div>
</div>
</template>

<script>
export default {
    name: "MedicationTherapy"
}
</script>

<style scoped>

    .container {
        display: flex;
        flex-direction: row;
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