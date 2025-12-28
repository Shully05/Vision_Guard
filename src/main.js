import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/assets/global.css'
Vue.config.productionTip = false

import ElementUI from 'element-ui';//引入elementui
import 'element-ui/lib/theme-chalk/index.css';//引入elementui
//在main.js中完整的引入全局样式设置
Vue.use(ElementUI,{ size: 'small', zIndex: 3000 });//引入elementui小组件样式设置

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
