import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import UserList from '../views/users/UserList.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/users',
      name: 'users',
      component: UserList
    },
    {
      path: '/users/edit/:id',
      name: 'UserEdit',
      component: () => import('@/views/users/UserEdit.vue')
    },
    {
      path: '/users/create',
      name: 'UserCreate',
      component: () => import('@/views/users/UserCreate.vue')
    },
  ],
})

export default router