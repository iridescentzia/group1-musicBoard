package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.domain.CommentVO;
import org.scoula.dto.CommentDTO;
import org.scoula.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class CommentController {

    private final CommentService commentService;

    //리뷰 ID 기준 댓글 목록 조회
    @GetMapping("/reviews/{reviewId}/comments")
    public ResponseEntity<List<CommentVO>> getCommentsByReviewId(@PathVariable Long reviewId) {
        log.info("comments fetched for reviewId {}", reviewId);
        List<CommentVO> comments = commentService.getCommentsByReviewId(reviewId);
        return ResponseEntity.ok(comments);
    }

    //사용자 ID 기준 댓글 목록 조회
    @GetMapping("/users/{userId}/comments")
    public ResponseEntity<List<CommentVO>> getCommentsByUserId(@PathVariable Long userId) {
        log.info("comments fetched for userId {}", userId);
        List<CommentVO> comments = commentService.getCommentsByUserId(userId);
        return ResponseEntity.ok(comments);
    }

    //단일 댓글 조회
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentVO> getComment(@PathVariable Long id) {
        log.info("comment fetched for id {}", id);
        CommentVO comment = commentService.getCommentById(id);
        if (comment == null) {
            log.warn("comment not found for id {}", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment);
    }

    //댓글 작성
    @PostMapping("/reviews/{reviewId}/comments")
    public ResponseEntity<Void> registerComments(@PathVariable Long reviewId, @RequestBody CommentDTO commentDTO) {
        log.info("comment registered");
        commentDTO.setReviewId(reviewId);
        commentDTO.setCreatedAt(new Date());
        commentService.registerComment(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //댓글 수정
    @PutMapping("/comments/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        log.info("comment updated for id {}", id);
        commentDTO.setId(id);
        commentService.updateComment(commentDTO);
        return ResponseEntity.ok().build();
    }

    //댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        log.info("comment deleted for id {}", id);
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
