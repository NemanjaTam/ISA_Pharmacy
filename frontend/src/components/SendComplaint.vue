// this.$route.params.id
<template>
    <div class="background">
        <TopMenuForDP/>
        <div class="center">
            <h1>Choose who you want to complain about:</h1>
            <select class="form-control" name="template" v-model="selected" required>
                <option v-for="(c, index) in comp" v-bind:value="c" :key="index">
                    {{ c }}
                </option>
            </select>
            <h1>Input text:</h1>
            <textarea name="" id="textar" cols="100" rows="10" v-model="text"></textarea>
            <br>
            <button type="button" class="btn btn-primary" @click="sendComplaint()">Send</button>
        </div>
        
    </div>
</template>

<script>
import axios from 'axios'
import TopMenuForDP from '../components/TopMenuForPD.vue'

export default {
    name: "SendComplaint",
    components: {
        TopMenuForDP
    },
    data() {
        return {
            comp: {},
            selected: "",
            text: ""
        }
    },
    mounted() {
        axios.get("http://localhost:9005/api/user/complainable/"+this.$store.getters.getUser.id)
            .then(res => {
                console.log(res.data);
                this.comp = res.data;
            })
    },
    methods: {
        sendComplaint() {
            var temp = this;
            if(this.selected == "") {
                alert("You must choose a person or pharmacy!");
                return;
            } else {
                
                var text = "User: " + this.$store.getters.getUser.name + " " + this.$store.getters.getUser.surname + " complaint on: " +this.selected+"\n"+this.text;
                console.log(text);
                var jsonObj = {
                    patId:this.$store.getters.getUser.id,
                    text:text
                }
                axios.post("http://localhost:9005/api/user/sendComplaint", jsonObj)
                    .then(res => {
                        if(res.data == "OK") {
                            alert("Successfully sent complaint!");
                            this.$router.push({ name: "ComplaintsListUser" });

                        }
                    })
            }
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