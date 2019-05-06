<template>
   <div class="shadow-sm p-3 mb-2 bg-white rounded" id="box">
      <b-row>
         <b-col cols="6">
            <router-link :to="'/server/' + channel.serverId + '/channel/' + channel.id" @click.native="updateName(channel.name)">
               <p class="margin">{{channel.name}}</p>
            </router-link>
         </b-col>
         <b-col cols="6">
            <b-dropdown size="sm" text="" class="m-2 ">
               <b-dropdown-item-button class="align" @click="deleteChannel">delete</b-dropdown-item-button>
               <b-dropdown-item-button class="align">change</b-dropdown-item-button>
            </b-dropdown>
         </b-col>
      </b-row>
   </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Channel',
  props: {
    channel: Object,
    updateName: Function
  },
  methods: {
    deleteChannel () {
      axios.delete('http://localhost:8080/war/api/channel/' + this.channel.id)
        .then((response) => {
          console.log(response.data)
        })
        .catch(error => {
          console.log(error.toString())
        })
    }
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
   .margin{
      margin-top: 15px;
      margin-left: 15px;
   }
   .align{
      font-size: 12px;
   }
</style>
