<template>
    <div class="background">
        <TopMenuForDP/>
        <div class="center">
            <table class="table table-striped table-inverse table-responsive align">
            <thead class="thead-inverse">
                <tr>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Subscribe/Unsubscribe</th>
                </tr>
                </thead>
                <tbody>
                    <tr v-for="pharm in listPharm" :key="pharm.id">
                        <td>{{pharm.name}}</td>
                        <td>{{pharm.address.street + " " + pharm.address.number + ", " + pharm.address.city}}</td>
                        <td v-if="pharm.subscribed"><button type="button" name="" :id="pharm.id" class="btn btn-danger" @click="unsubscribe(pharm.id)">Unsubscribe</button></td>
                        <td v-else><button type="button" name="" :id="pharm.id" class="btn btn-success" @click="subscribe(pharm.id)" >Subscribe</button></td>
                    </tr>
                </tbody>
        </table>
        </div>
        
    </div>
</template>

<script>
import axios from 'axios'
import TopMenuForDP from '../components/TopMenuForPD.vue'

export default {
    name: "SubList",
    components: {
        TopMenuForDP
    },
    data() {
        return {
            listPharm: []
        }
    },
    mounted() {
        axios.get("http://localhost:9005/api/pharmacy/getSubs/"+this.$store.getters.getUser.id)
            .then(res => {
                console.log(res.data);
                this.listPharm = res.data;
            })
    },
    methods: {
        unsubscribe(id) {
            var self = this;
            var idU = this.$store.getters.getUser.id;
            console.log(id + " UNSUB");
            axios.put("http://localhost:9005/api/pharmacy/unsubscriber/"+this.$store.getters.getUser.id+"/"+id)
            .then(res => {
                alert("Successfuly unsubscribed");
                axios.get("http://localhost:9005/api/pharmacy/getSubs/"+idU)
                .then(res => {
                    console.log(res.data);
                    self.listPharm = res.data;
                })
            })
        },
        subscribe(id) {
            console.log(id + " UNSUB");
            var self = this;
            var idU = this.$store.getters.getUser.id;
            axios.put("http://localhost:9005/api/pharmacy/subscriber/"+this.$store.getters.getUser.id+"/"+id)
            .then(res => {
                alert("Successfuly subscribed");
                axios.get("http://localhost:9005/api/pharmacy/getSubs/"+idU)
                .then(res => {
                    console.log(res.data);
                    self.listPharm = res.data;
                })
            })
        }
    },
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
    margin: auto;
    margin-top: 5%;
    width: 50%;
    }

</style>