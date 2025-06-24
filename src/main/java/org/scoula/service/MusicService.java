package org.scoula.service;

import org.scoula.domain.MusicVO;
import org.scoula.dto.MusicDTO;

import java.util.List;

public interface MusicService {
    public List<MusicVO> getList();
    public MusicVO get(int id);
    public void create(MusicVO music);
    public boolean update(MusicVO music);
    public boolean delete(int id);
}
