<template>
  <div>
    <TheHeader />
    <router-view></router-view>
    <!-- <test-data></test-data> -->
  </div>
</template>

<script>
import TheHeader from './components/TheHeader.vue'
// import TheNavigation from './components/TheNavigation.vue'

export default {
  name: 'App',
  components: {
    TheHeader,
    // TheNavigation
  }
}
</script>

<style lang="scss">
#app {
  font-family: 'MoscowSansRegular';
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
body {
  margin: 0;
  background-color: #F6F7FA;
  padding: 0 10px;
}
</style>
