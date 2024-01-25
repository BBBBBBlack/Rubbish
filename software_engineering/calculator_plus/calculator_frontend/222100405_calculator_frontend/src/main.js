// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import Rate from './Rate'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false
Vue.config.devtools = true
/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App),
  components: { App },
}).$mount('#app')
