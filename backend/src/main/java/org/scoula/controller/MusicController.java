package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.domain.MusicVO;
import org.scoula.dto.MusicDTO;
import org.scoula.service.MusicService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MusicController {
    final MusicService service;

    // 음악 목록 조회
    @GetMapping("/music")
    public ResponseEntity<List<MusicDTO>> getList() {
        log.info("GET /api/music");
        return ResponseEntity.ok(service.getList());
    }

    // 특정 음악 상세 (리뷰 포함)
    @GetMapping("/music/{id}")
    public ResponseEntity<MusicDTO> get(@PathVariable Long id) {
        log.info("GET /api/music/{}", id);
        return ResponseEntity.ok(service.get(id));
    }

    // 음악 등록
    @PostMapping("/music")
    public ResponseEntity<Void> create(@RequestBody MusicDTO music) {
        log.info("POST /api/music -> {}", music);
        service.create(music);
        return ResponseEntity.ok().build();
    }

    // 음악 수정
    @PutMapping("/music/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody MusicDTO music) {
        music.setId(id);
        log.info("PUT /api/music/{} -> {}", id, music);
        return service.update(music) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // 음악 삭제
    @DeleteMapping("/music/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /api/music/{}", id);
        return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}