package org.scoula.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.domain.UserVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private int userId;
    private String userName;

    public static UserDTO of(UserVO vo){
        return vo == null ? null : UserDTO.builder()
                .userId(vo.getUserId())
                .userName(vo.getUserName())
                .build();
    }

    public UserVO toVO(){
        return UserVO.builder()
                .userId(userId)
                .userName(userName)
                .build();

    }
}
