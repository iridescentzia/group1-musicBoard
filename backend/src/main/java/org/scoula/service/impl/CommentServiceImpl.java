package org.scoula.service.impl;

import lombok.RequiredArgsConstructor;
import org.scoula.domain.CommentVO;
import org.scoula.dto.CommentDTO;
import org.scoula.mapper.CommentMapper;
import org.scoula.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public List<CommentVO> getCommentsByReviewId(Long reviewId) {
        return commentMapper.selectCommentsByReviewId(reviewId);
    }

    @Override
    public List<CommentVO> getCommentsByUserId(Long userId) {
        return commentMapper.selectCommentsByUserId(userId);
    }

    @Override
    public CommentVO getCommentById(Long id) {
        return commentMapper.selectById(id);
    }

    @Override
    public void registerComment(CommentDTO commentDTO) {
        commentMapper.insert(commentDTO);
    }

    @Override
    public void updateComment(CommentDTO commentDTO) {
        commentMapper.update(commentDTO);
    }

    @Override
    public void deleteComment(Long id) {
        commentMapper.delete(id);
    }
}
