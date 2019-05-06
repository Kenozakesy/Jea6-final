<template>
   <div>
      <h2 id="login">Register</h2>
      <p v-if="this.Error !== ''" id="error">{{ Error }}</p>
      <p class="text" align="left">Username</p>
      <input type="text" v-model="form.username" placeholder="username" class="form-control"><br>
      <p class="text" align="left">Password</p>
      <input type="password" v-model="form.password" placeholder="password" class="form-control"><br>
      <p class="text" align="left">Password again</p>
      <input type="password" v-model="form.password2" placeholder="password again" class="form-control">
      <button type="submit" class="btn btn-primary" @click="register">Register</button>
   </div>
</template>

<script>
/* eslint-disable */
import axios from 'axios'
import router from '../router'

export default {
  name: 'RegisterBox',
  data () {
    return {
      form: {
        username: '',
        password: '',
        password2: ''
      },
      Error: ''
    }
  },
  methods: {
    register () {
      this.Error = ''
      if (this.form.username && this.form.password && this.form.password2) {
        if (this.form.password !== this.form.password2) {
          console.log('not same passwords')
          this.Error = 'passwords are not the same'
        }

         let info = JSON.stringify(
           {
             id : 1,
             name : this.form.username,
             password : this.form.password
           }
         )
        axios.post('http://localhost:8080/war/api/account', info, {
          headers: {
            'Content-Type': 'application/json; charset=utf-8',
            'Accept': 'application/json'
          }
        })
        .then((data) => {
            console.log(data.data)
            router.push('/login');
        })
        .catch(error => {
          console.log(error)
          this.Error = 'username already exists'
          this.form.username = ''
          this.form.password = ''
          this.form.password2 = ''
        })
      } else {
        this.Error = 'some fields are empty'
      }
    }
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
   #error{
      color: red;
      font-weight: bold;
   }
   .text{
      font-weight: bold;
      margin: 0px 0px 5px 5px;
   }
</style>
