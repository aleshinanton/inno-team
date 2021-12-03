<template>
  <header>
    <h1>Витрина транспортных инноваций</h1>
    <img class="logo" src="@/assets/tim.svg" alt="Транспортные инновации Москвы">
  </header>
</template>

<script>
export default {

}
</script>

<style scoped>
header {
    max-height: 8vh;
    min-height: 30px;
    margin: 0 auto;
    display: flex;
    padding: 5px 0;
    justify-content: space-between;
    border-bottom: 1px solid #009A96;
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

</style>