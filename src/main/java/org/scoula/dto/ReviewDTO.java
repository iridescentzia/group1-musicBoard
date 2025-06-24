package org.scoula.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.domain.ReviewVO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
    private Long id;
    private Long userId;
    private Long musicId;
    private String content;
    private int rating;
    private Date createdAt;

    // VO -> DTO 변환
    public static ReviewDTO of(ReviewVO vo) {
        return vo == null ? null : ReviewDTO.builder()
                .id(vo.getId())
                .userId(vo.getUserId())
                .musicId(vo.getMusicId())
                .content(vo.getContent())
                .rating(vo.getRating())
                .createdAt(vo.getCreatedAt())
                .build();
    }

    // DTO -> VO 변환
    public ReviewVO toVO() {
        return ReviewVO.builder()
                .id(id)
                .userId(userId)
                .musicId(musicId)
                .content(content)
                .rating(rating)
                .createdAt(createdAt)
                .build();
    }
}
