package project.singasong.playlistSong.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.enums.BrandType;

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
    private long songNo;
    private BrandType brand;
    private boolean isLike;

}
