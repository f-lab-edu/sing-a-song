package project.singasong.song.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.singasong.song.enums.BrandType;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Song {

    private Long id;
    private String title;
    private String singer;
    @JsonProperty("no")
    private long songNo;
    private BrandType brand;

}
