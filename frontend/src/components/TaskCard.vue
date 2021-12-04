<template>
  <router-link v-bind:class="{ header: header }" :to="taskLink">
    <div class="card" v-bind:class="{ header: header }">
      <div class="name">
        <p>{{ taskName }}</p>
      </div>
      <div class="responsible">
        <p>{{ responsible }}</p>
      </div>
      <div class="tags" v-if="!header">
        <span v-for="item in tags" :key="item" class="tag">{{item}}</span>
      </div>
      <div class="arrow" v-if="!header">
        <p>&#5171;</p>
      </div>
    </div>
  </router-link>
</template>

<script>
export default {
  props: [
    'taskId',
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
    taskLink() {
      return '/tasks/' + this.taskId;
    }
  }
}
</script>

<style scoped>
.card {
  width: 95%;
  margin: 0 auto;
  height: 60px;
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
  padding: 4px 5px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

a {
  color: black;
  text-decoration: none;
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
  margin: 18px 0;
}

.name {
  width: 34%;
  vertical-align: middle;
}

.header .name {
   padding-right: 0;
}

.tags {
  width: 30%;
  vertical-align: middle;
}

.tag {
  display: inline-block;
  background-color: #009A96;
  padding: 1px 10px;
  color: #fff;
  border-radius: 10px;
  margin: 4px 5px;
}


.responsible {
  width: 30%;
}
.header .responsible {
   padding-left: 0;
}
</style>