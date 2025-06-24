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
    private int id;
    private String username;

    public static UserDTO of(UserVO vo){
        return vo == null ? null : UserDTO.builder()
                .id(vo.getId())
                .username(vo.getUsername())
                .build();
    }

    public UserVO toVO(){
        return UserVO.builder()
                .id(id)
                .username(username)
                .build();

    }
}
