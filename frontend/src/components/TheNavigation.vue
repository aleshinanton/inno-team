<template>
  <div class="links">
    <router-link to="/projects"><h3>Реестр решений</h3></router-link>
    <router-link to="/tasks"><h3 class="separator">Реестр задач</h3></router-link>
    <div class="selected"><h4>{{ data }}</h4>
      <div class="avatar"></div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: ""
    }
  },
  mounted() {
    this.$keycloak.loadUserInfo().then(value =>
        this.data = value.name)
  },
}
</script>

<style>

.avatar {
  object-fit: cover;
  border-radius: 50%;
  width: 31px;
  height: 41px;
  background-color: #009A96;
  margin-left: auto;
  margin-right: 0;
}

.links {
  display: flex;
}

a {
  text-decoration: none;
}

h4 {
  text-align: right !important;
  float: left;
  font-weight: 400;
  margin-left: auto;
  margin-right: 0;
  align-items: flex-end;
}

h3 {
  text-align: left;
  padding: 0 15px;
  color: #009A96;
  text-decoration: underline;
  cursor: pointer;
  font-weight: 400;
}

.separator {
  border-left: 2px solid #009A96;
}

.active h3, .active {
  color: #71837f;
  text-decoration: none;
}
</style>