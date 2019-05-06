<template>
   <div>
      <p>friendlist</p>
      <p>*under construction*</p>
      <div class="shadow-none p-2 mb-3 bg-light rounded">
         <virtual-list :size="40" :remain="12" >
            <item v-for="(friend, index) of friends" :key="index" >
               <Friend></Friend>
            </item>
         </virtual-list>
      </div>
   </div>
</template>

<script>
import Friend from './Friend'
export default {
  name: 'Friendlist',
  components: {
    Friend
  },
  data () {
    return {
      friends: ['friend', 'friend', 'friend', 'friend', 'friend', 'friend', 'friend']
    }
  },
  created () {

  }
}
</script>

<style scoped>

.margin{
   padding: 10px 0px 0px 0px;
}

</style>
