package org.scoula.mapper;

import org.scoula.domain.ReviewVO;

import java.util.List;

public interface ReviewMapper {

    // 전체 리뷰 목록
    List<ReviewVO> getList();

    // 특정 리뷰 조회
    ReviewVO get(Long id);

    // 리뷰 작성
    void create(ReviewVO review);

    // 리뷰 수정
    int update(ReviewVO review);

    // 리뷰 삭제
    int delete(Long id);

    // 특정 음악의 리뷰 목록 조회
    List<ReviewVO> getListByMusicId(Long musicId);
}
