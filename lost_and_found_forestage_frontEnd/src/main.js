// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from '@/store'                   // api: https://github.com/vuejs/vuex
import VueCookies from 'vue-cookies'           
import ElementUI from 'element-ui';
import '@/element-ui-theme'
import '@/assets/scss/index.scss'
import 'element-ui/lib/theme-chalk/index.css';
import httpRequest from '@/utils/httpRequest' // api: https://github.com/axios/axios
import { isAuth } from '@/utils'
import cloneDeep from 'lodash/cloneDeep'
import './utils/filter_utils.js'
import infiniteScroll from 'vue-infinite-scroll';

Vue.use(infiniteScroll);
Vue.use(ElementUI);
Vue.use(VueCookies)
Vue.config.productionTip = false

// 挂载全局
Vue.prototype.$http = httpRequest // ajax请求方法
Vue.prototype.isAuth = isAuth     // 权限方法

// 保存整站vuex本地储存初始状态
window.SITE_CONFIG['storeState'] = cloneDeep(store.state)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
