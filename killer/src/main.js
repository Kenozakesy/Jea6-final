import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VModal from 'vue-js-modal'

import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false
Vue.use(VModal)
Vue.use(BootstrapVue)

// components
Vue.component('create-server-modal', require('./components/modals/Server/CreateServerModal').default)
Vue.component('create-channel-modal', require('./components/modals/Channel/CreateChannelModal').default)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
