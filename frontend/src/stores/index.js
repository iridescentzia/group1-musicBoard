// src/stores/index.js
import { createPinia } from 'pinia'

export default createPinia()

// 각 스토어 export
export { useReviewStore } from './reviewStore.js'