import { defineStore } from 'pinia'
import commentService from '../services/commentService'

export const useCommentStore = defineStore('comment', {
    state: () => ({
        commentsByReview: [],  //리뷰 기준 댓글 목록
        commentsByUser: [],    //사용자 기준 댓글 목록 (필요 시)
        singleComment: null,   //단일 댓글 상세
        loading: {
            listByReview: false,
            listByUser: false,
            single: false,
            create: false,
            update: false,
            delete: false
        },
        error: null
    }),

    getters: {
        commentCountByReview: (state) => state.commentsByReview.length,
        isLoading: (state) => Object.values(state.loading).some(Boolean),
        hasError: (state) => !!state.error
    },

    actions: {
        clearError() {
            this.error = null
        },

        clearSingleComment() {
            this.singleComment = null
        },

        //리뷰 기준 댓글 목록 조회
        async fetchCommentsByReviewId(reviewId) {
            this.loading.listByReview = true
            this.error = null
            try {
                this.commentsByReview = await commentService.getListByReviewId(reviewId)
            } catch (e) {
                this.error = e.response?.data?.message || e.message || '리뷰 기준 댓글 목록을 불러오는 데 실패했습니다.'
            } finally {
                this.loading.listByReview = false
            }
        },

        //사용자 기준 댓글 목록 조회
        async fetchCommentsByUserId(userId) {
            this.loading.listByUser = true
            this.error = null
            try {
                this.commentsByUser = await commentService.getListByUserId(userId)
            } catch (e) {
                this.error = e.response?.data?.message || e.message || '사용자 기준 댓글 목록을 불러오는 데 실패했습니다.'
            } finally {
                this.loading.listByUser = false
            }
        },

        //단일 댓글 조회
        async fetchComment(id) {
            this.loading.single = true
            this.error = null
            try {
                this.singleComment = await commentService.get(id)
            } catch (e) {
                this.error = e.response?.data?.message || e.message || '단일 댓글을 불러오는 데 실패했습니다.'
            } finally {
                this.loading.single = false
            }
        },

        //댓글 작성
        async createComment(type, id, commentData) {
            this.loading.create = true
            this.error = null
            try {
                await commentService.create(id, commentData)
                await this.reloadComments(type, id)
            } catch (e) {
                this.error = e.response?.data?.message || e.message || '댓글 작성에 실패했습니다.'
            } finally {
                this.loading.create = false
            }
        },

        //댓글 수정
        async updateComment(id, commentData, type, key) {
            this.loading.update = true
            this.error = null
            try {
                await commentService.update(id, commentData)
                await this.reloadComments(type, key)
            } catch (e) {
                this.error = e.response?.data?.message || e.message || '댓글 수정에 실패했습니다.'
            } finally {
                this.loading.update = false
            }
        },

        //댓글 삭제
        async deleteComment(id, type, key) {
            this.loading.delete = true
            this.error = null
            try {
                await commentService.delete(id)
                await this.reloadComments(type, key)
            } catch (e) {
                this.error = e.response?.data?.message || e.message || '댓글 삭제에 실패했습니다.'
            } finally {
                this.loading.delete = false
            }
        },

        async reloadComments(type, key) {
            if (type === 'user') {
                await this.fetchCommentsByUserId(key)
            } else if (type === 'review') {
                await this.fetchCommentsByReviewId(key)
            } else if (type === 'single') {
                await this.fetchComment(key)
            }
        }
    }
})

export default useCommentStore
