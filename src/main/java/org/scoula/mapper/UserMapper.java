package org.scoula.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.scoula.domain.UserVO;
import org.scoula.dto.ReviewDTO;

import java.util.List;

@Mapper
public interface UserMapper {

    //모든 사용자 조회
    List<UserVO> getAllUsers();
    // 사용자 추가
    void insertUser(UserVO user);
    //id로 사용자 조히
    UserVO getUserById(int id);
    //삭제
    void deleteUserById(int id);
    //수정
    void updateUser(UserVO user);
    //해당 사용자의 리뷰 목록 조회
    List<ReviewDTO> getReviewsByUserId(int userId);
}
