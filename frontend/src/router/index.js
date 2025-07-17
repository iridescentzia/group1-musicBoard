import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import UserList from '../views/users/UserList.vue'

// Music 관련 컴포넌트 import
import MusicList from '@/views/music/MusicList.vue'
import MusicDetail from '@/views/music/MusicDetail.vue'

// Review 관련 컴포넌트
import ReviewList from '@/views/reviews/ReviewList.vue'
import ReviewDetail from '@/views/reviews/ReviewDetail.vue'
import ReviewForm from '@/views/reviews/ReviewForm.vue'

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
    // 리뷰 관련 라우트
    {
      path: '/reviews',
      name: 'ReviewList',
      component: ReviewList
    },
    {
      path: '/reviews/write',
      name: 'ReviewCreate',
      component: ReviewForm
    },
    {
      path: '/reviews/:id',
      name: 'ReviewDetail',
      component: ReviewDetail,
      props: true // route params를 props로 전달
    },
    {
      path: '/reviews/:id/edit',
      name: 'ReviewEdit',
      component: ReviewForm,
      props: true // route params를 props로 전달
    },
    // 404 페이지 (모든 라우트의 마지막에 위치)
    {
      path: '/:pathMatch(.*)*',
      name: 'NotFound',
      redirect: '/'
    }
  ],
})

export default router