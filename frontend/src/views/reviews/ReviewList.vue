<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useReviewStore } from '@/stores/reviewStore.js'
import moment from 'moment'

const router = useRouter()
const reviewStore = useReviewStore()

// 검색 키워드
const searchKeyword = ref('')

// 필터된 리뷰 목록
const filteredReviews = computed(() => {
  if (!searchKeyword.value.trim()) {
    return reviewStore.reviews
  }
  return reviewStore.searchReviews(searchKeyword.value)
})

// 리뷰 목록 로드
const loadReviews = async () => {
  try {
    await reviewStore.fetchReviews()
  } catch (error) {
    console.error('리뷰 목록 로드 실패:', error)
  }
}

// 상세 페이지로 이동
const goToDetail = (reviewId) => {
  router.push(`/reviews/${reviewId}`)
}

// 텍스트 자르기
const truncateText = (text, maxLength) => {
  if (!text) return ''
  return text.length > maxLength ? text.substring(0, maxLength) + '...' : text
}

// 날짜 포맷팅
const formatDate = (dateString) => {
  if (!dateString) return ''
  return moment(dateString, 'YYYY-MM-DD HH:mm:ss').format('YYYY.MM.DD HH:mm')
}

// 컴포넌트 마운트 시 데이터 로드
onMounted(() => {
  loadReviews()
})
</script>

<template>
  <div class="review-list">
    <!-- 헤더 섹션 -->
    <div class="list-header">
      <h2>🎵 음악 리뷰</h2>
      <div class="header-actions">
        <input
            type="text"
            v-model="searchKeyword"
            placeholder="리뷰 검색..."
            class="search-input"
        />
        <router-link to="/reviews/write" class="btn btn-primary">
          ✏️ 리뷰 작성
        </router-link>
      </div>
    </div>

    <!-- 에러 상태 -->
    <div v-if="reviewStore.error" class="error-message">
      ❌ {{ reviewStore.error }}
      <button @click="loadReviews" class="btn">다시 시도</button>
    </div>

    <!-- 리뷰 목록 -->
    <div v-else-if="filteredReviews.length > 0" class="reviews-grid">
      <div
          v-for="review in filteredReviews"
          :key="review.id"
          class="review-card"
          @click="goToDetail(review.id)"
      >
        <!-- 음악 정보 -->
        <div class="music-info">
          <img
              :src="review.music?.coverUrl || '/default-cover.jpg'"
              :alt="review.music?.title"
              class="cover-image"
          />
          <div class="music-details">
            <h4 class="music-title">{{ review.music?.title || '제목 없음' }}</h4>
            <p class="music-artist">{{ review.music?.artist || '아티스트 정보 없음' }}</p>
            <span class="music-genre">{{ review.music?.genre }}</span>
          </div>
        </div>

        <!-- 별점 -->
        <div class="rating">
          <span class="stars">
            <span v-for="n in 5" :key="n" class="star" :class="{ filled: n <= review.rating }">
              ⭐
            </span>
          </span>
          <span class="rating-text">{{ review.rating }}/5</span>
        </div>

        <!-- 리뷰 내용 -->
        <div class="review-content">
          <p>{{ truncateText(review.content, 100) }}</p>
        </div>

        <!-- 메타 정보 -->
        <div class="review-meta">
          <span class="user-id">👤 User {{ review.userId }}</span>
          <span class="created-date">📅 {{ formatDate(review.createdAt) }}</span>
        </div>
      </div>
    </div>

    <!-- 빈 상태 -->
    <div v-else class="empty-state">
      <h3>아직 리뷰가 없어요</h3>
      <p>첫 번째 음악 리뷰를 작성해보세요!</p>
      <router-link to="/reviews/write" class="btn btn-primary">
        ✏️ 첫 리뷰 작성하기
      </router-link>
    </div>
  </div>
</template>

<style scoped>
.review-list {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.list-header h2 {
  margin: 0;
  color: #333;
}

.header-actions {
  display: flex;
  gap: 15px;
  align-items: center;
}

.search-input {
  padding: 10px 15px;
  border: 1px solid #ddd;
  border-radius: 5px;
  width: 250px;
}

.btn {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background: #007bff;
  color: white;
  text-decoration: none;
  cursor: pointer;
}

.btn-primary {
  background: #007bff;
}

.btn:hover {
  background: #0056b3;
}

.error-message {
  text-align: center;
  padding: 20px;
  background: #fee;
  border: 1px solid #fcc;
  border-radius: 5px;
  color: #c66;
  margin-bottom: 20px;
}

.reviews-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(400px, 1fr));
  gap: 20px;
}

.review-card {
  background: white;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  cursor: pointer;
}

.review-card:hover {
  border-color: #007bff;
}

.music-info {
  display: flex;
  gap: 15px;
  margin-bottom: 15px;
}

.cover-image {
  width: 80px;
  height: 80px;
  border-radius: 5px;
  object-fit: cover;
}

.music-details {
  flex: 1;
}

.music-title {
  margin: 0 0 5px 0;
  font-size: 16px;
  font-weight: 600;
}

.music-artist {
  margin: 0 0 5px 0;
  color: #666;
  font-size: 14px;
}

.music-genre {
  background: #f0f0f0;
  color: #666;
  padding: 2px 8px;
  border-radius: 10px;
  font-size: 12px;
}

.rating {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 15px;
}

.stars {
  display: flex;
}

.star {
  font-size: 16px;
  opacity: 0.3;
}

.star.filled {
  opacity: 1;
}

.rating-text {
  font-weight: 600;
  color: #007bff;
}

.review-content p {
  line-height: 1.5;
  color: #555;
  margin: 0 0 15px 0;
}

.review-meta {
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  color: #888;
  padding-top: 15px;
  border-top: 1px solid #eee;
}

.empty-state {
  text-align: center;
  padding: 60px 20px;
  background: white;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.empty-state h3 {
  margin: 0 0 10px 0;
  color: #666;
}

.empty-state p {
  margin: 0 0 20px 0;
  color: #888;
}
</style>