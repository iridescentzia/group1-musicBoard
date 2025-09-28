<script setup>
import { ref, computed } from 'vue'
import { useCommentStore } from '@/stores/commentStore.js'
import { useRouter } from 'vue-router'
import moment from 'moment'

const router = useRouter()

//props: 조회 기준(type), 검색어(keyword)
const props = defineProps({
  type: { type: String, default: 'user' },
  keyword: { type: String, default: '' },
})

const commentStore = useCommentStore()
const error = ref(null)
const loading = ref(false)

//검색어, 조회 기준 상태
const searchKeyword = ref(props.keyword)
const searchType = ref(props.type)

//댓글 목록 계산 (조회 기준에 따라 다르게 반환)
const comments = computed(() => {
  if (searchType.value === 'review') return commentStore.commentsByReview
  else if (searchType.value === 'user') return commentStore.commentsByUser
  else if (searchType.value === 'single') return commentStore.singleComment ? [commentStore.singleComment] : []
  return []
})

//댓글 불러오기 (검색어 없으면 전체, 있으면 필터 조회)
const loadComments = async () => {
  error.value = null
  loading.value = true
  try {
    if (!searchKeyword.value.trim()) {
      if (searchType.value === 'user') await commentStore.fetchCommentsByUserId(null)
      else if (searchType.value === 'review') await commentStore.fetchCommentsByReviewId(null)
      else if (searchType.value === 'single') commentStore.singleComment = null
    } else {
      if (searchType.value === 'user') await commentStore.fetchCommentsByUserId(searchKeyword.value)
      else if (searchType.value === 'review') await commentStore.fetchCommentsByReviewId(searchKeyword.value)
      else if (searchType.value === 'single') await commentStore.fetchComment(searchKeyword.value)
    }
  } catch (e) {
    error.value = commentStore.error || '댓글을 불러오는 데 실패했습니다.'
  } finally {
    loading.value = false
  }
}

//댓글 수정 모드 시작
const editingCommentId = ref(null)
const editingContent = ref('')

const startEdit = (comment) => {
  editingCommentId.value = comment.id
  editingContent.value = comment.content
}

//댓글 수정 저장
const saveEdit = async () => {
  if (!editingContent.value.trim()) {
    alert('댓글 내용을 입력하세요.')
    return
  }
  try {
    await commentStore.updateComment(
        editingCommentId.value,
        { content: editingContent.value },
        searchType.value === 'review' ? searchKeyword.value : null,
        searchType.value === 'user' ? searchKeyword.value : null
    )
    editingCommentId.value = null
    editingContent.value = ''
    await loadComments()
  } catch (e) {
    alert(commentStore.error || '댓글 수정에 실패했습니다.')
  }
}

//댓글 수정 취소
const cancelEdit = () => {
  editingCommentId.value = null
  editingContent.value = ''
}

//댓글 삭제
const deleteComment = async (id) => {
  if (!confirm('댓글을 삭제하시겠습니까?')) return
  try {
    await commentStore.deleteComment(
        id,
        searchType.value === 'review' ? searchKeyword.value : null,
        searchType.value === 'user' ? searchKeyword.value : null
    )
    await loadComments()
  } catch (e) {
    alert(commentStore.error || '댓글 삭제에 실패했습니다.')
  }
}

//댓글 작성 페이지로 이동
const goToCreateComment = () => {
  router.push({ name: 'CommentCreate' })
}

//날짜 포맷
const formatDate = (date) => date ? moment(date).format('YYYY.MM.DD HH:mm') : ''
</script>

<template>
  <div class="comment-list-container">
    <!-- 검색어 입력, 조회 기준 선택, 조회 버튼 -->
    <div class="search-bar">
      <input
          v-model="searchKeyword"
          placeholder="검색어를 입력하세요"
          @keyup.enter="loadComments"
      />
      <select v-model="searchType">
        <option value="user">사용자</option>
        <option value="review">리뷰</option>
        <option value="single">단일 댓글</option>
      </select>
      <button @click="loadComments" aria-label="검색">🔍</button>

      <!-- 댓글 작성 버튼 -->
      <button class="btn btn-primary create-btn" @click="goToCreateComment">
        댓글 작성
      </button>
    </div>

    <!-- 댓글 리스트 영역 -->
    <div class="comment-list">
      <div v-if="loading" class="loading">로딩 중...</div>
      <div v-else-if="error" class="error-message">❌ {{ error }}</div>
      <div v-else-if="comments.length === 0" class="empty-state">
        <p>댓글이 없습니다.</p>
      </div>
      <div v-else>
        <div v-for="comment in comments" :key="comment.id" class="comment-item">
          <div class="comment-meta">
            <span>👤 User {{ comment.userId }}</span>
            <span>{{ formatDate(comment.createdAt) }}</span>
          </div>

          <!-- 수정 모드 -->
          <div v-if="editingCommentId === comment.id" class="comment-edit-form">
            <textarea v-model="editingContent" rows="3"></textarea>
            <button class="btn btn-primary" @click="saveEdit">저장</button>
            <button class="btn btn-secondary" @click="cancelEdit">취소</button>
          </div>

          <!-- 일반 댓글 표시 -->
          <div v-else class="comment-content">
            <p>{{ comment.content }}</p>
            <div class="comment-actions">
              <button class="btn btn-secondary" @click.stop="startEdit(comment)">수정</button>
              <button class="btn btn-danger" @click.stop="deleteComment(comment.id)">삭제</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.comment-list-container {
  margin-top: 20px;
}

.search-bar {
  display: flex;
  gap: 10px;
  margin-bottom: 15px;
  align-items: center;
}

.search-bar input {
  flex-grow: 1;
  padding: 6px 10px;
  font-size: 14px;
  border-radius: 6px;
  border: 1px solid #ccc;
}

.search-bar select {
  width: 140px;
  padding: 6px 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  background-color: white;
}

.search-bar button {
  padding: 6px 12px;
  border-radius: 6px;
  border: none;
  cursor: pointer;
  background-color: #007bff;
  color: white;
  font-size: 16px;
}

/* 댓글 작성 버튼 스타일 */
.create-btn {
  background-color: #28a745;
  margin-left: 10px;
}

.create-btn:hover {
  background-color: #218838;
}

.comment-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.comment-item {
  border-bottom: 1px solid #eee;
  padding-bottom: 15px;
}

.comment-meta {
  display: flex;
  justify-content: space-between;
  font-size: 13px;
  color: #666;
  margin-bottom: 8px;
}

.comment-content p {
  margin: 0 0 10px 0;
  font-size: 14px;
  color: #333;
  word-break: break-word;
}

.comment-actions {
  display: flex;
  gap: 10px;
}

.comment-edit-form textarea {
  width: 100%;
  padding: 8px;
  font-size: 14px;
  border-radius: 5px;
  border: 1px solid #ccc;
  margin-bottom: 8px;
  resize: vertical;
}

.btn {
  display: inline-block;
  padding: 6px 14px;
  border: none;
  border-radius: 5px;
  font-size: 13px;
  cursor: pointer;
  transition: background 0.2s ease;
}

.btn-primary {
  background-color: #007bff;
  color: white;
}

.btn-primary:hover {
  background-color: #0056b3;
}

.btn-secondary {
  background-color: #6c757d;
  color: white;
}

.btn-secondary:hover {
  background-color: #5a6268;
}

.btn-danger {
  background-color: #dc3545;
  color: white;
}

.btn-danger:hover {
  background-color: #c82333;
}

.loading {
  color: #007bff;
  font-weight: bold;
  text-align: center;
}

.error-message {
  color: #dc3545;
  background-color: #fee;
  border: 1px solid #fcc;
  padding: 10px;
  border-radius: 5px;
  text-align: center;
}

.empty-state {
  text-align: center;
  color: #888;
  padding: 10px;
}
</style>
