import axios from "@/services";

const MUSIC_API = "/music";

export const getMusicList = () => axios.get(MUSIC_API);
export const getMusicById = (id) => axios.get(`${MUSIC_API}/${id}`);