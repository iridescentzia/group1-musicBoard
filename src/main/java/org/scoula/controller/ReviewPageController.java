package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.service.MusicService;
import org.scoula.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Log4j2
@Controller
@RequiredArgsConstructor
public class ReviewPageController {

    private final MusicService musicService;
    private final ReviewService reviewService;

    // 전체 리뷰 목록 페이지 추가
    @GetMapping("/reviews")
    public String showReviewList(Model model) {
        log.info("Getting /reviews (JSP)✅");
        model.addAttribute("reviews", reviewService.getList());
        return "review/reviewList"; // WEB-INF/views/review/reviewList.jsp
    }

    // 리뷰 상세 페이지 추가
    @GetMapping("/reviews/{id}")
    public String showReviewDetail(@PathVariable Long id, Model model) {
        log.info("GET /reviews/{} (JSP)", id);
        model.addAttribute("review", reviewService.get(id));
        return "review/reviewDetail"; // WEB-INF/views/review/reviewDetail.jsp
    }

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
