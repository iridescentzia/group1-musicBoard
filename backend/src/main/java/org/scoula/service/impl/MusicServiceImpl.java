package org.scoula.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.domain.MusicVO;
import org.scoula.dto.MusicDTO;
import org.scoula.mapper.MusicMapper;
import org.scoula.service.MusicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {
    private final MusicMapper mapper;

    @Override
    public List<MusicDTO> getList() {
        log.info("====================== getList ======================");
        List<MusicVO> voList = mapper.getList();
        return voList.stream()
                .map(MusicDTO::of)  // VO → DTO 변환
                .collect(Collectors.toList());
    }

    @Override
    public MusicDTO get(Long id) {
        log.info("====================== get ======================" + id);
        MusicVO music = mapper.get(id);
        MusicVO result = Optional.ofNullable(music)
                .orElseThrow(NoSuchElementException::new);
        return MusicDTO.of(result);  // VO → DTO 변환
    }

    @Override
    public void create(MusicDTO musicDTO) {
        log.info("====================== create ======================" + musicDTO);
        MusicVO musicVO = musicDTO.toVO();  // DTO → VO 변환
        mapper.create(musicVO);
    }

    @Override
    public boolean update(MusicDTO musicDTO) {
        log.info("====================== update ====================" + musicDTO);
        MusicVO musicVO = musicDTO.toVO();  // DTO → VO 변환
        return mapper.update(musicVO) == 1;
    }

    @Override
    public boolean delete(Long id) {
        log.info("====================== delete ====================" + id);
        return mapper.delete(id) == 1;
    }
}