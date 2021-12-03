<template>
  <div class="card">
    <div class="id">
      <p>{{projectId}}</p>
    </div>
    <div class="name">
      <p>{{projectName}}</p>
    </div>
    <div class="organisation">
      <p>{{organisation}}</p>
    </div>
    <div class="status">
      <p>{{projectStatus}}</p>
    </div>
    <div class="arrow">
      <p>&#5171;</p>
    </div>
  </div>
</template>

<script>
export default {
  props: [
    'projectName',
    'projectId',
    'organisation',
    'projectStatus'
  ]
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
  box-shadow: 0 0 11px rgba(33,33,33,.2); 
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

div {
  padding: 0 5px;
  overflow: hidden;
}

.arrow {
  width: 6%;
}

.arrow p {
  text-align: right;
  margin: 13px 0;
}

.name {
  width: 50%;
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
  width:21%;
}
</style>