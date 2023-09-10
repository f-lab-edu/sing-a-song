package project.singasong.playlistSong.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.singasong.song.enums.BrandType;
import project.singasong.song.enums.SearchConditionType;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaylistSongPagingDto {

    private final int limit = 10;
    private long offset;

    private Long songId;
    private Long playlistId;
    private String title;
    private String singer;
    private Long songNo;
    private String brand;

}
