import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import UserList from '../views/users/UserList.vue'
import CommentList from "@/views/comments/CommentList.vue";
import CommentForm from "@/views/comments/CommentForm.vue";

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

    //댓글 목록
    {
      path: '/comments',
      name: 'CommentList',
      component: CommentList,
      props: route => ({
        type: route.query.type || 'user',       //기본값: 'user'
        keyword: route.query.keyword || '',     //검색어 (예: 사용자ID, 리뷰ID, 댓글ID)
      }),
    },
    //댓글 작성
    {
      path: '/comments/create/:reviewId?',
      name: 'CommentCreate',
      component: CommentForm,
      props: true,
    },
    //댓글 수정
    {
      path: '/comments/:id/edit',
      name: 'CommentEdit',
      component: CommentList,
      props: route => ({ type: 'single', keyword: route.params.id })
    },
  ],
})

export default router