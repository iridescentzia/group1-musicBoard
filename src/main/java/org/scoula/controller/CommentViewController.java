package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.domain.CommentVO;
import org.scoula.dto.CommentDTO;
import org.scoula.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
@Log4j2
public class CommentViewController {

    private final CommentService commentService;

    // 1) 리뷰 ID 기준 댓글 목록 조회
    @GetMapping("/review/{reviewId}")
    public String getCommentsByReviewId(@PathVariable Long reviewId, Model model) {
        log.info("View: Fetching comments list for reviewId {}", reviewId);
        List<CommentVO> comments = commentService.getCommentsByReviewId(reviewId);
        model.addAttribute("comments", comments);
        model.addAttribute("reviewId", reviewId);
        model.addAttribute("mode", "list");
        return "comment/commentList";
    }

    // 1-1) 사용자 ID 기준 댓글 목록 조회
    @GetMapping("/user/{userId}")
    public String getCommentsByUserId(@PathVariable Long userId, Model model) {
        log.info("View: Fetching comments list for userId {}", userId);
        List<CommentVO> comments = commentService.getCommentsByUserId(userId);
        model.addAttribute("comments", comments);
        model.addAttribute("userId", userId);
        model.addAttribute("mode", "listUser");
        return "comment/commentList";
    }

    // 2) 단일 댓글 상세 조회
    @GetMapping("/{id}")
    public String getComment(@PathVariable Long id, Model model) {
        log.info("View: Fetching comment detail for id {}", id);
        CommentVO comment = commentService.getCommentById(id);
        if (comment == null) {
            model.addAttribute("error", "해당 댓글이 존재하지 않습니다.");
            model.addAttribute("mode", "error");
        } else {
            model.addAttribute("comment", comment);
            model.addAttribute("mode", "detail");
        }
        return "comment/commentList";
    }

    // 3) 댓글 작성 폼 페이지
    @GetMapping("/review/{reviewId}/new")
    public String showCommentForm(@PathVariable Long reviewId, Model model) {
        model.addAttribute("reviewId", reviewId);
        model.addAttribute("mode", "form");
        return "comment/commentList";
    }

    // 4) 댓글 작성 처리
    @PostMapping("/review/{reviewId}/new")
    public String registerComment(@PathVariable Long reviewId,
                                  @RequestParam Long userId,
                                  @RequestParam String content) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setReviewId(reviewId);
        commentDTO.setUserId(userId);
        commentDTO.setContent(content);
        commentDTO.setCreatedAt(new Date());
        commentService.registerComment(commentDTO);
        log.info("View: Comment registered for reviewId {}", reviewId);
        return "redirect:/comments/review/" + reviewId;
    }

    // 5) 댓글 수정 폼 페이지
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        CommentVO comment = commentService.getCommentById(id);
        if (comment == null) {
            model.addAttribute("error", "수정할 댓글이 존재하지 않습니다.");
            model.addAttribute("mode", "error");
        } else {
            model.addAttribute("comment", comment);
            model.addAttribute("mode", "edit");
        }
        return "comment/commentList";
    }

    // 6) 댓글 수정 처리
    @PostMapping("/{id}/edit")
    public String updateComment(@PathVariable Long id,
                                @RequestParam String content) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(id);
        commentDTO.setContent(content);
        commentService.updateComment(commentDTO);
        log.info("View: Comment updated for id {}", id);
        return "redirect:/comments/" + id;
    }

    // 7) 댓글 삭제 처리
    @GetMapping("/{id}/delete")
    public String deleteComment(@PathVariable Long id) {
        CommentVO comment = commentService.getCommentById(id);
        if (comment != null) {
            commentService.deleteComment(id);
            log.info("View: Comment deleted for id {}", id);
            // 삭제한 댓글의 리뷰 ID로 목록으로 리다이렉트
            Long reviewId = comment.getReviewId();
            return "redirect:/comments/review/" + (reviewId != null ? reviewId : 1L);
        } else {
            // 댓글이 없으면 기본 리뷰 ID 1번 목록으로 이동 (필요시 변경)
            return "redirect:/comments/review/1";
        }
    }
}
