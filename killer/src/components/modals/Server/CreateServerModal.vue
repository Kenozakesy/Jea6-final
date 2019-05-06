<template>
   <modal name="create-server-modal"
          id="modal-test"
          :adaptive="true"
          :scrollable="false"
          :max-width=600
          height="100%"
          @before-open="beforeOpen"
          @before-close="beforeClose">
      <form id="scroll" class="overflow-y-auto p-8 center w-full h-full" @submit.prevent="save()">
         <div class="center w-full flex-col">

            <h2 align="center">New server</h2>

            <p v-if="this.Error !== ''" class="error" align="center">{{ Error }}</p>

            <div class="w-full">
               <div class="flex flex-col p-4">
                  <input class="form-control"
                         v-model="server.name"
                         type="text"
                         placeholder="Servername">

                  <button class="btn btn-primary margin" >
                     Create server
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
  name: 'create-server-modal',
  data () {
    return {
      server: {
        id: 1,
        name: '',
        userID: null
      },
      Error: ''
    }
  },
  methods: {
    beforeOpen () {

    },
    beforeClose () {
      this.Error = ''
      this.server.name = ''
    },
    save () {
      this.Error = ''
      if (!this.server.name) {
        this.Error = 'Not a valid server name'
      } else {
        this.server.userID = localStorage.getItem('userId')

        axios.post('http://localhost:8080/war/api/server', this.server, {
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
