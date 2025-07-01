package org.scoula.mapper;

import org.apache.ibatis.annotations.Select;
import org.scoula.domain.MusicVO;
import org.scoula.dto.MusicDTO;

import java.util.List;

public interface MusicMapper {
    public List<MusicVO> getList();  // 전체 목록 조회
    public MusicVO get(Long id);  // 개별 목록 조회
    public void create(MusicVO music);  // 등록
    public int update(MusicVO music);  // 수정
    public int delete(Long id);  // 삭제
}
