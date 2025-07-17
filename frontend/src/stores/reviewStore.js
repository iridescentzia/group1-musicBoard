import { defineStore } from 'pinia'
import { reviewService } from '@/services/reviewService.js'

export const useReviewStore = defineStore('review', {
    state: () => ({
        // 리뷰 데이터
        reviews: [],           // 전체 리뷰 목록
        currentReview: null,   // 현재 선택된 리뷰
        musicReviews: [],      // 특정 음악의 리뷰 목록

        // 로딩 상태
        loading: {
            list: false,
            detail: false,
            create: false,
            update: false,
            delete: false,
            musicReviews: false
        },

        // 에러 상태
        error: null
    }),

    getters: {
        // 리뷰 개수
        reviewCount: (state) => state.reviews.length,

        // 로딩 상태 확인
        isLoading: (state) => Object.values(state.loading).some(Boolean),

        // 에러 상태 확인
        hasError: (state) => !!state.error,

        // 최신 리뷰 (작성일 기준 정렬)
        latestReviews: (state) => {
            return [...state.reviews]
                .sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt))
                .slice(0, 5)
        }
    },

    actions: {
        // 에러 초기화
        clearError() {
            this.error = null
        },

        // 현재 리뷰 초기화
        clearCurrentReview() {
            this.currentReview = null
        },

        // 전체 리뷰 목록 조회
        async fetchReviews() {
            try {
                this.loading.list = true
                this.error = null

                const reviews = await reviewService.getList()
                this.reviews = reviews || []

                return reviews
            } catch (error) {
                this.error = error.response?.data?.message || '리뷰 목록을 불러오는데 실패했습니다.'
                console.error('fetchReviews error:', error)
                throw error
            } finally {
                this.loading.list = false
            }
        },

        // 특정 리뷰 조회
        async fetchReview(id) {
            try {
                this.loading.detail = true
                this.error = null

                const review = await reviewService.get(id)
                this.currentReview = review

                return review
            } catch (error) {
                this.error = error.response?.data?.message || '리뷰를 불러오는데 실패했습니다.'
                console.error('fetchReview error:', error)
                throw error
            } finally {
                this.loading.detail = false
            }
        },

        // 리뷰 생성
        async createReview(reviewData) {
            try {
                this.loading.create = true
                this.error = null

                await reviewService.create(reviewData)

                // 성공 후 목록 새로고침
                await this.fetchReviews()

                return true
            } catch (error) {
                this.error = error.response?.data?.message || '리뷰 작성에 실패했습니다.'
                console.error('createReview error:', error)
                throw error
            } finally {
                this.loading.create = false
            }
        },

        // 리뷰 수정
        async updateReview(id, reviewData) {
            try {
                this.loading.update = true
                this.error = null

                await reviewService.update(id, reviewData)

                // 현재 리뷰가 수정된 리뷰라면 다시 조회
                if (this.currentReview?.id === id) {
                    await this.fetchReview(id)
                }

                // 목록 새로고침
                await this.fetchReviews()

                return true
            } catch (error) {
                this.error = error.response?.data?.message || '리뷰 수정에 실패했습니다.'
                console.error('updateReview error:', error)
                throw error
            } finally {
                this.loading.update = false
            }
        },

        // 리뷰 삭제
        async deleteReview(id) {
            try {
                this.loading.delete = true
                this.error = null

                await reviewService.delete(id)

                // 로컬 상태에서도 제거
                this.reviews = this.reviews.filter(review => review.id !== id)

                // 현재 선택된 리뷰가 삭제된 리뷰라면 초기화
                if (this.currentReview?.id === id) {
                    this.currentReview = null
                }

                return true
            } catch (error) {
                this.error = error.response?.data?.message || '리뷰 삭제에 실패했습니다.'
                console.error('deleteReview error:', error)
                throw error
            } finally {
                this.loading.delete = false
            }
        },

        // 특정 음악의 리뷰 목록 조회
        async fetchMusicReviews(musicId) {
            try {
                this.loading.musicReviews = true
                this.error = null

                const reviews = await reviewService.getListByMusicId(musicId)
                this.musicReviews = reviews || []

                return reviews
            } catch (error) {
                this.error = error.response?.data?.message || '음악 리뷰를 불러오는데 실패했습니다.'
                console.error('fetchMusicReviews error:', error)
                throw error
            } finally {
                this.loading.musicReviews = false
            }
        },

        // 리뷰 검색 (클라이언트 사이드)
        searchReviews(keyword) {
            if (!keyword.trim()) {
                return this.reviews
            }

            const lowerKeyword = keyword.toLowerCase()
            return this.reviews.filter(review =>
                review.title?.toLowerCase().includes(lowerKeyword) ||
                review.content?.toLowerCase().includes(lowerKeyword) ||
                review.musicTitle?.toLowerCase().includes(lowerKeyword) ||
                review.artist?.toLowerCase().includes(lowerKeyword)
            )
        }
    }
})

export default useReviewStore