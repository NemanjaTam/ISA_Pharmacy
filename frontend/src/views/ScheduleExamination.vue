<template>
    <div class="background">
        <TopMenuForPD/>
        <Scheduling class="form"/>
    </div>
</template>

<script>

import TopMenuForPD from '../components/TopMenuForPD.vue'
import Scheduling from '../components/Scheduling.vue'

export default {
    name: 'ScheduleExamination',
    components: {
        TopMenuForPD,
        Scheduling
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

    .form {
        margin-right: 20px;
        margin-left: 20px;
    }
</style>