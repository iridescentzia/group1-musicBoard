package org.scoula.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.domain.ReviewVO;
import org.scoula.dto.ReviewDTO;
import org.scoula.mapper.ReviewMapper;
import org.scoula.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper mapper;

    // 전체 리뷰 목록 조회
    @Override
    public List<ReviewDTO> getList() {
        log.info("getList...✅");
        return mapper.getList().stream()
                .map(ReviewDTO::of)
                .toList();
    }

    // 특정 리뷰 조회
    @Override
    public ReviewDTO get(Long id) {
        log.info("get...✅" + id);
        ReviewDTO review = ReviewDTO.of(mapper.get(id));
        return Optional.ofNullable(review)
                .orElseThrow(NoSuchElementException::new);
    }

    // 리뷰 작성
    @Override
    public void create(ReviewDTO review) {
        log.info("create...✅" + review);
        mapper.create(review.toVO());
    }

    // 리뷰 수정
    @Override
    public boolean update(ReviewDTO review) {
        log.info("update...✅" + review);
        return mapper.update(review.toVO()) == 1;
    }

    // 리뷰 삭제
    @Override
    public boolean delete(Long id) {
        log.info("delete...✅" + id);
        return mapper.delete(id) == 1;
    }

    // 특정 음악에 대한 리뷰 목록
    @Override
    public List<ReviewDTO> getListByMusicId(Long musicId) {
        log.info("getListByMusicId...✅" + musicId);
        return mapper.getListByMusicId(musicId).stream()
                .map(ReviewDTO::of)
                .toList();
    }
}
