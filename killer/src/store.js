import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from './router'
import jwtDecode from 'jwt-decode'

Vue.use(Vuex)

export default new Vuex.Store({
  /* eslint-disable */
  state: {
    accessToken: null,
    username: '',
    userId: null,
    loggingIn: false,
    loginError: null,
  },
  mutations: {
    loginStart: state => state.loggingIn = true,
    loginStop: (state, errorMessage) => {
      state.loggingIn = false;
      state.loginError = errorMessage;
    },
    updateAccessToken: (state, accessToken) => {
      state.accessToken = accessToken;
    },
    logout: (state) => {
      state.accessToken = null;
    }
  },
  actions: {
    doLogin({ commit }, loginData) {
      commit('loginStart');

      console.log(loginData)
      axios.post('http://localhost:8080/war/api/authentication', loginData, {
        headers:
              {
                'Content-Type': 'application/json; charset=utf-8',
                'Accept': 'application/json'
              }
      })
        .then(response => {
          console.log(response.data)

          let decoded = jwtDecode(response.data)

          localStorage.setItem('username', decoded.User);
          localStorage.setItem('userId', decoded.ID);
          localStorage.setItem('accessToken', response.data);

          axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('accessToken') //own doing
          commit('loginStop', null);
          commit('updateAccessToken', response.data);

          router.push('/home');
        })
        .catch(error => {
          console.log(error.toString())
          commit('loginStop', error.response.data.error);
          commit('updateAccessToken', null);
        })
    },
    fetchAccessToken({ commit }) {
      commit('updateAccessToken', localStorage.getItem('accessToken'));
    },
    logout({ commit }) {
      // localStorage.removeItem('token');
      localStorage.clear();
      localStorage.removeItem('username');
      localStorage.removeItem('userId');
      localStorage.removeItem('accessToken');
      axios.defaults.headers.common['Authorization'] = null;
      commit('logout');
      router.push('/login');
    }
  }
})
