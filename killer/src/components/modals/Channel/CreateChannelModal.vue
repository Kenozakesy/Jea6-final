<template>
   <modal name="create-channel-modal"
          id="modal-test"
          :adaptive="true"
          :scrollable="false"
          :max-width=600
          height="100%"
          @before-open="beforeOpen"
          @before-close="beforeClose">
      <form id="scroll" class="overflow-y-auto p-8 center w-full h-full" @submit.prevent="save()">
         <div class="center w-full flex-col">

            <h2 align="center">New channel</h2>

            <p v-if="this.Error !== ''" class="error" align="center">{{ Error }}</p>

            <div class="w-full">
               <div class="flex flex-col p-4">
                  <input class="form-control"
                         v-model="channel.name"
                         type="text"
                         placeholder="Channel name">

                  <button class="btn btn-primary margin" >
                     Create channel
                  </button>
               </div>
            </div>
         </div>
      </form>

   </modal>
</template>

<script>
import axios from 'axios'

export default {
  name: 'create-channel-modal',
  data () {
    return {
      channel: {
        id: 1,
        name: '',
        serverId: null
      },
      Error: ''
    }
  },
  methods: {
    beforeOpen (e) {
      this.channel.serverId = e.params.serverId
    },
    beforeClose () {
      this.Error = ''
      this.channel.name = ''
    },
    save () {
      this.Error = ''
      console.log(this.channel.serverId)
      if (!this.channel.name) {
        this.Error = 'Not a valid channel name'
      } else {
        axios.post('http://localhost:8080/war/api/channel', this.channel, {
          headers: {
            'Content-Type': 'application/json; charset=utf-8',
            'Accept': 'application/json'
          }
        })
          .then((data) => {
            console.log(data.data)
          })
          .catch(error => {
            console.log(error)
          })
      }
    }
  }
}
</script>

<style scoped>

   .error {
      color: red;
      font-weight: bold;
      margin: 0px;
   }
   .margin{
      margin-top: 10px;
   }

</style>
