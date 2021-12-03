<template>
  <div class="filter">
    <form onsubmit="event.preventDefault();" role="search">
        <input id="search" type="search" placeholder="Введите ключевые слова" autofocus required />
        <button type="submit">Искать</button>    
    </form>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.filter {
    height: 100px;
    width: 75%;
    margin: 10px auto;
    padding: 15px;
}

form {
    border-bottom: 1px solid #009A96;
}

#search {
    width: 80%;
    border: none;
    outline: none;
    background-color: inherit;
    text-align: left;
    height: 30px;
    font-size: 15px;
}

button[type="submit"] {
    background: inherit;
    cursor: pointer;
    border: none;
    border-left: 2px solid #009A96;
    height: 30px;
    font-size: 16px;
    width: 20%;
    color: #009A96;
}

button[type="submit"]:hover {
  box-shadow: 0 0 11px 2px rgba(33,33,33,.2); 
}
</style>