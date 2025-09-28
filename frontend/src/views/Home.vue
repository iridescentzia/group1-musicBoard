<!-- src/views/Home.vue -->
<template>
  <div class="home">
    <h1>🎵 음악 리뷰 게시판에 오신 것을 환영합니다!</h1>
    <p>다양한 음악에 대한 솔직한 리뷰를 확인해보세요.</p>
    <router-link to="/reviews" class="btn">리뷰 보러가기</router-link>
  </div>
</template>

<style scoped>
.home {
  text-align: center;
  padding: 60px 20px;
}
.btn {
  padding: 10px 20px;
  background: #007bff;
  color: white;
  text-decoration: none;
  border-radius: 5px;
}
</style>