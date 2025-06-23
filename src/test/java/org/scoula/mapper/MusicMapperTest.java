package org.scoula.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.domain.MusicVO;
import org.scoula.dto.MusicDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
public class MusicMapperTest {
    @Autowired
    private MusicMapper mapper;
    private static MusicVO testMusic;

    @BeforeAll
    static void setup() {
        testMusic = MusicVO.builder()
                .title("JUnit 테스트 노래")
                .artist("테스트 가수")
                .genre("테스트 장르")
                .releaseDate(new Date())
                .coverUrl("https://test.cover.url")
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("새 음악 생성")
    public void testCreate() {
        mapper.create(testMusic);
        assertTrue(testMusic.getId() > 0);
        log.info("생성된 music ID: {}", testMusic.getId());
    }

    @Test
    @Order(2)
    @DisplayName("전체 목록 가져오기")
    public void testGetList() {
        var list = mapper.getList();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        log.info("Music 목록 수: {}", list.size());
    }

    @Test
    @Order(3)
    @DisplayName("ID로 음악 조회")
    public void testGetById() {
        MusicVO music = mapper.get(testMusic.getId());
        assertNotNull(music);
        assertEquals(testMusic.getTitle(), music.getTitle());
        log.info("조회된 music: {}", music);
    }

    @Test
    @Order(4)
    @DisplayName("음악 정보 수정")
    public void testUpdate() {
        testMusic.setTitle("수정된 제목");
        testMusic.setArtist("수정된 가수");

        int updated = mapper.update(testMusic);
        assertEquals(1, updated);
        MusicVO updatedMusic = mapper.get(testMusic.getId());
        assertEquals("수정된 제목", updatedMusic.getTitle());
        log.info("수정된 music: {}", updatedMusic);
    }

    @Test
    @Order(5)
    @DisplayName("음악 삭제")
    public void testDelete() {
        int deleted = mapper.delete(testMusic.getId());
        assertEquals(1, deleted);
        MusicVO deletedMusic = mapper.get(testMusic.getId());
        assertNull(deletedMusic);
        log.info("삭제 완료 ID: {}", testMusic.getId());
    }
}