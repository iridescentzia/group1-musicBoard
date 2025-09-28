package org.scoula.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.domain.MusicVO;
import org.scoula.domain.ReviewVO;

import java.time.LocalDateTime;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    private MusicDTO music;

    // VO -> DTO 변환
    public static ReviewDTO of(ReviewVO vo) {
        return vo == null ? null : ReviewDTO.builder()
                .id(vo.getId())
                .userId(vo.getUserId())
                .musicId(vo.getMusicId())
                .content(vo.getContent())
                .rating(vo.getRating())
                .createdAt(vo.getCreatedAt())
                .music(MusicDTO.of(vo.getMusic())) // MusicVo -> MusicDTO 변환
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
                .music(music != null ? music.toVO() : null) // MusicDTO -> MusicVO 변
                .build();
    }
}
