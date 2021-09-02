<template>
  <!-- MODALNI DIJALOG ZA LEKOVE - BOJANA -->

  <div>
    <b-button  @click="onshow(selected)" variant="success">Add</b-button>
    <b-modal v-model="show" ref="modal" title="Add worker" hide-footer>
      <div>
        <b-form-input
          id="input-live"
          v-model="newWorker.name"
          placeholder="Enter name"
          type="text"
          trim
        ></b-form-input>
            <b-form-input
          id="input-live"
          v-model="newWorker.surname"
          placeholder="Enter surname"
          type="text"
          trim
        ></b-form-input>
            <b-form-input
          id="input-live"
          v-model="newWorker.email"
          placeholder="Enter email"
          type="text"
          trim
        ></b-form-input>
                    <b-form-input
          id="input-live"
          v-model="newWorker.state"
          placeholder="Enter state"
          type="text"
          trim
        ></b-form-input>
                            <b-form-input
          id="input-live"
          v-model="newWorker.city"
          placeholder="Enter city"
          type="text"
          trim
        ></b-form-input>
                                    <b-form-input
          id="input-live"
          v-model="newWorker.street"
          placeholder="Enter street"
          type="text"
          trim
        ></b-form-input>
                                            <b-form-input
          id="input-live"
          v-model="newWorker.number"
          placeholder="Enter number"
          type="text"
          trim
        ></b-form-input>
                                            <b-form-input
          id="input-live"
          v-model="newWorker.postalCode"
          placeholder="Enter postalCode"
          type="text"
          trim
        ></b-form-input>
          <b-button>OK</b-button>
      </div>
    </b-modal>
  
  </div>
</template>

<script>
export default {
  name: "ModalAddWorker",
  props: ["selected", "id","workerType"],
 computed:{},
  data() {
    return {
      newWorker: {
        name: "",
        surname: "",
        email: "",
        password:"",
        state:"",
        city:"",
        street:"",
        number:"",
        postalCode:"",
        workingDays:[],
      },
      show: false,
    };
  },
  methods: {
    onshow(selected) {
      this.show = true;

    },
  },
  
};
</script>
