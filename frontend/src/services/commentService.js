import axios from 'axios'

const api = axios.create({
    baseURL: '/api',
    headers: { 'Content-Type': 'application/json' }
})

    // 리뷰 ID 기준 댓글 목록 조회
const commentService = {
    async getListByReviewId(reviewId) {
        const res = await api.get(`/reviews/${reviewId}/comments`)
        return res.data
    },

    // 사용자 ID 기준 댓글 목록 조회
    async getListByUserId(userId) {
        const res = await api.get(`/users/${userId}/comments`)
        return res.data
    },

    // 단일 댓글 조회
    async get(id) {
        const res = await api.get(`/comments/${id}`)
        return res.data
    },

    // 댓글 작성
    async create(reviewId, commentData) {
        const res = await api.post(`/reviews/${reviewId}/comments`, commentData)
        return res.data
    },

    // 댓글 수정
    async update(id, commentData) {
        const res = await api.put(`/comments/${id}`, commentData)
        return res.data
    },

    // 댓글 삭제
    async delete(id) {
        const res = await api.delete(`/comments/${id}`)
        return res.data
    }
}

export default commentService
