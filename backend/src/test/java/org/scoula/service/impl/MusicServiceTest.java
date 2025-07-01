/*
package org.scoula.service.impl;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.domain.MusicVO;
import org.scoula.dto.MusicDTO;
import org.scoula.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MusicServiceImpl.class})
@Log4j2
class MusicServiceTest {
    @Autowired
    private MusicService service;
    private static MusicVO testMusic;

    @BeforeAll
    static void initMusic() {
        testMusic = MusicVO.builder()
                .title("서비스 테스트 노래")
                .artist("서비스 테스트 가수")
                .genre("서비스 테스트 장르")
                .releaseDate(new Date())
                .coverUrl("https://example.cover.url")
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("======================= create =======================")
    void create() {
        service.create(testMusic);
        assertTrue(testMusic.getId() > 0);
        log.info("생성된 노래 ID: {}", testMusic.getId());
    }

    @Test
    @Order(2)
    @DisplayName("======================= getList =======================")
    void getList() {
        var list = service.getList();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        log.info("전체 노래 수: {}", list.size());
    }

    @Test
    @Order(3)
    @DisplayName("======================= get =======================")
    void get() {
        MusicVO music = service.get(testMusic.getId());
        assertNotNull(music);
        assertEquals(testMusic.getTitle(), music.getTitle());
        log.info("조회된 노래: {}", music);
    }

    @Test
    @Order(4)
    @DisplayName("======================= update =======================")
    void update() {
        testMusic.setTitle("수정된 제목");
        boolean updated = service.update(testMusic);
        assertTrue(updated);
        MusicVO updatedMusic = service.get(testMusic.getId());
        assertEquals("수정된 제목", updatedMusic.getTitle());
        log.info("수정된 노래 정보: {}", updatedMusic);
    }

    @Test
    @Order(5)
    @DisplayName("======================= delete =======================")
    void delete() {
        boolean deleted = service.delete(testMusic.getId());
        assertTrue(deleted);
        MusicVO deletedMusic = service.get(testMusic.getId());
        assertNull(deletedMusic);
        log.info("삭제된 ID: {}", testMusic.getId());
    }
}*/
