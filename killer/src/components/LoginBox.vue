<template>
   <div>
     <h2 id="login">Login</h2>
     <p v-if="loginError">{{ loginError }}</p>
     <form @submit.prevent="loginSubmit">
       <input type="text" v-model="form.username" placeholder="username" class="form-control"><br>
       <input type="password" v-model="form.password" placeholder="password" class="form-control">
       <button type="submit" class="btn btn-primary">Login</button>
     </form>
      <button @click="registerViewPush" class="btn btn-primary">Register</button><br />
   </div>
</template>

<script>
/* eslint-disable */
import router from '../router'
import { mapState, mapActions } from 'vuex';

export default {
  name: 'LoginBox',
  components: {
  },
  data() {
    return {
      form: {
        username: '',
        password: ''
      },
      token: ''
    }
  },
  computed: {
    ...mapState([
      'loggingIn',
      'loginError',
    ])
  },
  methods: {
    ...mapActions([
      'doLogin'
    ]),
    loginSubmit() {

      // TODO: check login here
      // TODO: set a new hash code in database
      // TODO: send mail in java
      // TODO: check hashcode with the login (down)

        this.doLogin({
          username : this.form.username,
          password : this.form.password,
        });
    },
    registerViewPush(){
      router.push('/register');
    },
  }
}
</script>

<style scoped>
.btn{
  margin: 20px 10px 0 0;
}
#login{
   color: white;
}
</style>
