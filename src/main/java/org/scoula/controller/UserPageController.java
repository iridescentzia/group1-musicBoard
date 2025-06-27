package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserPageController {
    private final UserService userService;

    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/userList"; // -> /WEB-INF/views/userList.jsp
    }
}
