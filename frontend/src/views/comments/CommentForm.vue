<script setup>
import { ref } from 'vue'
import { useCommentStore } from '@/stores/commentStore.js'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()

const commentStore = useCommentStore()

//폼 데이터 상태 관리
const reviewId = ref(route.params.reviewId || '')
const userId = ref('')    //작성자 ID 입력값
const content = ref('')   //댓글 내용

//에러 및 로딩 상태 관리
const error = ref(null)
const loading = ref(false)

//댓글 등록 함수
const submitComment = async () => {
  //유효성 검사
  if (!userId.value.trim()) {
    alert('작성자 ID를 입력하세요.')
    return
  }
  if (!content.value.trim()) {
    alert('댓글 내용을 입력하세요.')
    return
  }

  loading.value = true
  error.value = null

  try {
    //댓글 생성 요청 (userName은 빈 문자열로 보냄)
    await commentStore.createComment('review', reviewId.value, {
      userId: userId.value,
      content: content.value
    })
    alert('댓글이 등록되었습니다.')
    //등록 후 댓글 목록 페이지로 이동 (리뷰 기준 조회)
    router.push({ name: 'CommentList', query: { type: 'review', keyword: reviewId.value } })
  } catch (e) {
    error.value = commentStore.error || '댓글 등록 중 오류가 발생했습니다.'
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="comment-form-container">
    <h2>댓글 작성</h2>

    <!-- 작성자 ID 입력 -->
    <div class="field-group">
      <label>작성자 ID</label>
      <input v-model="userId" placeholder="작성자 ID를 입력하세요" />
    </div>

    <!-- 리뷰 ID 입력 -->
    <div class="field-group">
      <label>리뷰 ID</label>
      <input v-model="reviewId" placeholder="리뷰 ID 입력 또는 선택" />
    </div>

    <!-- 댓글 내용 입력 -->
    <div class="field-group">
      <label>댓글 내용</label>
      <textarea v-model="content" rows="5" placeholder="댓글 내용을 입력하세요"></textarea>
    </div>

    <!-- 등록 및 취소 버튼 -->
    <div class="button-group">
      <button :disabled="loading" @click="submitComment" class="btn btn-primary">등록</button>
      <button @click="$router.back()" class="btn btn-secondary">취소</button>
    </div>

    <!-- 에러 메시지 출력 -->
    <p v-if="error" class="error-message">{{ error }}</p>
  </div>
</template>

<style scoped>
.comment-form-container {
  max-width: 480px;
  margin: 40px auto;
  padding: 20px 25px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #fafafa;
  box-shadow: 0 2px 6px rgb(0 0 0 / 0.1);
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

h2 {
  margin-bottom: 20px;
  color: #333;
  text-align: center;
}

.field-group {
  margin-bottom: 16px;
  display: flex;
  flex-direction: column;
}

label {
  font-weight: 600;
  margin-bottom: 6px;
  color: #555;
}

input,
textarea {
  padding: 10px 12px;
  font-size: 15px;
  border: 1px solid #ccc;
  border-radius: 6px;
  resize: vertical;
  font-family: inherit;
  color: #333;
}

input:focus,
textarea:focus {
  border-color: #007bff;
  outline: none;
  box-shadow: 0 0 6px #a7d0ff;
}

.button-group {
  display: flex;
  justify-content: center;
  gap: 15px;
  margin-top: 25px;
}

.btn {
  padding: 10px 24px;
  font-size: 16px;
  border-radius: 6px;
  cursor: pointer;
  border: none;
  font-weight: 600;
  transition: background-color 0.25s ease;
}

.btn-primary {
  background-color: #007bff;
  color: white;
}

.btn-primary:disabled {
  background-color: #a2cdfd;
  cursor: not-allowed;
}

.btn-primary:hover:not(:disabled) {
  background-color: #0056b3;
}

.btn-secondary {
  background-color: #6c757d;
  color: white;
}

.btn-secondary:hover {
  background-color: #5a6268;
}

.error-message {
  margin-top: 15px;
  text-align: center;
  color: #dc3545;
  font-weight: 600;
}
</style>
