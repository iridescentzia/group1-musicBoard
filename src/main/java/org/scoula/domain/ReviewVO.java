package org.scoula.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewVO {
    private Long id; // 리뷰 고유 ID
    private Long userId; // 작성자 (users 테이블 참조)
    private Long musicId; // 리뷰 대상 음악 (music 테이블 참조)
    private String content; // 리뷰 내용
    private int rating; // 평점
    private Date createdAt; // 작성 일시
}
