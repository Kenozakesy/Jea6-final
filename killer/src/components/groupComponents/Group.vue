<template>
   <router-link :to="'/server/' + group.id" @click.native="updateName(group.name)">
      <div class="shadow-sm p-3 mb-2 bg-white rounded" id="box">
         <img src="../../assets/discord-512.png" width="50" height="50">
         <p v-bind="group.id">{{group.name}}</p>
      </div>
   </router-link>
</template>

<script>
export default {
  name: 'Group',
  props: {
    group: Object,
    updateName: Function
  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style scoped>
p{
   margin: 5px;
   font-size: 11px;
}
#box{
   margin: 0px 5px 0px 0px;
}
</style>
