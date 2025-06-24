package org.scoula.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    private Long id;
    private Long reviewId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;

    private String userName; //users 조인
    private String reviewContent; //reviews 조인
    private Integer reviewRating; //reviews 조인
}
