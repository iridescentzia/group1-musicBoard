import {defineStore} from 'pinia'
import {ref} from 'vue'
import userService from '@/services/userService'

export const useUserStore = defineStore('user', ()=>{
    const users = ref([])

    const fetchUsers = async () => {
        try{
            const res = await userService.getAllUsers()
            console.log('서버 응답 데이터', res.data)
            users.value = res.data
        } catch (err) {
            console.error('유저 가져오기 실패', err)
        }
    } 
    
    const getUser = async (id) => {
        try{
            const res = await userService.getUser(id)
            return res.data
        } catch (err) {
            console.error('유저 조회 실패', err)
            throw err
        }
    }

    const createUser = async (userData) => {
        try{
            await userService.createUser(userData)
        } catch (err) {
            console.error('유저 생성 실패', err)
            throw err
        }
    }

    const updateUser = async (id, userData) => {
        try{
            await userService.updateUser(id, userData)
        } catch (err) {
            console.error('유저 수정 실패', err)
            throw err
        }
    }

    const deleteUser = async (id) => {
        await userService.deleteUser(id)
    }
    

    return{
        users,
        fetchUsers,
        getUser,
        createUser,
        updateUser,
        deleteUser
    }
})