<script setup>
import {ref, onMounted} from 'vue'
import {useRoute, useRouter} from 'vue-router'
import { useUserStore } from '@/stores/userStore'

const route = useRoute()
const router = useRouter()
const userStore = useUserStore()

//초기 from형태 - userId와 UserName을 빈값으로 설정
const form = ref({
    userId: '',
    userName: ''
})
// async: 비동기 함수 선언 (기다릴 수 있는 함수)
// await: 결과가 올 때까지 기다림
// await이 없으면 form.value에 아직 도착하지 않은 데이터를 넣으려 해서 에러남.
onMounted(async ()=>{
    const id = Number(route.params.id)
    try{
        const res = await userStore.getUser(id)
        form.value = res
    } catch (err) {
        console.error('유저 정보 조회 실패: ', err)
        alert('유저 정보를 불러올 수 없습니다.')
    }
})

const updateUser = async ()=>{
    try{
        await userStore.updateUser(form.value.userId, form.value)
        alert('수정 완료')
        router.push('/users')
    }catch (err){
        console.error('수정 실패: ',err)
    }
}
</script>

<template>
<div class="container mt-4">
    <h2>사용자 정보 수정</h2>

    <!-- @submit 폼이 제출될 때 실행되는 이벤트
    .prevent 기본 동작(새로고침)을 막고 js 함수로 처리
    updateUser 위에서 만든 비동기 함수 호출 수정 완료 버튼 누르면 함수 실행
    (서버에 put요청 보냄) -->
    <form @submit.prevent="updateUser">
        <div class="mb-3">
            <label for="userName" class="form-label">이름</label>
            <input 
                id="userName"
                v-model="form.userName"
                class="form-control"
                required 
            />
        </div>

        <!-- 여기서 submit이벤트 발생하면 위에 설정한 대로 updateUser함수 호출 -->
        <button type="submit" class="btn btn-success">수정 완료</button>
    </form>
</div>
</template>

<style scoped>

</style>