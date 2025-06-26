package org.scoula.service;

import org.scoula.domain.MusicVO;
import org.scoula.dto.MusicDTO;

import java.util.List;

public interface MusicService {
    public List<MusicDTO> getList();
    public MusicDTO get(Long id);
    public void create(MusicDTO music);
    public boolean update(MusicDTO music);
    public boolean delete(Long id);
}
