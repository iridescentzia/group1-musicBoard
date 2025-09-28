<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useReviewStore } from '@/stores/reviewStore.js'

const route = useRoute()
const router = useRouter()
const reviewStore = useReviewStore()

// 수정 모드 여부
const isEditMode = computed(() => !!route.params.id)

// 제출 중 상태
const isSubmitting = ref(false)

// 폼 데이터
const formData = ref({
  userId: 1, // 기본값
  musicId: null,
  content: '',
  rating: 5
})

// 폼 유효성 검사
const isFormValid = computed(() => {
  return formData.value.userId > 0 &&
      formData.value.musicId > 0 &&
      formData.value.content.trim().length > 0 &&
      formData.value.rating >= 1 &&
      formData.value.rating <= 5
})

// 별점 설정
const setRating = (rating) => {
  formData.value.rating = rating
}

// 수정 모드일 때 기존 데이터 로드
const loadReviewForEdit = async () => {
  try {
    const reviewId = route.params.id
    await reviewStore.fetchReview(reviewId)

    const review = reviewStore.currentReview
    if (review) {
      formData.value = {
        userId: review.userId,
        musicId: review.musicId,
        content: review.content,
        rating: review.rating
      }
    }
  } catch (error) {
    console.error('리뷰 로드 실패:', error)
  }
}

// 폼 제출 처리
const handleSubmit = async () => {
  if (!isFormValid.value || isSubmitting.value) return

  isSubmitting.value = true

  try {
    if (isEditMode.value) {
      // 수정 모드
      const reviewId = route.params.id
      await reviewStore.updateReview(reviewId, formData.value)
      alert('리뷰가 수정되었습니다!')
      router.push(`/reviews/${reviewId}`)
    } else {
      // 생성 모드
      await reviewStore.createReview(formData.value)
      alert('리뷰가 작성되었습니다!')
      router.push('/reviews')
    }
  } catch (error) {
    console.error('폼 제출 실패:', error)
    alert(`리뷰 ${isEditMode.value ? '수정' : '작성'}에 실패했습니다.`)
  } finally {
    isSubmitting.value = false
  }
}

// 취소 처리
const handleCancel = () => {
  const hasChanges = formData.value.content.trim() !== '' || formData.value.rating !== 5

  if (hasChanges) {
    const confirmed = confirm('작성 중인 내용이 있습니다. 정말 취소하시겠습니까?')
    if (!confirmed) return
  }

  if (isEditMode.value) {
    router.push(`/reviews/${route.params.id}`)
  } else {
    router.push('/reviews')
  }
}

// 컴포넌트 마운트 시 처리
onMounted(() => {
  if (isEditMode.value) {
    loadReviewForEdit()
  }
})

// 에러 상태 감지해서 리셋
watch(() => reviewStore.error, (newError) => {
  if (newError) {
    isSubmitting.value = false
  }
})
</script>

<template>
  <div class="review-form">
    <!-- 헤더 -->
    <div class="form-header">
      <router-link to="/reviews" class="back-btn">
        ← 목록으로
      </router-link>
      <h2>{{ isEditMode ? '리뷰 수정' : '새 리뷰 작성' }}</h2>
    </div>

    <!-- 에러 상태 -->
    <div v-if="reviewStore.error" class="error-message">
      ❌ {{ reviewStore.error }}
    </div>

    <!-- 폼 -->
    <form v-else @submit.prevent="handleSubmit" class="review-form-container">
      <!-- 사용자 ID (임시) -->
      <div class="form-group">
        <label for="userId">사용자 ID</label>
        <input
            id="userId"
            type="number"
            v-model.number="formData.userId"
            min="1"
            required
            placeholder="사용자 ID를 입력하세요"
        />
        <small class="help-text">임시로 사용자 ID를 직접 입력합니다.</small>
      </div>

      <!-- 음악 ID (임시) -->
      <div class="form-group">
        <label for="musicId">음악 ID</label>
        <input
            id="musicId"
            type="number"
            v-model.number="formData.musicId"
            min="1"
            required
            placeholder="음악 ID를 입력하세요"
        />
        <small class="help-text">리뷰할 음악의 ID를 입력하세요.</small>
      </div>

      <!-- 별점 -->
      <div class="form-group">
        <label>평점</label>
        <div class="rating-input">
          <div class="stars">
            <button
                v-for="n in 5"
                :key="n"
                type="button"
                class="star-btn"
                :class="{ active: n <= formData.rating }"
                @click="setRating(n)"
            >
              ⭐
            </button>
          </div>
          <span class="rating-text">{{ formData.rating }}/5</span>
        </div>
        <small class="help-text">별을 클릭해서 평점을 매겨주세요.</small>
      </div>

      <!-- 리뷰 내용 -->
      <div class="form-group">
        <label for="content">리뷰 내용</label>
        <textarea
            id="content"
            v-model="formData.content"
            rows="8"
            required
            placeholder="이 음악에 대한 솔직한 리뷰를 작성해주세요..."
            maxlength="1000"
        ></textarea>
        <div class="char-count">
          {{ formData.content.length }}/1000자
        </div>
      </div>

      <!-- 버튼들 -->
      <div class="form-actions">
        <button
            type="button"
            @click="handleCancel"
            class="btn btn-secondary"
            :disabled="isSubmitting"
        >
          취소
        </button>
        <button
            type="submit"
            class="btn btn-primary"
            :disabled="isSubmitting || !isFormValid"
        >
          {{ isSubmitting ? (isEditMode ? '수정 중...' : '작성 중...') : (isEditMode ? '✏️ 수정 완료' : '📝 작성 완료') }}
        </button>
      </div>
    </form>
  </div>
</template>

<style scoped>
.review-form {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.form-header {
  display: flex;
  align-items: center;
  gap: 15px;
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

.form-header h2 {
  margin: 0;
  color: #333;
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

.review-form-container {
  background: white;
  padding: 30px;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.form-group {
  margin-bottom: 25px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: 600;
  color: #333;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
}

.form-group input:focus,
.form-group textarea:focus {
  outline: none;
  border-color: #007bff;
}

.form-group textarea {
  resize: vertical;
  min-height: 120px;
  line-height: 1.5;
}

.help-text {
  display: block;
  margin-top: 5px;
  color: #666;
  font-size: 12px;
}

.rating-input {
  display: flex;
  align-items: center;
  gap: 15px;
  margin-bottom: 8px;
}

.stars {
  display: flex;
}

.star-btn {
  background: none;
  border: none;
  font-size: 24px;
  cursor: pointer;
  opacity: 0.3;
  padding: 2px;
}

.star-btn:hover,
.star-btn.active {
  opacity: 1;
}

.rating-text {
  font-weight: 600;
  color: #007bff;
}

.char-count {
  text-align: right;
  margin-top: 5px;
  color: #666;
  font-size: 12px;
}

.form-actions {
  display: flex;
  gap: 15px;
  justify-content: flex-end;
  margin-top: 30px;
  padding-top: 20px;
  border-top: 1px solid #eee;
}

.btn {
  padding: 12px 24px;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
  min-width: 120px;
}

.btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.btn-primary {
  background: #007bff;
  color: white;
}

.btn-primary:hover:not(:disabled) {
  background: #0056b3;
}

.btn-secondary {
  background: #6c757d;
  color: white;
}

.btn-secondary:hover:not(:disabled) {
  background: #545b62;
}
</style>
