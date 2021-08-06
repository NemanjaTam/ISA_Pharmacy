<template>
  <div class="background">
    <!-- <TopMenuForDP/> -->
    <div>
      <b-card no-body>
        <b-tabs card>
          <b-tab title="Profile" active>
            <div class="container">
              <div class="container-child-1">
                <b-form>
                  <label class="labelBox"><b>Name:</b></label>
                  <b-form-input
                    v-model="name"
                    class="textBox"
                    disabled
                  ></b-form-input>
                  <label class="labelBox"><b>Average points:</b></label>
                  <b-form-input
                    v-model="this.avgRating"
                    class="textBox"
                    disabled
                  ></b-form-input>
                  <label class="labelBox"><b>Address:</b></label>
                  <div class="parent">
                    <label class="labelBox"><b>State:</b></label>
                    <b-form-input
                      id="input-small"
                      size="sm"
                      v-model="address.state"
                      class="textBox"
                      disabled
                    ></b-form-input>
                    <label class="labelBox"><b>City:</b></label>
                    <b-form-input
                      id="input-small"
                      size="sm"
                      v-model="address.city"
                      class="textBox"
                      disabled
                    ></b-form-input>
                    <label class="labelBox"><b>Street:</b></label>
                    <b-form-input
                      id="input-small"
                      size="sm"
                      v-model="address.street"
                      class="textBox"
                      disabled
                    ></b-form-input>
                    <label class="labelBox"><b>Number:</b></label>
                    <b-form-input
                      id="input-small"
                      size="sm"
                      v-model="address.number"
                      class="textBox"
                      disabled
                    ></b-form-input>
                    <label class="labelBox"><b>Postal code:</b></label>
                    <b-form-input
                      id="input-small"
                      size="sm"
                      v-model="address.postal"
                      class="textBox"
                      disabled
                    ></b-form-input>
                  </div>
                </b-form>
              </div>
            </div>
          </b-tab>
          <b-tab title="Dermatogists">
            <p
              v-for="(listItem, index) in dermatologists"
              class="my-2 field"
              :key="index"
            >
              {{ listItem.name + " " + listItem.surname }}
              <!-- <input
          type="text"
           v-bind:value="listItem"
          :key="index"
    /> -->
              <button @click.prevent="remove(index)">info</button>
            </p>
          </b-tab>

          <b-tab title="Pharmacists">
            <p
              v-for="(listItem, index) in pharmacists"
              class="my-2 field"
              :key="index"
            >
              {{ listItem.name + " " + listItem.surname }}
              <!-- <input
          type="text"
           v-bind:value="listItem"
          :key="index"
    /> -->
              <button @click.prevent="remove(index)">info</button>
            </p>
          </b-tab>
        </b-tabs>
      </b-card>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import NavbarAdmin from "../components/NavbarAdmin.vue";
export default {
  name: "Pharmacy",
  components: {
    NavbarAdmin,
  },
  data() {
    return {
      name: "",
      address: "",
      avgRating: "", //treba prikazati liste dermatologa,liste farmaceuta,lekova itd..
      address: {
        state: "",
        city: "",
        postal: "",
        street: "",
        number: "",
      },
      dermatologists: [
        {
          id: "",
          name: "",
          surname: "",
          email: "",
        },
      ],
      pharmacists: [
        {
          id: "",
          name: "",
          surname: "",
          email: "",
        },
      ],
      message: "",
    };
  },
  methods: {
    getPharmacyById(id) {
      const headers = { "Content-Type": "application/json" };
      fetch(`http://localhost:9005/api/pharmacy/getpharmacy/${id}`, { headers })
        .then((response) => response.json())
        .then(
          (data) => (
            (this.name = data.name),
            (this.address.state = data.address.state),
            (this.address.city = data.address.city),
            (this.address.street = data.address.street),
            (this.address.number = data.address.number),
            (this.address.postal = data.address.postalCode),
            (this.avgRating = data.avgRating)
          )
        );
    },

    getDermatologists(id) {
      const headers = { "Content-Type": "application/json" };
      fetch(
        `http://localhost:9005/api/dermatologist/getalldermatologist/${id}`,
        { headers }
      )
        .then((response) => response.json())
        .then((data) => (this.dermatologists = data));
    },

    getPharmacists(id) {
      const headers = { "Content-Type": "application/json" };
      fetch(
        `http://localhost:9005/api/pharmacist/getallpharmacistsbyid/${id}`,
        {
          headers,
        }
      )
        .then((response) => response.json())
        .then((data) => (this.pharmacists = data));
    },
  },
  mounted() {
    const id = this.$route.params.id;
    console.log(id);
    this.getPharmacyById(id);
    this.getDermatologists(id);
    this.getPharmacists(id);
  },
};
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

.info {
  background: white;
  border-radius: 25px;
  border: 5px solid #343a40;
  padding: 20px;
}
.containerMiddle {
  grid-area: middle;
  height: 500px;
}

* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Style the header */
.header {
  grid-area: header;
  padding: 0px;

  text-align: center;
  font-size: 15px;
}

/* The grid container */
.grid-container {
  background-image: url("../assets/img/medicine.jpg");
  position: absolute;
  top: 0;
  left: 0;
  min-width: 100%;
  min-height: 100%;
  display: grid;
  grid-template-areas:
    "header header header header header header"
    "left middle middle middle middle right"
    "footer footer footer footer footer footer";
  /* grid-column-gap: 10px; - if you want gap between the columns */
}

.left,
.middle,
.right {
  padding: 25px;
  /*height: 500px; /* Should be removed. Only for demonstration */
}

/* Style the left column */
.left {
  grid-area: left;
}

/* Style the middle column */
.middle {
  grid-area: middle;
}

/* Style the right column */
.right {
  grid-area: right;
}
.parent {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(6, 1fr);
  grid-column-gap: 0px;
  grid-row-gap: 0px;
}

/* Style the footer */
.footer {
  grid-area: footer;

  padding: 10px;
  opacity: 0.5;
  text-align: center;
}

/* Responsive layout - makes the three columns stack on top of each other instead of next to each other */
@media (max-width: 600px) {
  .grid-container {
    grid-template-areas:
      "header header header header header header"
      "left left left left left left"
      "middle middle middle middle middle middle"
      "right right right right right right"
      "footer footer footer footer footer footer";
  }
}
</style>
