<template>
    <div class="links">
        <router-link to="/projects"><h3>Реестр решений</h3></router-link>
        <router-link to="/tasks"><h3 class="separator">Реестр задач</h3></router-link>
    </div>
</template>

<script>
export default {

}
</script>

<style>
.links {
    display: flex;
}

a {
    text-decoration: none;
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