<template>
    <div class="background">
        <TopMenuForPD/>
        <div>
            <b-form-input 
                class="boxDatePicker" 
                type="search"
                style="font-style:italic" 
                required
                id="filter-input"
                v-model="filter"
                placeholder="Type to Search"
            />
        </div>
        <div class="table-responsive">


            <b-table
                class="table-light" 
                style="margin:20px"
                head-variant="dark"
                selectable sticky-header="100%"
                select-mode="single"
                striped
                hover
                :items="patients"
                :fields="fields"
                :filter="filter"
                :filter-included-fields="filterOn"
            ></b-table>
            </div>
    </div>    
</template>

<script>

import axios from 'axios'
import TopMenuForPD from '../components/TopMenuForPD.vue'

export default {
    name: 'AllClients',
    components: {
        TopMenuForPD
    },
    computed: {
        User() {
            return this.$store.getters.getUser
        },
        userType() {
            return this.$store.getters.getUserType
        }
    },
    data() {
        return {
            selected: 'name',
            options: [
                { text: 'By name', value: 'name' },
                { text: 'By surname', value: 'surname' },
            ],
            fields: [
                {key: 'name', sortable: true}, 
                {key: 'surname', sortable:true}, 
                {key: 'email', sortable: true}, 
                {key: 'phone', sortable: true}, 
                {key: 'address.state', sortable: true, label: 'State'},
                {key: 'address.city', sortable: true, label: 'City'},
                {key: 'address.street', sortable: true, label: 'Street'},
                {key: 'address.number', sortable: true, label: 'Number'},
                {key: 'address.postalCode', sortable: true, label: 'Postal code'}
            ],
            patients: [],
            filter: null,
            filterOn: []
        }
    },
    created() {
        if(this.userType == "DERMATOLOGIST") {
            axios.get("http://localhost:9005/api/dermatologist/get-all/" + this.User.id)
                .then(r => {
                    this.patients = JSON.parse(JSON.stringify(r.data))
                })
        }
        else {
            alert("TODO")
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