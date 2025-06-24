package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.config.ServletConfig;
import org.scoula.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class})
@Log4j2
class CommentControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Autowired
    private CommentService commentService;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    //리뷰 ID 기준 댓글 목록 조회 테스트(GET)
    @Test
    void getCommentsByReviewId() throws Exception {
        Long reviewId = 1L;
        mockMvc.perform(get("/api/reviews/{reviewId}/comments", reviewId))
                .andExpect(status().isOk())
                .andDo(result -> log.info("Response: {}", result.getResponse().getContentAsString()));
    }

    //사용자 ID 기준 댓글 목록 조회(GET)
    @Test
    void getCommentsByUserId() throws Exception {
        Long userId = 1L;
        mockMvc.perform(get("/api/users/{userId}/comments", userId))
                .andExpect(status().isOk())
                .andDo(result -> log.info("Response: {}", result.getResponse().getContentAsString()));
    }

    //단일 댓글 조회(GET)
    @Test
    void getComment() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/api/comments/{id}", id))
                .andExpect(status().isOk())
                .andDo(result -> log.info("Response: {}", result.getResponse().getContentAsString()));
    }

    //댓글 작성(POST)
    @Test
    void registerComments() throws Exception {
        Long reviewId = 1L;
        String json = """
            {
                "userId": 1,
                "content": "테스트 댓글 작성"
            }
            """;

        mockMvc.perform(post("/api/reviews/{reviewId}/comments", reviewId)
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andDo(result -> log.info("댓글 등록 완료"));
    }

    //댓글 수정(PUT)
    @Test
    void updateComment() throws Exception {

        Long id = 1L;
        String json = """
            {
                "content": "수정된 댓글 내용"
            }
            """;

        mockMvc.perform(put("/api/comments/{id}", id)
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andDo(result -> log.info("댓글 수정 완료"));
    }

    //댓글 삭제(DELETE)
    @Test
    void deleteComment() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/comments/{id}", id))
                .andExpect(status().isOk())
                .andDo(result -> log.info("댓글 삭제 완료"));
    }
}