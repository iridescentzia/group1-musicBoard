<script setup>
</script>
<template>
  <nav class="navbar">
    <div class="nav-container">
      <ul class="nav-menu">
        <li class="nav-item">
          <router-link to="/" class="nav-link">홈</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/reviews" class="nav-link">후기</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/music" class="nav-link">음악</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/comments" class="nav-link">댓글</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/users" class="nav-link">마이페이지</router-link>
        </li>
      </ul>
    </div>
  </nav>
</template>

<style scoped>
.navbar {
  background: #fff;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  position: sticky;
  top: 0;
  z-index: 100;
}

.nav-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 1rem;
}

.nav-menu {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
  justify-content: center;
}

.nav-item {
  margin: 0 1rem;
}

.nav-link {
  display: block;
  padding: 1rem 0.5rem;
  text-decoration: none;
  color: #333;
  font-weight: 500;
  border-bottom: 2px solid transparent;
  transition: all 0.3s ease;
}

.nav-link:hover {
  color: #007bff;
  border-bottom-color: #007bff;
}

.nav-link.router-link-active {
  color: #007bff;
  border-bottom-color: #007bff;
}

@media (max-width: 768px) {
  .nav-menu {
    flex-wrap: wrap;
    justify-content: center;
  }

  .nav-item {
    margin: 0.5rem;
  }

  .nav-link {
    padding: 0.5rem;
    font-size: 0.9rem;
  }
}
</style>