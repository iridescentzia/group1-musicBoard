package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.dto.ReviewDTO;
import org.scoula.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService service;

    // 전체 리뷰 목록 조회
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDTO>> getList() {
        log.info("GET /api/reviews ✅");
        return ResponseEntity.ok(service.getList());
    }

    // 특정 리뷰 목록 조회
    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewDTO> get(@PathVariable Long id) {
        log.info("GET /api/reviews/{} ✅", id);
        return ResponseEntity.ok(service.get(id));
    }

    // 리뷰 작성
    @PostMapping("/reviews")
    public ResponseEntity<Void> create(@RequestBody ReviewDTO review) {
        log.info("POST /api/reviews -> {} ✅", review);
        service.create(review);
        return ResponseEntity.ok().build();
    }

    // 리뷰 수정
    @PutMapping("/reviews/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ReviewDTO review) {
        review.setId(id);
        log.info("PUT /api/reviews/{} -> {} ✅", id, review);
        return service.update(review) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // 리뷰 삭제
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /api/reviews/{} ✅", id);
        return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // 특정 음악의 리뷰 목록 조회
    @GetMapping("/music/{musicId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getListByMusicId(@PathVariable Long musicId) {
        log.info("GET /api/music/{}/reviews", musicId);
        return ResponseEntity.ok(service.getListByMusicId(musicId));
    }

}
