import { defineStore } from "pinia";
import { getMusicList, getMusicById } from "@/services/musicService";

export const useMusicStore = defineStore("musicStore", {
    state: () => ({
        musicList: [],
        selectedMusic: null,
    }),
    actions: {
        async fetchMusicList() {
            const res = await getMusicList();
            this.musicList = res.data;
        },
        async fetchMusicById(id) {
            const res = await getMusicById(id);
            this.selectedMusic = res.data;
        },
    },
});