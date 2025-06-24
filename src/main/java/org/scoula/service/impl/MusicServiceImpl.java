package org.scoula.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.domain.MusicVO;
import org.scoula.mapper.MusicMapper;
import org.scoula.service.MusicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {
    final private MusicMapper mapper;

    @Override
    public List<MusicVO> getList() {
        log.info("====================== getList ======================");
        return mapper.getList();
    }

    @Override
    public MusicVO get(int id) {
        log.info("====================== get ======================" + id);
        MusicVO music = mapper.get(id);
        return Optional.ofNullable(music)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void create(MusicVO music) {
        log.info("====================== create ======================" + music);
        mapper.create(music);
    }

    @Override
    public boolean update(MusicVO music) {
        log.info("====================== update ====================" + music);
        return mapper.update(music) == 1;
    }

    @Override
    public boolean delete(int id) {
        log.info("====================== delete ====================" + id);
        return mapper.delete(id) == 1;
    }
}
