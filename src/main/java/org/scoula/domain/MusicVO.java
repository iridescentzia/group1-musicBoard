package org.scoula.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MusicVO {
    private Long id;
    private String title;
    private String artist;
    private String genre;
    private Date releaseDate;
    private String coverUrl;
}
