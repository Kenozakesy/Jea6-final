<template>
   <div id="app">
      <Header></Header>

      <b-container >
         <b-row>
            <b-col cols="2" class="margin">
               <GroupList
              :GroupId="serverId"
               ></GroupList>
            </b-col>
            <b-col cols="2" class="margin">
               <ChannelList
              :serverId="serverId"
               ></ChannelList>
            </b-col>
            <b-col cols="5" class="margin">
               <Chatbox
              :channelId=channelId
               ></Chatbox>
            </b-col>
            <b-col cols="3" class="margin">
               <Friendlist></Friendlist>
            </b-col>
         </b-row>
      </b-container>

   </div>
</template>

<script>
/* eslint-disable */
import { mapActions } from 'vuex'
import Header from '../components/Header'
import GroupList from '../components/groupComponents/GroupList'
import Chatbox from '../components/chatboxComponent/Chatbox'
import Friendlist from '../components/friends/Friendlist'
import ChannelList from '../components/channelComponents/ChannelList'

export default {
  name: 'Server',
  components: {
    ChannelList,
    Friendlist,
    Header,
    GroupList,
    Chatbox
  },
  data() {
    return {
      serverId: this.$route.params.serverId,
      channelId: this.$route.params.channelId
    }
  },
  watch: {
      '$route.params.serverId': function (id) {
         this.serverId = id
      },
      '$route.params.channelId': function (id) {
         this.channelId = id
      }
  },
  methods: {

  },
  created () {

  }
}
</script>

<style scoped>
.margin {
   padding: 0px 5px;
}
</style>
