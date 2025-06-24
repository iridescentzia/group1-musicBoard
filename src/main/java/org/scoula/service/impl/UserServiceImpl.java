package org.scoula.service.impl;

import lombok.RequiredArgsConstructor;
import org.scoula.dto.UserDTO;
import org.scoula.mapper.UserMapper;
import org.scoula.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;

    @Override
    public List<UserDTO> getAllUsers(){
        return mapper.getAllUsers().stream()
                .map(UserDTO::of)
                .toList();
    }

    @Override
    public void insertUser(UserDTO dto) {
        mapper.insertUser(dto.toVO());
    }

    @Override
    public UserDTO getUserById(int id) {
        return UserDTO.of(mapper.getUserById(id));
    }

    @Override
    public void deleteUserById(int id) {
        mapper.deleteUserById(id);
    }

    @Override
    public void updateUser(UserDTO dto) {
        mapper.updateUser(dto.toVO());
    }

    @Override
    public List<ReviewDTO> getReviewsByUserId(int userId){
        return mapper.getReviewsByUserId(userId);
    }

}
