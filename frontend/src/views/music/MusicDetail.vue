<script setup>
import { onMounted, computed } from "vue";
import { useRoute } from "vue-router";
import { useMusicStore } from "@/stores/musicStore";

const route = useRoute();
const musicStore = useMusicStore();
const music = computed(() => musicStore.selectedMusic);

onMounted(() => {
  musicStore.fetchMusicById(route.params.id);
});

const formatDate = (date) => new Date(date).toLocaleDateString("ko-KR");
</script>

<template>
  <div class="detail container" v-if="music">
    <img :src="music.coverUrl" class="cover" />
    <div class="info">
      <h2>{{ music.title }}</h2>
      <p><strong>🎤 아티스트:</strong> {{ music.artist }}</p>
      <p><strong>🎵 장르:</strong> {{ music.genre }}</p>
      <p><strong>📅 발매일:</strong> {{ formatDate(music.releaseDate) }}</p>
    </div>
  </div>
</template>


<style scoped>
.container {
  display: flex;
  padding: 2rem;
}
.cover {
  width: 300px;
  border-radius: 10px;
  margin-right: 2rem;
}
.info {
  font-size: 1.1rem;
}
</style>