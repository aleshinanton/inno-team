<template>
  <div>
    <TheHeader />
    <Showcase />
    <!-- <test-data></test-data> -->
  </div>
</template>

<script>
import TheHeader from './components/TheHeader.vue'
import Showcase from './components/Showcase.vue'
// import TestData from './components/TestData.vue';

export default {
  name: 'App',
  components: {
    TheHeader,
    Showcase,
    // TestData
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
