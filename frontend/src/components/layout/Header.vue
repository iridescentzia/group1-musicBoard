<!-- 음악 후기 게시판용 간단 Header -->
<script setup>
// import config from "@/config/index.js";
</script>

<template>
  <div class="header-hero">
    <!-- <h1>{{ config.title }}</h1>
    <p>{{ config.subtitle }}</p> -->
  </div>
</template>

<style scoped>
.header-hero {
  background-image: url('@/assets/images/music-bg.jpg');
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  color: white;
  padding: 3rem 2rem;
  text-align: center;
  box-shadow: 0 2px 10px rgba(0,0,0,0.3);
}

.header-hero h1 {
  font-size: 2.5rem;
  margin-bottom: 1rem;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
}

.header-hero p {
  font-size: 1.2rem;
  text-shadow: 1px 1px 2px rgba(0,0,0,0.5);
}
</style>