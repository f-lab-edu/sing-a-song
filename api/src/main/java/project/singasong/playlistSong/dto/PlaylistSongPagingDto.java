package project.singasong.playlistSong.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.enums.BrandType;
import project.singasong.playlist.dto.PlaylistPagingDto;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaylistSongPagingDto {

    private static int limitSize = 10;
    private int limit;
    private long offset;

    private Long songId;
    private Long playlistId;
    private String title;
    private String singer;
    private long songNo;
    private long userId;
    private BrandType brand;
    private boolean isLike;

    public static PlaylistSongPagingDto of(long playlistId, long offset) {
        return PlaylistSongPagingDto.builder()
            .playlistId(playlistId)
            .limit(limitSize)
            .offset(offset)
            .build();
    }

    public static PlaylistSongPagingDto userOf(long playlistId, long userId, long offset) {
        return PlaylistSongPagingDto.builder()
            .playlistId(playlistId)
            .userId(userId)
            .limit(limitSize)
            .offset(offset)
            .build();
    }

}
