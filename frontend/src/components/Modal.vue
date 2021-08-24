<template>
  <div>
    <b-button size="sm" @click="show = true">Edit</b-button>

    <b-modal v-model="show" ref="modal" title="Edit" @ok="handleOk">
      <div>
        <b-form-input v-model="medication.name" placeholder="name"></b-form-input>
        <b-form-input v-model="item.name" placeholder="name"></b-form-input>
        <b-form-input v-model="item.name" placeholder="name"></b-form-input>
        <b-form-input v-model="item.name" placeholder="name"></b-form-input>
        <b-form-input v-model="item.name" placeholder="name"></b-form-input>
        <b-form-input v-model="item.name" placeholder="name"></b-form-input>
        <b-form-input v-model="item.name" placeholder="name"></b-form-input>
        <b-form-input v-model="item.name" placeholder="name"></b-form-input>
      </div>
      <!-- <label for="input-live">Name:</label>
              <b-form-input
                id="input-live"
                v-model="item.name"
                aria-describedby="input-live-help input-live-feedback"
                :state="nameState"
                placeholder="Enter your name"
                trim
              ></b-form-input> -->
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "Modal",
  props: ["item"],
  data() {
    return {
      show: false,
      medication:{},
    };
  },
  mounted(){
    this.medication = item;
  }
};
</script>
