package org.scoula.service;

import org.scoula.dto.ReviewDTO;
import org.scoula.dto.UserDTO;

import java.util.List;

public interface UserService {
    public List<UserDTO> getAllUsers();
    public UserDTO getUserById(int id);
    public void insertUser(UserDTO user);
    public void updateUser(UserDTO user);
    public void deleteUserById(int id);
    public List<ReviewDTO> getReviewsByUserId(int userId);

}
