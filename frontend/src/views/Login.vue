<template>
  <div class="background">
    <div class="mt-3 card">
      <b-card-group deck>
        <b-card bg-variant="light" header="Log in" class="text-center title">
          <b-form-group
            class="foo"
            id="input-group-1"
            label="Email address:"
            label-for="input-1"
            description="We'll never share your email with anyone else."
          >
            <b-form-input
              id="input-1"
              v-model="form.email"
              type="email"
              placeholder="enter email"
              style="font-style: italic"
              label-cols-lg="7"
              label-cols="4"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group
            class="foo2"
            id="input-group-2"
            label="Password:"
            label-for="input-2"
          >
            <b-form-input
              id="input-2"
              type="password"
              v-model="form.password"
              placeholder="enter password"
              style="font-style: italic"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group
            class="foo2"
            id="input-group-2"
            label="Confirm password:"
            label-for="input-2"
          >
            <b-form-input
              id="input-2.2"
              type="password"
              v-model="form.confirmPassword"
              placeholder="confirm password"
              style="font-style: italic"
              required
            ></b-form-input>
          </b-form-group>
        </b-card>
      </b-card-group>
    </div>
  </div>
</template>

<style scoped>
.background {
  background-image: url("medicine.jpg");
  background-size: cover;
  overflow: auto;
}
.card {
  box-shadow: 10px 4px 8px 0 rgba(0, 0, 0, 0.2);
  transition: 15s;
  width: 50%;
  border-radius: 20px;
  margin-top: 40px;
  margin-bottom: 50px;
  margin-right: 500px;
  margin-left: 500px;
  width: 570px;
}
.title {
  text-align: center;
  font-family: fantasy;
  font-size: 25px;
  margin-top: 10px;
}

.foo {
  padding-top: 5px;
  padding-left: 70px;
  padding-right: 90px;
}
.foo2 {
  padding-left: 70px;
  padding-right: 90px;
}
</style>

<script>
export default {
  name: "Login",
  setup() {},
  methods: {
    onSubmit(event) {
      event.preventDefault();
    },
  },
};
</script>