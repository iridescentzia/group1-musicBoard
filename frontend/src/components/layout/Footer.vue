<script setup>
import { computed } from 'vue'

const currentYear = computed(() => new Date().getFullYear())
</script>

<template>
  <footer class="footer">
    <div class="footer-container">
      <div class="footer-content">
        <div class="footer-section">
          <h3>🎵 Music Review</h3>
          <p>음악에 대한 진솔한 후기를 나누는 공간</p>
        </div>

        <div class="footer-section">
          <h4>바로가기</h4>
          <ul class="footer-links">
            <li><router-link to="/reviews">후기 게시판</router-link></li>
            <li><router-link to="/music">음악 탐색</router-link></li>
            <li><router-link to="/users">마이페이지</router-link></li>
          </ul>
        </div>

        <div class="footer-section">
          <h4>고객지원</h4>
          <ul class="footer-links">
            <li><a href="#">공지사항</a></li>
            <li><a href="#">문의하기</a></li>
            <li><a href="#">이용약관</a></li>
          </ul>
        </div>
      </div>

      <div class="footer-bottom">
        <p>&copy; {{ currentYear }} Music Review. All rights reserved.</p>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background: #333;
  color: #fff;
  margin-top: auto;
}

.footer-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem 1rem 1rem;
}

.footer-content {
  display: grid;
  grid-template-columns: 2fr 1fr 1fr;
  gap: 2rem;
  margin-bottom: 2rem;
}

.footer-section h3 {
  margin-bottom: 1rem;
  color: #007bff;
}

.footer-section h4 {
  margin-bottom: 1rem;
  font-size: 1rem;
}

.footer-section p {
  color: #ccc;
  line-height: 1.6;
}

.footer-links {
  list-style: none;
  padding: 0;
  margin: 0;
}

.footer-links li {
  margin-bottom: 0.5rem;
}

.footer-links a {
  color: #ccc;
  text-decoration: none;
  transition: color 0.3s ease;
}

.footer-links a:hover {
  color: #007bff;
}

.footer-bottom {
  text-align: center;
  padding-top: 1rem;
  border-top: 1px solid #555;
  color: #ccc;
  font-size: 0.9rem;
}

@media (max-width: 768px) {
  .footer-content {
    grid-template-columns: 1fr;
    gap: 1.5rem;
  }

  .footer-container {
    padding: 1.5rem 1rem 1rem;
  }
}
</style>