package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.dto.ReviewDTO;
import org.scoula.dto.UserDTO;
import org.scoula.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    //모든 사용자 조회
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 사용자 추가
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO dto) {
        userService.insertUser(dto);
        return ResponseEntity.ok("User created");
    }

    // 특정 사용자 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // 사용자 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted");
    }

    // 사용자 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody UserDTO dto) {
        dto.setId(id);
        userService.updateUser(dto);
        return ResponseEntity.ok("User updated");
    }

    // 해당 사용자의 리뷰 목록 조회
    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviewsByUserId(@PathVariable int id) {
        List<ReviewDTO> reviews = userService.getReviewsByUserId(id);
        return ResponseEntity.ok(reviews);
    }
}
