<template>
   <div>
      <p>chatbox </p>
      <p>*under construction*</p>

      <div class="shadow-none p-2 mb-3 bg-light rounded scroll">
         <ul v-for="(message, index) of messages" :key="index">
            <li>
               <Message
                 :message="message"
               ></Message>
            </li>
         </ul>
      </div>

      <div id="text">
         <textarea class="form-control" aria-label="With textarea" placeholder="message"></textarea>
         <button type="submit" class="btn btn-primary" id="send" @click="sendMessage">Send</button>
      </div>
   </div>
</template>

<script>
import Message from './Message'
import axios from 'axios'
// import $ from 'jQuery'

export default {
  name: 'Chatbox',
  props: [
    'channelId'
  ],
  components: {
    Message
  },
  watch: {
    'channelId': function (channelId) {
      if (!channelId) {
        this.messages = []
        this.id = null
      } else {
        this.id = this.channelId
        this.loadPage()
        this.connect()
      }
    }
  },
  data () {
    return {
      id: this.channelId,
      messages: [],
      ws: null,
      test: 'test'
    }
  },
  methods: {
    loadPage () {
      axios.get('http://localhost:8080/war/api/post/channel/' + this.channelId, {})
        .then((response) => {
          this.messages = response.data
        })
        .catch(error => {
          console.log('chatbox ' + error.toString())
        })
    },
    connect () {
      if (this.ws) {
        console.log('exist')
        this.ws.onclose = function () {} // disable onclose handler first
        this.ws.close()
      }
      // let username = localStorage.getItem('username')

      // ws = new WebSocket('ws://' + 'localhost:9009' + pathname + '/chat/' + username)
      this.ws = new WebSocket('ws://' + 'localhost:8080/war/chat/' + this.id)
      console.log(this.ws)

      this.ws.onmessage = ((event) => {
        console.log(event.data)
        let message = JSON.parse(event.data)
        console.log(message.from + ' : ' + message.content + '\n')
        console.log(this.test)

        //works
      });
    },
    responseData: function () {
      console.log('test')
    },
    sendMessage () {
      var json = JSON.stringify({
        'content': 'string'
      })
      this.ws.send(json)
    }
  },
  created () {
    this.loadPage()
  }

}
</script>

<style scoped>
#send{
   margin: 10px;
}
#text{

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
