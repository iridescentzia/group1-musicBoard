import api from './index.js'

export default{
    getAllUsers(){
        return api.get(`/users`)
    },
    getUser(id){
        return api.get(`/users/${id}`)
    },
    createUser(userData){
        return api.post(`/users`, userData)
    },
    updateUser(id, userData){
        return api.put(`/users/${id}`, userData)
    },
    deleteUser(id){
        return api.delete(`/users/${id}`)
    }
}