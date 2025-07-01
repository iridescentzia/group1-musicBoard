<script setup>
import Header from './Header.vue'
import Navbar from './Navbar.vue'
import Footer from './Footer.vue'
</script>

<template>
  <div class="layout-wrapper">
    <!-- 상단 헤더 -->
    <Header />

    <!-- 네비게이션 -->
    <Navbar />

    <!-- 메인 콘텐츠 영역 -->
    <main class="main-content">
      <div class="container">
        <slot />
      </div>
    </main>

    <!-- 하단 푸터 -->
    <Footer />
  </div>
</template>

<style scoped>
.layout-wrapper {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #f8f9fa;
}

.main-content {
  flex: 1;
  padding: 2rem 0;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 1rem;
}

@media (max-width: 768px) {
  .main-content {
    padding: 1rem 0;
  }

  .container {
    padding: 0 0.5rem;
  }
}
</style>