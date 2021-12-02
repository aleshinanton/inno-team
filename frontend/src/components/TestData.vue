<template>
<div>
    <p>{{data}}</p>
</div>
</template>

<script>
import { getTestData } from '../services/test'
export default {
  name: 'Dashboard',
  data() {
      return {
          data: []
      }
  },
  methods: {
    getTestData() {
      getTestData().then(response => {
        console.log(response)
        this.data = response
      })
    }
  },
  mounted () {
    this.getTestData();
  }
}
</script>