package project.singasong.like.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.enums.BrandType;
import project.singasong.playlistSong.dto.PlaylistSongPagingDto;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LikePagingDto {

    private static int limitSize = 10;
    private int limit;
    private long offset;

    private Long userId;
    private Long songId;
    private String title;
    private String singer;
    private long songNo;
    private BrandType brand;

    public static LikePagingDto of(long userId, long offset) {
        return LikePagingDto.builder()
            .userId(userId)
            .limit(limitSize)
            .offset(offset)
            .build();
    }

}
