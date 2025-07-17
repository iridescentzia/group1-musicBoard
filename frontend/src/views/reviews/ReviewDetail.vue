<script setup>
import { computed, onMounted, onUnmounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useReviewStore } from '@/stores/reviewStore.js'
import moment from 'moment'

const route = useRoute()
const router = useRouter()
const reviewStore = useReviewStore()

// 현재 리뷰 정보
const review = computed(() => reviewStore.currentReview)

// 리뷰 로드
const loadReview = async () => {
  try {
    const reviewId = route.params.id
    await reviewStore.fetchReview(reviewId)
  } catch (error) {
    console.error('리뷰 로드 실패:', error)
  }
}

// 리뷰 수정 페이지로 이동
const editReview = () => {
  router.push(`/reviews/${route.params.id}/edit`)
}

// 리뷰 삭제 확인
const confirmDelete = () => {
  const confirmed = confirm('정말로 이 리뷰를 삭제하시겠습니까?')

  if (confirmed) {
    deleteReview()
  }
}

// 리뷰 삭제
const deleteReview = async () => {
  try {
    const reviewId = route.params.id
    await reviewStore.deleteReview(reviewId)

    alert('리뷰가 삭제되었습니다.')
    router.push('/reviews')
  } catch (error) {
    console.error('리뷰 삭제 실패:', error)
    alert('리뷰 삭제에 실패했습니다.')
  }
}

// 날짜 포맷팅
const formatDate = (dateString) => {
  if (!dateString) return ''
  return moment(dateString).format('YYYY.MM.DD')
}

// 날짜시간 포맷팅
const formatDateTime = (dateString) => {
  if (!dateString) return ''
  return moment(dateString, 'YYYY-MM-DD HH:mm:ss').format('YYYY년 MM월 DD일 HH:mm')
}

// 컴포넌트 마운트 시 데이터 로드
onMounted(() => {
  loadReview()
})

// 컴포넌트 언마운트 시 현재 리뷰 정보 클리어
onUnmounted(() => {
  reviewStore.clearCurrentReview()
})
</script>

<template>
  <div class="review-detail">
    <!-- 에러 상태 -->
    <div v-if="reviewStore.error" class="error-message">
      ❌ {{ reviewStore.error }}
      <div class="error-actions">
        <button @click="loadReview" class="btn">다시 시도</button>
        <router-link to="/reviews" class="btn">목록으로</router-link>
      </div>
    </div>

    <!-- 리뷰 상세 내용 -->
    <div v-else-if="review" class="review-content">
      <!-- 헤더 -->
      <div class="detail-header">
        <router-link to="/reviews" class="back-btn">
          ← 목록으로
        </router-link>
        <div class="header-actions">
          <button @click="editReview" class="btn">
            ✏️ 수정
          </button>
          <button @click="confirmDelete" class="btn btn-danger">
            🗑️ 삭제
          </button>
        </div>
      </div>

      <!-- 음악 정보 섹션 -->
      <div class="music-section">
        <img
            :src="review.music?.coverUrl || '/default-cover.jpg'"
            :alt="review.music?.title"
            class="cover-image"
        />
        <div class="music-info">
          <h1 class="music-title">{{ review.music?.title || '제목 없음' }}</h1>
          <h2 class="music-artist">{{ review.music?.artist || '아티스트 정보 없음' }}</h2>
          <div class="music-meta">
            <span v-if="review.music?.genre" class="genre-tag">
              🎵 {{ review.music.genre }}
            </span>
            <span v-if="review.music?.releaseDate" class="release-date">
              📅 {{ formatDate(review.music.releaseDate) }}
            </span>
          </div>
        </div>
      </div>

      <!-- 리뷰 정보 섹션 -->
      <div class="review-section">
        <!-- 별점 -->
        <div class="rating-section">
          <h3>평점</h3>
          <div class="rating-display">
            <div class="stars">
              <span v-for="n in 5" :key="n" class="star" :class="{ filled: n <= review.rating }">
                ⭐
              </span>
            </div>
            <span class="rating-text">{{ review.rating }}/5</span>
          </div>
        </div>

        <!-- 리뷰 내용 -->
        <div class="content-section">
          <h3>리뷰</h3>
          <div class="review-text">
            {{ review.content }}
          </div>
        </div>

        <!-- 메타 정보 -->
        <div class="meta-section">
          <div class="meta-item">
            <span class="meta-label">작성자:</span>
            <span class="meta-value">👤 User {{ review.userId }}</span>
          </div>
          <div class="meta-item">
            <span class="meta-label">작성일:</span>
            <span class="meta-value">📅 {{ formatDateTime(review.createdAt) }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 리뷰를 찾을 수 없음 -->
    <div v-else class="not-found">
      <h3>리뷰를 찾을 수 없습니다</h3>
      <p>요청하신 리뷰가 존재하지 않거나 삭제되었을 수 있습니다.</p>
      <router-link to="/reviews" class="btn">
        목록으로 돌아가기
      </router-link>
    </div>
  </div>
</template>

<style scoped>
.review-detail {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.error-message {
  text-align: center;
  padding: 20px;
  background: #fee;
  border: 1px solid #fcc;
  border-radius: 5px;
  color: #c66;
}

.error-actions {
  margin-top: 15px;
  display: flex;
  gap: 10px;
  justify-content: center;
}

.detail-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
  padding-bottom: 15px;
  border-bottom: 1px solid #eee;
}

.back-btn {
  color: #007bff;
  text-decoration: none;
}

.back-btn:hover {
  text-decoration: underline;
}

.header-actions {
  display: flex;
  gap: 10px;
}

.btn {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  background: #007bff;
  color: white;
  text-decoration: none;
  cursor: pointer;
}

.btn:hover {
  background: #0056b3;
}

.btn-danger {
  background: #dc3545;
}

.btn-danger:hover {
  background: #c82333;
}

.music-section {
  display: flex;
  gap: 30px;
  margin-bottom: 30px;
  padding: 20px;
  background: white;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.cover-image {
  width: 200px;
  height: 200px;
  border-radius: 8px;
  object-fit: cover;
}

.music-info {
  flex: 1;
}

.music-title {
  margin: 0 0 15px 0;
  font-size: 28px;
  font-weight: bold;
  color: #333;
}

.music-artist {
  margin: 0 0 20px 0;
  font-size: 20px;
  color: #666;
  font-weight: 500;
}

.music-meta {
  display: flex;
  gap: 15px;
}

.genre-tag, .release-date {
  background: #f8f9fa;
  color: #666;
  padding: 5px 10px;
  border-radius: 15px;
  font-size: 14px;
  border: 1px solid #dee2e6;
}

.review-section {
  background: white;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
}

.review-section h3 {
  margin: 0 0 15px 0;
  color: #333;
  font-size: 18px;
}

.rating-section {
  margin-bottom: 25px;
  padding-bottom: 25px;
  border-bottom: 1px solid #eee;
}

.rating-display {
  display: flex;
  align-items: center;
  gap: 15px;
}

.stars {
  display: flex;
}

.star {
  font-size: 20px;
  opacity: 0.3;
}

.star.filled {
  opacity: 1;
}

.rating-text {
  font-size: 18px;
  font-weight: bold;
  color: #007bff;
}

.content-section {
  margin-bottom: 25px;
  padding-bottom: 25px;
  border-bottom: 1px solid #eee;
}

.review-text {
  line-height: 1.6;
  font-size: 16px;
  color: #444;
  background: #f8f9fa;
  padding: 15px;
  border-radius: 5px;
  border-left: 4px solid #007bff;
}

.meta-section {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.meta-item {
  display: flex;
  gap: 15px;
}

.meta-label {
  font-weight: 600;
  color: #666;
  min-width: 80px;
}

.meta-value {
  color: #333;
}

.not-found {
  text-align: center;
  padding: 60px 20px;
  background: white;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.not-found h3 {
  margin: 0 0 10px 0;
  color: #666;
}

.not-found p {
  margin: 0 0 20px 0;
  color: #888;
}
</style>