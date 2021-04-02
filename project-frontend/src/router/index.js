import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Noauth from '../components/Noauth.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
//import UserList from '../components/admin/UserList.vue'
import Appointment from '../components/admin/Appointment.vue'
import Customer from '../components/admin/Customer.vue'
Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    redirect:'/login',
  },
  {
    path:'/login',
    component:Login,
  },
  {
    path:'/home',
    component:Home,
    redirect:'/welcome',
    children:[
      {path:'/welcome', component:Welcome,},
      //{path:'/user', component:UserList,},
      {path:'/appointment', component:Appointment,},
      {path:'/customer', component:Customer,},
    ]
  },
  {
    path:'/noauth',
    component: Noauth
  }
]

const router = new VueRouter({
  routes,
  mode: 'hash',
  base: process.env.BASE_URL,
})

//挂载路由导航守卫
router.beforeEach((to,from,next)=>{
  // to 将要访问
  // from 从哪访问
  // next 接着干 next（url） 重定向到url上 next（）继续访问 to路径
  if (to.path=='/login') return next();
  //获取user
  const userFlag = window.sessionStorage.getItem("user"); //取出当前用户
  if (!userFlag) return next('/login');
  next(); //符合要求 放行
}) 

export default router
