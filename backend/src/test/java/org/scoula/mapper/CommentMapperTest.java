/*
package org.scoula.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.domain.CommentVO;
import org.scoula.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;

    private static CommentDTO testCommentDTO;
    private static Long insertedId;

    @BeforeAll
    static void beforeAll() {
        testCommentDTO = CommentDTO.builder()
                .reviewId(1L)
                .userId(1L)
                .content("테스트 댓글 내용")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("댓글 입력 테스트")
    void insert() {
        int affectedRows = commentMapper.insert(testCommentDTO);
        assertEquals(1, affectedRows, "insert 결과가 1이어야 정상");
        assertNotNull(testCommentDTO.getId(), "입력 후 ID가 null이면 안 된다.");
        insertedId = testCommentDTO.getId();
        log.info("입력된 댓글 ID: {}", insertedId);
    }

    @Test
    @Order(2)
    @DisplayName("리뷰 ID 기준 댓글 목록 조회 테스트")
    void selectCommentsByReviewId() {
        List<CommentVO> comments = commentMapper.selectCommentsByReviewId(testCommentDTO.getReviewId());
        assertNotNull(comments, "댓글 목록이 null이면 안 된다.");
        //assertFalse(comments.isEmpty(), "댓글 목록이 비어있으면 안 된다.")
        log.info("리뷰 ID: {} / 댓글 개수: {}", testCommentDTO.getReviewId(), comments.size());
    }

    @Test
    @Order(3)
    @DisplayName("사용자 ID 기준 댓글 목록 조회 테스트")
    void selectCommentsByUserId() {
        List<CommentVO> comments = commentMapper.selectCommentsByUserId(testCommentDTO.getUserId());
        assertNotNull(comments, "댓글 목록이 null이면 안 된다.");
        log.info("사용자 ID: {} / 댓글 개수: {}", testCommentDTO.getUserId(), comments.size());
    }

    @Test
    @Order(4)
    @DisplayName("ID 기준 단일 댓글 조회 테스트")
    void selectById() {
        CommentVO commentVO = commentMapper.selectById(insertedId);
        assertNotNull(commentVO, "조회된 댓글이 null이면 안 된다.");
        assertEquals(insertedId, commentVO.getId(), "조회된 댓글 ID가 일치해야 한다.");
        log.info("조회된 댓글: {}", commentVO);
    }

    @Test
    @Order(5)
    @DisplayName("댓글 수정 테스트")
    void update() {
        CommentDTO updatedDTO = CommentDTO.builder()
                .id(insertedId)
                .content("수정된 댓글 내용")
                .build();
        int updatedRows = commentMapper.update(updatedDTO);
        assertEquals(1, updatedRows, "수정된 행 1이여야 정상");

        CommentVO updatedComment = commentMapper.selectById(insertedId);
        assertEquals("수정된 댓글 내용", updatedComment.getContent(), "댓글 내용이 수정되어야 한다.");
        log.info("수정된 댓글: {}", updatedComment);
    }

    @Test
    @Order(6)
    @DisplayName("댓글 삭제 테스트")
    void delete() {
        int deletedRows = commentMapper.delete(insertedId);
        assertEquals(1, deletedRows, "삭제된 행 1이어야 정상");

        CommentVO deletedComment = commentMapper.selectById(insertedId);
        assertNull(deletedComment, "삭제된 댓글은 조회 시 null이어야 한다.");
        log.info("삭제 완료 댓글 ID: {}", insertedId);
    }
}*/
