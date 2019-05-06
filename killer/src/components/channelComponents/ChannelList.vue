<template>
   <div>
      <p>{{this.activeChannelName}}</p>

      <div class="shadow-none p-2 mb-3 bg-light rounded scroll">
         <ul>
            <li v-for="(channel, index) of channels" :key="index" >
               <Channel
              :channel="channel"
              :updateName="updateName"
               ></Channel>
            </li>
         </ul>
      </div>

      <div class="shadow-none p-3 mb-5 bg-light rounded">
         <button class="btn btn-primary" @click="$modal.show('create-channel-modal' , { serverId: server.id })">Create channel</button>
      </div>
      <create-channel-modal></create-channel-modal>
   </div>
</template>

<script>
/* eslint-disable */
import Channel from './Channel'
import virtualList from 'vue-virtual-scroll-list'
import axios from 'axios'

export default {
  name: 'ChannelList',
  props: {
    serverId: String
  },
  components: {
    Channel,
    virtualList
  },
  watch: {
    serverId: function (id) {
      this.server.id = id
      this.loadPage()
    },
    channels: {

    }
  },
  data() {
    return {
      channels: [],
      server: {
        id: this.serverId,
      },
      activeChannelName: 'channel name'
    }
  },
  methods: {
      loadPage() {
        console.log(this.server.id)
        axios.get('http://localhost:8080/war/api/channel/server/' + this.server.id)
          .then((response) => {
            console.log(response.data)
            this.channels = response.data
          })
          .catch(error => {
            console.log(error.toString())
          })
      },
      updateName (name) {
        this.activeChannelName = name
      }
  },
  created () {
     this.loadPage()
  }
}
</script>

<style scoped>
button {
   font-size: 15px;
}
ul {
   list-style-type: none;
   padding: 0px;
}
.scroll{
   overflow: auto;
   height: 500px;
}
</style>
