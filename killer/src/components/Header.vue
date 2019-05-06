/* eslint-disable */
<template>

   <div>
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
         <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="nav justify-content-center">
               <li class="nav-item">
                  <button class="btn btn-primary" @click=logout>Logout</button>
               </li>
               <li class="nav-item">
                  <button class="btn btn-primary" @click=sendMail>Logout</button>
               </li>
            </ul>
         </div>
      </nav>

   </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
// import jwtDecode from 'jwt-decode'

export default {
  name: 'Header',
  components: {

  },
  computed: {
    ...mapState([
      'loggingIn',
      'loginError'
    ])
  },
  methods: {
    ...mapActions([
      'logout'
    ]),
    sendMail () {
      console.log(localStorage.getItem('accessToken'))
      console.log(localStorage.getItem('username'))
      console.log(localStorage.getItem('userId'))
    }
  }
}
</script>
/* eslint-disable */

<style scoped>
ul{
margin-left: 400px;
}
li{
margin-right: 50px;
}

</style>
