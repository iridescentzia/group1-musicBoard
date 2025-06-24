package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.service.MusicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class ReviewPageController {

    private final MusicService musicService;

    @GetMapping("/reviews/form")
    public String showReviewForm(Model model) {
        log.info("GET /reviews/form (JSP) ✅");

        // 음악 목록 전달 (select 박스용)
        model.addAttribute("musicList", musicService.getList());

        // 사용자 ID 임시 전달 (예: 1번 유저)
        model.addAttribute("userId", 1);

        return "review/reviewForm"; // => /WEB-INF/views/review/reviewForm.jsp
    }

    @GetMapping("/reviews/my")
    public String showMyReviews(Model model) {
        log.info("GET /reviews/my (JSP) ✅");

        // 로그인 유저의 ID 기준으로 리뷰 조회 (지금은 임시로 1번 유저로 고정)
        Long userId = 1L;
        model.addAttribute("userId", userId);

        return "review/reviewMyList"; // => /WEB-INF/views/review/reviewMyList.jsp
    }
}
