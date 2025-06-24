package org.scoula.service;

import org.scoula.domain.CommentVO;
import org.scoula.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentVO> getCommentsByReviewId(Long reviewId);
    List<CommentVO> getCommentsByUserId(Long userId);
    CommentVO getCommentById(Long id);
    void registerComment(CommentDTO commentDTO);
    void updateComment(CommentDTO commentDTO);
    void deleteComment(Long id);
}
