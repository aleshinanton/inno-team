<template>
  <header class="flex">
    <h1>Витрина транспортных инноваций</h1>
    <img class="logo" src="@/assets/tim.svg" alt="Транспортные инновации Москвы">
    <div class="flex">
      <h4>Best Customer</h4>
      <div class="avatar"></div>
      <button @click="logout()">Выход</button>
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      data: ""
    }
  },
  methods: {
    logout() {
      this.$keycloak.logout();
    }
  },
  mounted() {
    this.$keycloak.loadUserInfo().then(value =>
        this.data = value.name)
  },
}
</script>

<style scoped>
header {
    max-height: 8vh;
    min-height: 30px;
    margin: 5px auto;
    padding: 5px 0;
    justify-content: space-between;
    border-bottom: 1px solid #009A96;
}

.flex {
  display: flex;
  align-items: center;
}

button {
    background: inherit;
    cursor: pointer;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    color: #009A96;
    padding: 14px 16px;
    margin-left: 10px;
}

button:hover {
  box-shadow: 0 0 11px 2px rgba(33,33,33,.2);
  border: 1px solid #009A96;
  padding: 14px 15px;
}

img {
  max-height: 40px;
}

h1 {
  font-size: 30px;
  color: #009A96;
  margin: 0;        
  display: table-cell;
  vertical-align: middle;
}
.avatar {
  background-image: url('../assets/avatar.png');
  background-size: cover;
  border-radius: 50%;
  width: 40px;
  height: 40px;
  border: 1px solid #009A96;
  margin-left: 16px;
}

h4 {
  text-align: right !important;
  font-weight: 400;
}
</style>