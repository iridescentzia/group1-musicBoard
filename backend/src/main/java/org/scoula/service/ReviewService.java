package org.scoula.service;

import org.scoula.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    // 전체 리뷰 목록 조회
    List<ReviewDTO> getList();

    // 특정 리뷰 상세 조회
    ReviewDTO get(Long id);

    // 리뷰 작성
    void create(ReviewDTO review);

    // 리뷰 수정
    boolean update(ReviewDTO review);

    // 리뷰 삭제
    boolean delete(Long id);

    // 특정 음악의 리뷰 목록 조회
    List<ReviewDTO> getListByMusicId(Long musicId);
}
