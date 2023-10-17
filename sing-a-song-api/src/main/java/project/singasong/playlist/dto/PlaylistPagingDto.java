package project.singasong.playlist.dto;

import lombok.Builder;
import lombok.Getter;
import project.singasong.song.enums.SearchConditionType;

@Getter
@Builder
public class PlaylistPagingDto {

    private final int limit = 5;
    private long offset;
    private Long userId;
    private String title;
    private String name;

    public static PlaylistPagingDto of(Long userId, long offset) {
        return PlaylistPagingDto.builder()
            .userId(userId)
            .offset(offset)
            .build();
    }

    public static PlaylistPagingDto allOf(long offset) {
        return PlaylistPagingDto.builder()
            .offset(offset)
            .build();
    }

    public static PlaylistPagingDto searchAllOf(SearchConditionType searchCondition, String searchWord, long offset) {
        switch (searchCondition) {
            case TITLE -> {
                return PlaylistPagingDto.builder()
                    .title(searchWord)
                    .offset(offset)
                    .build();
            }
            case NAME -> {
                return PlaylistPagingDto.builder()
                    .name(searchWord)
                    .offset(offset)
                    .build();
            }
            default -> throw new IllegalStateException("검색 조건이 누락되었습니다.");
        }
    }

}
