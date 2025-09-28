<script setup>
import { ref, onMounted, computed } from 'vue'
import { useUserStore } from '@/stores/userStore'
import { storeToRefs } from 'pinia'

const userStore = useUserStore()
const { users } = storeToRefs(userStore)

const selectedUserId = ref('')

const selectedUser = computed(() =>
  users.value.find(user => user.userId === Number(selectedUserId.value))
)

onMounted(() => {
  userStore.fetchUsers()
})

const handleSelect = () => {
  console.log('선택된 유저 ID:', selectedUserId.value)
}

const deleteUser = async (id) =>{
  if(confirm('정말 삭제하시겠어요?')){
    try{
      await userStore.deleteUser(id)
      alert('삭제 완료')
      selectedUserId.value = ''
      userStore.fetchUsers()
    } catch (err) {
      console.error('삭제 실패: ', err)
      alert('삭제 중 오류 발생')
    }
  }
}
</script>

<template>
<div class="container mt-4">
    <h2>사용자 목록</h2>
    
    <select v-model="selectedUserId" @change="handleSelect" 
      class="form-select mb-4">
      <option disabled value="">--- 사용자 선택 ---</option>
      <option v-for="user in users" :key="user.userId" :value="user.userId">
        {{ user.userName }}
      </option>
    </select>

    <div v-if="selectedUser">
      <h4>선택된 사용자 정보</h4>
      <ul>
        <li>ID: {{ selectedUser.userId }}</li>
        <li>이름: {{ selectedUser.userName }}</li>
      </ul>

      <div class="d-flex gap-2 mt-2"></div>
      <button @click="deleteUser(selectedUser.userId)" class="btn btn-danger">
        사용자 삭제
      </button>
      <br>
      <router-link :to="`/users/edit/${selectedUser.userId}`" class="btn btn-primary">
        사용자 수정
      </router-link>
      <br>
      <router-link :to="`/users/${selectedUser.userId}/reviews`" class="btn btn-outline-secondary">
        사용자의 리뷰
      </router-link>
    </div>
    <br>
    <router-link to="/users/create" class="btn btn-success mb-3">
      사용자 등록
    </router-link>
  </div>
</template>

<style scoped>
h2 {
  margin-bottom: 1rem;
}
li {
  padding: 0.5rem 0;
  border-bottom: 1px solid #ccc;
}
</style>