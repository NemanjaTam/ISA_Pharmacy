<template>
    <div class="background">
        <TopMenuForDP/>
        <div class="center">
            <h2>Text:</h2>
            <textarea name="" id="" cols="50" rows="10" v-model="Comp.text" disabled></textarea>
            <div v-if="Comp.reply != ''">
                <h2>Reply:</h2>
                <textarea name="" id="" cols="50" rows="10" v-model="Comp.reply" disabled></textarea>
            </div>
            <div v-else>
                <h2>No one has replied yet!</h2>
            </div>
            
        </div>
        
    </div>
</template>

<script>
import axios from 'axios'
import TopMenuForDP from '../components/TopMenuForPD.vue'
export default {
    name: "OneComplaintUser",
    components: {
        TopMenuForDP
    },
    data() {
        return {
            Comp: {}
        }
    },
    mounted() {
        axios.get("http://localhost:9005/api/user/getOneComplaint/"+this.$route.params.id)
            .then(res => {
                console.log(res.data);
                this.Comp = res.data;
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