<template>
  <router-link v-bind:class="{ header: header }" :to="projectLink">
    <div class="card" v-bind:class="{ header: header }">
      <div class="id">
        <p>{{ projectId }}</p>
      </div>
      <div class="name">
        <p>{{ projectName }}</p>
      </div>
      <div class="organisation">
        <p>{{ organisation }}</p>
      </div>
      <div class="status">
        <p><span v-if="!header" :class="setColor()">&#9679;</span> {{ projectStatus }}</p>
      </div>
      <div class="arrow">
        <p v-if="!header">&#5171;</p>
      </div>
    </div>
  </router-link>
</template>

<script>
export default {
  props: [
    'projectName',
    'projectId',
    'organisation',
    'projectStatus',
    'header'
  ],
  data() {
    return {
      colorMarker: 'green'
    }
  },
  methods: {
    setColor() {
      return this.projectStatus === 'Идея' ? 'green' : (this.projectStatus === 'Прототип' ? 'blue' : (this.projectStatus === 'Продукт' ? 'orange' : 'black'))
    }
  },
  computed: {
    projectLink() {
      return '/projects/' + this.projectId;
    }
  }
}
</script>

<style scoped>
.card {
  width: 95%;
  margin: 0 auto;
  height: 50px;
  display: flex;
  padding: 0 15px;
  border-radius: 3px 0 0;
}

.header {
  pointer-events: none;
  opacity: 0.6;
  border-bottom: 2px solid #009A96;
}

.card:hover {
  box-shadow: 0 0 11px rgba(33, 33, 33, .2);
  border-bottom: 2px solid #009A96;
  cursor: pointer;
}

p {
  text-align: left;
  padding: 0 5px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

a {
  color: black;
  text-decoration: none;
}

.green {
  color: green;
}

.blue {
  color: rgb(75, 75, 245);
}

.orange {
  color: orange;
}

div {
  padding: 0 5px;
  overflow: hidden;
}

.arrow {
  width: 6%;
  color: #009A96;
}

.arrow p {
  text-align: right;
  margin: 13px 0;
}

.name {
  width: 41%;
  vertical-align: middle;
}

.id {
  width: 5%;
  vertical-align: middle;
}

.status {
  width: 15%;
  vertical-align: middle;
}

.organisation {
  width: 30%;
}
</style>