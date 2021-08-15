<template>
  <div class="div_background">
    <span class="header_span">Create an action/promotion</span>
    <br />
    <!-- <textarea
      required
      v-model="message_box"
      placeholder="enter details"
      class="promotion_area"
     
    ></textarea> -->
    <b-form-textarea
      id="textarea"
      v-model="message_box"
      placeholder="Enter something..."
      rows="3"
      max-rows="6"
      class="promotion_area"
      :state="message_box.length >= 1"
    ></b-form-textarea>
    <div>
      <label for="datepicker-invalid" class="label_datepicker"
        >Select start date</label
      >
      <b-form-datepicker
        class="mb-2 datepicker_b"
        :min="minDatePicker_1"
        v-model="datepicker_1"
        :format="'dd-MM-yyyy'"
      ></b-form-datepicker>
      <label for="datepicker-valid">Select end date</label>
      <b-form-datepicker
        :format="'dd-MM-yyyy'"
        :min="datepicker_1"
        class="datepicker_b"
        v-model="datepicker_2"
      ></b-form-datepicker>
      <br />
      <b-button variant="success" class="button_b" @click="submitClick"
        >Submit</b-button
      >
      <b-button variant="danger" class="button_b" @click="resetForm"
        >Reset</b-button
      >
      <br />
    </div>
  </div>
</template>

<script>
export default {
  name: "Action",
  computed: {
    User() {
      this.$store.getters.getUser;
    },
    userType() {
      return this.$store.getters.getUserType;
    },
    userId() {
      return this.$store.getters.getId;
    },
    Pharmacy() {
      return this.$store.getters.getPharmacy;
    },
    isRegisteredUser() {
      return this.$store.getters.isRegistered;
    },
  },
  data() {
    const now = new Date();
    const today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    const minDate = new Date(today);
    return {
      datepicker_1: new Date(),
      datepicker_2: new Date(),
      minDatePicker_1: minDate,
      message_box: "",
      isActive: true,
    };
  },
  methods: {
    resetForm() {
      this.message_box = "";
      this.datepicker_1 = new Date();
      this.datepicker_2 = new Date();
    },
    submitClick() {
      const body = {
        adminId: this.userId,
        // pharmacyId: this.Pharmacy,
        form: this.message_box,
        startTime: this.datepicker_1,
        endTime: this.datepicker_2,
        isActive: this.isActive,
      };
      if (this.message_box != "") {
        const headers = {
          method: "POST",
          "Content-Type": "application/json",

          body: JSON.stringify(body),
        };
        fetch(`http://localhost:9005/api/sales/create/`, {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "POST",
          body: JSON.stringify(body),
        }).then((response) => response.json());
      }else{
        alert("Please insert text into text area")
      }
    },
  },
  mounted() {
    console.log(this.userId);
  },
};
</script>

<style>
.div_background {
  background-color: rgb(255, 255, 255);
  align-items: center;
  border-radius: 1mm;

  border: 3px solid green;
}
.header_span {
  font-weight: bold;
  margin: 5px 10px 10px 5px;
}
.promotion_area {
  width: 600px;
  height: 200px;
}
.label_datepicker {
  text-align: left;
}
.datepicker_b {
  width: 200px;
}
.button_b {
  margin: 5px 5px 5px 5px;
}
</style>
