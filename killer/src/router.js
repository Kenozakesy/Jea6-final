/* eslint-disable */

import Vue from 'vue'
import Router from 'vue-router'
import login from './views/Login.vue'
import store from './store'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/login',
      name: 'login',
      meta: {title: 'login'},
      component: () => import(/* webpackChunkName: "login" */ './views/Login.vue')
    },
    {
      path: '/users',
      name: 'users',
      meta: {title: 'users'},
      component: () => import(/* webpackChunkName: "users" */ './views/Users.vue')
    },
    {
      path: '/home',
      name: 'home',
      meta: {title: 'home'},
      component: () => import(/* webpackChunkName: "users" */ './views/Home.vue')
    },
    // {
    //   path: '/server',
    //   name: 'server',
    //   meta: {title: 'server'},
    //   component: () => import(/* webpackChunkName: "login" */ './views/Server.vue')
    // },
    {
      path: '/server/:serverId',
      name: 'server',
      meta: {title: 'server'},
      component: () => import(/* webpackChunkName: "login" */ './views/Server.vue')
    },
    {
      path: '/server/:serverId/channel/:channelId',
      name: 'chat',
      meta: {title: 'chat'},
      component: () => import(/* webpackChunkName: "login" */ './views/Server.vue')
    },
    {
      path: '/register',
      name: 'register',
      meta: {title: 'register'},
      component: () => import(/* webpackChunkName: "users" */ './views/Register.vue')
    },
    {
      path: '/group',
      name: 'group',
      meta: {title: 'group'},
      component: () => import(/* webpackChunkName: "users" */ './views/Group.vue')
    },
    {
      path: '*',
      redirect: '/login'
    }
  ]
})

router.beforeEach((to, from, next) => {
  document.title = to.meta.title
  store.dispatch('fetchAccessToken')

  if (!store.state.accessToken) {
    if (to.fullPath !== '/login' && to.fullPath !=='/register') {
      next('/login')
    }
  }

  //works
  if (store.state.accessToken) {
    if (to.fullPath === '/login' || to.fullPath === '/register') {
      next('/home')
    }
  }

  next();
})

export default router
