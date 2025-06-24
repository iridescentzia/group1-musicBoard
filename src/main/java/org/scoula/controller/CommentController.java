package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.domain.CommentVO;
import org.scoula.dto.CommentDTO;
import org.scoula.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class CommentController {

    private final CommentService commentService;

    //리뷰 ID 기준 댓글 목록 조회
    @GetMapping("/reviews/{reviewId}/comments")
    public List<CommentVO> getCommentsByReviewId(@PathVariable Long reviewId) {
        return commentService.getCommentsByReviewId(reviewId);
    }

    //사용자 ID 기준 댓글 목록 조회
    @GetMapping("/users/{userId}/comments")
    public List<CommentVO> getCommentsByUserId(@PathVariable Long userId) {
        return commentService.getCommentsByUserId(userId);
    }

    //단일 댓글 조회
    @GetMapping("/comments/{id}")
    public CommentVO getComment(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    //댓글 작성
    @PostMapping("/reviews/{reviewId}/comments")
    public void registerComments(@PathVariable Long reviewId, @RequestBody CommentDTO commentDTO) {
        commentDTO.setReviewId(reviewId);
        commentDTO.setCreatedAt(LocalDateTime.now());
        commentService.registerComment(commentDTO);
    }

    //댓글 수정
    @PutMapping("/comments/{id}")
    public void updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        commentDTO.setId(id);
        commentService.updateComment(commentDTO);
    }

    //댓글 삭제
    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
