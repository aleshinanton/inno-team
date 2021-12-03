<template>
  <div class="card" v-bind:class="{ header: header }">
    <div class="name">
      <p>{{ taskName }}</p>
    </div>
    <div class="initiator">
      <p>{{ initiator }}</p>
    </div>
    <div class="responsible">
      <p>{{ responsible }}</p>
    </div>
    <div class="tags">
      <span v-for="item in tags" :key="item"><span v-if="!header" class="tag">{{item}}</span></span>
    </div>
    <div class="arrow">
      <p v-if="!header">&#5171;</p>
    </div>
  </div>
</template>

<script>
export default {
  props: [
    'taskName',
    'responsible',
    'initiator',
    'tags',
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

<style>
.card {
  width: 95%;
  margin: 0 auto;
  height: 50px;
  display: flex;
  padding: 0 15px;
  border-radius: 3px 0 0;
}

.card:hover {
  box-shadow: 0 0 11px rgba(33, 33, 33, .2);
  border-bottom: 2px solid #009A96;
  cursor: pointer;
}

.header {
  pointer-events: none;
  opacity: 0.6;
  border-bottom: 2px solid #009A96;
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
  width: 20%;
  vertical-align: middle;
}

.tags {
  width: 30%;
  vertical-align: middle;
}

.tag {
  background-color: #009A96;
  padding: 2px 10px;
  color: #fff;
  border-radius: 10px;
  margin-left: 10px;
}

.initiator {
  width: 22%;
  vertical-align: middle;
}

.responsible {
  width: 22%;
}
</style>