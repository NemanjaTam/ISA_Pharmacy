<template>
  <!-- MODALNI DIJALOG ZA LEKOVE - BOJANA -->

  <div>
    <b-button variant="warning" @click="onshow(selected)">Edit</b-button>
    <b-modal v-model="show" ref="modal" title="Edit user" hide-footer>
      <div>
        <b-form-input
          id="input-live"
          v-model="pharmacist.name"
          placeholder="Enter name"
          type="text"
          trim
        ></b-form-input>
            <b-form-input
          id="input-live"
          v-model="pharmacist.surname"
          placeholder="Enter surname"
          type="text"
          trim
        ></b-form-input>
            <b-form-input
          id="input-live"
          v-model="pharmacist.email"
          placeholder="Enter email"
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
  name: "ModalEditPharmacist",
  props: ["selected", "id"],
  computed: {},
  data() {
    return {
      pharmacist: {
        name: "",
        surname: "",
        email: "",
      },
      show: false,
    };
  },
  methods: {
    onshow(selected) {
      this.show = true;
      if(selected == null){
          this.show = false;
      }else{
      this.pharmacist.name = selected.name;
      this.pharmacist.surname = selected.surname;
      this.pharmacist.email = selected.email;
      }

    },
  },
};
</script>
<style scoped></style>
