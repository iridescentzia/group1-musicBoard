package org.scoula.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.domain.MusicVO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicDTO {
    private Long id;
    private String title;
    private String artist;
    private String genre;
    private Date releaseDate;
    private String coverUrl;

    // VO -> DTO
    public static MusicDTO of(MusicVO vo) {
        return vo == null ? null : MusicDTO.builder()
                .id(vo.getId())
                .title(vo.getTitle())
                .artist(vo.getArtist())
                .genre(vo.getGenre())
                .releaseDate(vo.getReleaseDate())
                .coverUrl(vo.getCoverUrl())
                .build();
    }

    // DTO -> VO
    public MusicVO toVO() {
        return MusicVO.builder()
                .id(id)
                .title(title)
                .artist(artist)
                .genre(genre)
                .releaseDate(releaseDate)
                .coverUrl(coverUrl)
                .build();
    }
}
