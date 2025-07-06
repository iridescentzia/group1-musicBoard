import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import UserList from '../views/users/UserList.vue'

// Music 관련 컴포넌트 import
import MusicList from '@/views/music/MusicList.vue'
import MusicDetail from '@/views/music/MusicDetail.vue'

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

    // music 라우터
    {
      path: '/music',
      name: 'MusicList',
      component: MusicList
    },
    {
      path: '/music/:id',
      name: 'MusicDetail',
      component: MusicDetail,
      props: true
    }
  ],
})

export default router