<script setup>
import { onMounted } from "vue";
import { useMusicStore } from "@/stores/musicStore";
const musicStore = useMusicStore();

onMounted(() => {
  musicStore.fetchMusicList().then(() => {
    console.log("음악 목록:", musicStore.musicList);
  });
});
</script>

<template>
  <div class="music-list container">
    <h2>💿 음악 목록</h2>
    <div class="grid">
      <div v-for="music in musicStore.musicList" :key="music.id" class="card">
        <img :src="music.coverUrl" alt="cover" class="cover" />
        <h3>{{ music.title }}</h3>
        <p>{{ music.artist }}</p>
        <span class="genre">{{ music.genre }}</span>
        <router-link :to="`/music/${music.id}`" class="detail-btn"> 자세히 보기</router-link>
      </div>
    </div>
  </div>
</template>


<style scoped>
.container {
  padding: 2rem;
}
.grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
  gap: 20px;
}
.card {
  border: 1px solid #ddd;
  padding: 1rem;
  border-radius: 8px;
  text-align: center;
  transition: 0.3s;
}
.card:hover {
  transform: scale(1.02);
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}
.cover {
  width: 100%;
  border-radius: 6px;
  margin-bottom: 0.5rem;
}
.genre {
  font-size: 0.9rem;
  color: #888;
}
.detail-btn {
  display: inline-block;
  margin-top: 0.5rem;
  color: #007bff;
}
</style>