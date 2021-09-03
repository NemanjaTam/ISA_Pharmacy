// this.$route.params.id
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
                <h2>Reply:</h2>
                <textarea name="" id="" cols="50" rows="10" v-model="ans"></textarea>
                <br>
                <button type="button" class="btn btn-primary" @click="reply()" >Reply</button>

            </div>
            
        </div>
        
    </div>
</template>

<script>
import axios from 'axios'
import TopMenuForDP from '../components/TopMenuForPD.vue'
export default {
    name: "OneComplaintAdmin",
    components: {
        TopMenuForDP
    },
    data() {
        return {
            Comp: {},
            ans: ""
        }
    },
    mounted() {
        axios.get("http://localhost:9005/api/user/getOneComplaint/"+this.$route.params.id)
            .then(res => {
                console.log(res.data);
                this.Comp = res.data;
            })
    },
     methods: {
         reply() {
             console.log(this.ans.trim());
             if(this.ans.trim() == "") {
                 alert("Reply can't be empty!");
                 return;
             } else {
                 var fullRep = "Admin: " + this.$store.getters.getUser.name + " " + this.$store.getters.getUser.surname + " answered this complaint\n"+this.ans;
                 var jsonObj = {
                     id:this.Comp.id,
                     reply:fullRep,
                     admId:this.$store.getters.getUser.id
                 }
                 axios.put("http://localhost:9005/api/user/answerComplaint", jsonObj)
                    .then(res => {
                        if(res.data == "OK") {
                            alert("Successfully answered complaint!");
                            this.$router.push({ name: "ComplaintsListAdmin" });

                        }
                    })
             }
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
    margin: auto;
    margin-top: 5%;
    width: 50%;
    }

</style>