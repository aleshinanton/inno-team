<template>
  <header>
            <h1>Витрина инноваций</h1>
        <img class="logo" src="@/assets/tim.svg" alt="Транспортные инновации Москвы">
  </header>
</template>

<script>
export default {

}
</script>

<style>
header {
    max-width: 1280px;
    width: 100%;
    height: 8vh;
    min-height: 30px;
    margin: 0 auto;
    display: flex;
    padding: 3px 0;
    justify-content: space-between;
    border-bottom: 1px solid #009A96;
}

h1 {
  font-size: 30px;
  color: #009A96;
  margin: 0;        
  display: table-cell;
  vertical-align: middle;
}

</style>