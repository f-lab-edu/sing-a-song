package project.singasong.playlist.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaylistPagingDto {

    private final int limit = 5;
    private long offset;
    private Long userId;

    public static PlaylistPagingDto of(Long userId, Long offset) {
        return PlaylistPagingDto.builder()
            .userId(userId)
            .offset(offset)
            .build();
    }

}
