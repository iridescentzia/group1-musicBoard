package org.scoula.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.domain.CommentVO;
import org.scoula.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    private static CommentDTO testCommentDTO;
    private static Long insertedId;

    @BeforeAll
    static void setUp() {
        testCommentDTO = CommentDTO.builder()
                .reviewId(1L)
                .userId(1L)
                .content("테스트 댓글 내용")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("댓글 등록 테스트")
    void registerComment() {
        commentService.registerComment(testCommentDTO);
        insertedId = testCommentDTO.getId();
        assertNotNull(insertedId, "등록 후 댓글 ID는 null 이 아니어야 한다.");
        log.info("등록된 댓글 ID: {}", insertedId);
    }

    @Test
    @Order(2)
    @DisplayName("리뷰 ID 기준 댓글 목록 조회 테스트")
    void getCommentsByReviewId() {
        List<CommentVO> comments = commentService.getCommentsByReviewId(testCommentDTO.getReviewId());
        assertNotNull(comments, "댓글 목록이 null이면 안 된다.");
        log.info("리뷰 ID {} / 댓글 개수: {}", testCommentDTO.getReviewId(), comments.size());
    }

    @Test
    @Order(3)
    @DisplayName("사용자 ID 기준 댓글 목록 조회 테스트")
    void getCommentsByUserId() {
        List<CommentVO> comments = commentService.getCommentsByUserId(testCommentDTO.getUserId());
        assertNotNull(comments, "댓글 목록이 null이면 안 된다.");
        log.info("사용자 ID {} / 댓글 개수: {}", testCommentDTO.getUserId(), comments.size());
    }

    @Test
    @Order(4)
    @DisplayName("ID 기준 단일 댓글 조회 테스트")
    void getCommentById() {
        CommentVO comment = commentService.getCommentById(insertedId);
        assertNotNull(comment, "댓글 조회 결과가 null이면 안 된다.");
        assertEquals(insertedId, comment.getId(), "조회된 댓글 ID가 일치해야 한다.");
        log.info("조회된 댓글: {}", comment);
    }

    @Test
    @Order(5)
    @DisplayName("댓글 수정 테스트")
    void updateComment() {
        CommentDTO updatedDTO = CommentDTO.builder()
                .id(insertedId)
                .content("수정된 댓글 내용")
                .build();
        commentService.updateComment(updatedDTO);

        CommentVO updatedComment = commentService.getCommentById(insertedId);
        assertEquals("수정된 댓글 내용", updatedComment.getContent(), "댓글 내용이 수정되어야 한다.");
        log.info("수정된 댓글: {}", updatedComment);
    }

    @Test
    @Order(6)
    @DisplayName("댓글 삭제 테스트")
    void deleteComment() {
        commentService.deleteComment(insertedId);
        CommentVO deletedComment = commentService.getCommentById(insertedId);
        assertNull(deletedComment, "삭제된 댓글은 조회 시 null이어야 한다.");
        log.info("삭제 완료 댓글 ID: {}", insertedId);
    }
}
