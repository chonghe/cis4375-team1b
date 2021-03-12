import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import enLocale from 'element-ui/lib/locale/lang/en.js'

//添加全局样式
import './assets/css/global.css'
// 引入iconfont

//导入axios
import axios from 'axios'
import ElementUI from 'element-ui'
//挂载axios
Vue.prototype.$http = axios
//设置访问根路径
axios.defaults.baseURL = "/api"
//http://127.0.0.1:9000

Vue.use(ElementUI, { enLocale })

Vue.config.productionTip = false



new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
