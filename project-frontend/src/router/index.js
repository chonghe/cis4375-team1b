import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Noauth from '../components/Noauth.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import Calendar from '../components/admin/Calendar.vue'
import Appointment from '../components/admin/Appointment.vue'
import Employee from '../components/EmployeeList.vue'
import User from '../components/UserList.vue'
import Customer from '../components/admin/Customer.vue'
import EmpHome from '../components/EmpHome.vue'
import EmpCustomer from '../components/EmpCustomer.vue'
import EmpAppointment from '../components/EmpAppointment.vue'
import EmpCalendar from '../components/EmpCalendar.vue'
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
    redirect:'/calendar',
    children:[
      {path:'/welcome', component:Welcome,},
      {path:'/calendar', component:Calendar,},
      {path:'/appointment', component:Appointment,},
      {path: '/employee', component:Employee,},
      {path: '/userList', component: User,},
      {path:'/customer', component:Customer,},
    ]
  },
  
  {
    path:'/emphome',
    component:EmpHome,
    children:[
      {path:'/welcome', component:Welcome,},
      {path:'/empappointment', component:EmpAppointment,},
      {path:'/empcustomer', component:EmpCustomer,},
      {path:'/empcalendar', component:EmpCalendar,},
    ]
  },
  
  {
    path:'/noauth',
    component: Noauth
  }
]

const router = new VueRouter({
  routes,
  mode:'history',
  //mode: 'hash',
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
