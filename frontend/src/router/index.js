import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/',
    name: 'home',
    component: HomeView,
    children: [          // 嵌套路由
      {
        path: '',        // 默认子路由
        name: 'index',
        component: () => import('../views/IndexView.vue')
      },
      {
        path: 'manager', // 访问路径：/manager
        name: 'manager',
        component: () => import('../views/ManagerView.vue')
      },
      {
        path: 'student', // 访问路径：/student
        name: 'student',
        component: () => import('../views/StudentView.vue')
      },
      {
        path: 'teacher', // 访问路径：/teacher
        name: 'teacher',
        component: () => import('../views/TeacherView.vue')
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router