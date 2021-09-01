<template>
  <div>
    <b-table :items="appointments" selectable>
      striped
    </b-table>
  </div>
</template>
<script>
export default {
  name: "CreateAppointments",
  components: {},
  computed: {},
  data() {
    return {
      appointments: [],
      fields:[],
    };
  },
};
</script>

<style scoped></style>
