import axios from 'axios'

// API 기본 설정
const API_BASE_URL = 'http://localhost:8080/api'

const api = axios.create({
    baseURL: API_BASE_URL,
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json'
    }
})

// 요청 인터셉터 (로깅용)
api.interceptors.request.use(
    (config) => {
        console.log(`🔄 API Request: ${config.method?.toUpperCase()} ${config.url}`)
        return config
    },
    (error) => Promise.reject(error)
)

// 응답 인터셉터 (에러 처리)
api.interceptors.response.use(
    (response) => {
        console.log(`✅ API Response: ${response.config.method?.toUpperCase()} ${response.config.url}`)
        return response
    },
    (error) => {
        console.error('❌ API Error:', error.response?.data || error.message)
        return Promise.reject(error)
    }
)

export const reviewService = {
    // 전체 리뷰 목록 조회
    async getList() {
        const response = await api.get('/reviews')
        return response.data // List<ReviewDTO>
    },

    // 특정 리뷰 조회
    async get(id) {
        const response = await api.get(`/reviews/${id}`)
        return response.data // ReviewDTO
    },

    // 리뷰 작성
    async create(reviewData) {
        const response = await api.post('/reviews', reviewData)
        return response.data // void (성공 시 빈 응답)
    },

    // 리뷰 수정
    async update(id, reviewData) {
        const response = await api.put(`/reviews/${id}`, reviewData)
        return response.data // void (성공 시 빈 응답)
    },

    // 리뷰 삭제
    async delete(id) {
        const response = await api.delete(`/reviews/${id}`)
        return response.data // void (성공 시 빈 응답)
    },

    // 특정 음악의 리뷰 목록 조회
    async getListByMusicId(musicId) {
        const response = await api.get(`/music/${musicId}/reviews`)
        return response.data // List<ReviewDTO>
    }
}

export default reviewService