<script setup>
import {ref} from 'vue'
import {useRouter} from 'vue-router'
import { useUserStore } from '@/stores/userStore'

const router = useRouter()
const userStore = useUserStore()

const form = ref({
    userName: ''
})

const createUser = async () => {
    try{
        await userStore.createUser(form.value)
        alert('사용자 등록 완료')
        router.push('/users')
    } catch (err){
        console.error('사용자 등록 실패: ', err)
        alert('등록 중 오류가 발생했습니다.')
    }
}
</script>

<template>
    <div class="container mt-4">
        <h2>새 사용자 등록</h2>

        <form @submit.prevent="createUser">
            <div class="mb-3">
                <label for="userName" class="form-label">이름</label>
                <input 
                    id="userName"
                    v-model="form.userName"
                    class="form-control"
                    required
                />
            </div>

            <button type="submit" class="btn btn-primary">등록</button>
        </form>
    </div>
</template>

<style scoped>

</style>