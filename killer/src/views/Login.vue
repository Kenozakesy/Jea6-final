<template>
  <div id="app">
      <b-container class="bv-example-row">
        <b-row>
          <b-col sm="6" offset="3">
            <b-jumbotron id="jumbo">
              <img class="discord_logo" alt="Vue logo" src="../assets/discord-512.png" sm="6" offset="3">
              <LoginBox></LoginBox>
            </b-jumbotron>
          </b-col>
        </b-row>
      </b-container>
  </div>
</template>

<script>
// @ is an alias to /src

import LoginBox from '../components/LoginBox'
export default {
  name: 'home',
  components: {
    LoginBox
  }
}
</script>

<style scoped>
  .discord_logo {
    width: 300px;
    height: 300px;
  }
  #app{
    margin-top: 100px;
  }
  #jumbo{
    background-color: darkgray;
  }
</style>
