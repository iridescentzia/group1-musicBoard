package org.scoula.mapper;


import org.apache.ibatis.annotations.Param;
import org.scoula.domain.CommentVO;
import org.scoula.dto.CommentDTO;

import java.util.List;

public interface CommentMapper {

    //리뷰 ID 기준 댓글 + 리뷰 내용/별점 조회
    List<CommentVO> selectCommentsByReviewId(@Param("reviewId") Long reviewId);

    //사용자 ID 기준 댓글 + 사용자 이름 조회
    List<CommentVO> selectCommentsByUserId(@Param("userId") Long userId);

    //ID 기준 단일 댓글 조회
    CommentVO selectById(@Param("id") Long id);

    //댓글 등록
    int insert(CommentDTO commentDTO);

    //댓글 수정
    int update(CommentDTO commentDTO);

    //댓글 삭제
    int delete(@Param("id") Long id);
}

