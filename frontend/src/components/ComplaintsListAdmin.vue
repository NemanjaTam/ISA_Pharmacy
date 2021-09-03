<template>
    <div class="background">
        <TopMenuForDP/>
        <div class="center">
            <table class="table table-striped table-inverse table-responsive align">
            <thead class="thead-inverse">
                <tr>
                    <th>Text</th>
                    <th>Reply</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <tr v-for="comp in listComp" :key="comp.id">
                        <td>{{comp.text.substring(0,60) + "... "}}</td>
                        <td v-if="comp.reply== ''">No answer yet</td>
                        <td v-else>{{comp.reply.substring(0,10) + "... "}}</td>
                        <td><a name="" id="" class="btn btn-primary" v-bind:href="'/OneComplaintAdmin/'+comp.id" role="button">Detailed</a></td>
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
    name: "ComplaintsListAdmin",
    components: {
        TopMenuForDP
    },
    data() {
        return {
            listComp: []
        }
    },
    mounted() {
        axios.get("http://localhost:9005/api/user/getAdminComplaint/"+this.$store.getters.getUser.id)
            .then(res => {
                console.log(res.data);
                this.listComp = res.data;
            })
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