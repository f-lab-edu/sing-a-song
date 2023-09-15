package project.singasong.song.dto;

import lombok.Builder;
import lombok.Getter;
import project.singasong.song.enums.BrandType;
import project.singasong.song.enums.SearchConditionType;

@Getter
@Builder
public class SongPagingDto {

    private final int limit = 10;
    private long offset;

    private Long id;
    private BrandType brand;
    private String title;
    private String singer;
    private String songNo;

    public static SongPagingDto of(BrandType brand, SearchConditionType searchCondition, String searchWord, long offset) {
        switch (searchCondition) {
            case singer -> {
                return SongPagingDto.builder()
                    .brand(brand)
                    .singer(searchWord)
                    .offset(offset)
                    .build();
            }
            case title -> {
                return SongPagingDto.builder()
                    .brand(brand)
                    .title(searchWord)
                    .offset(offset)
                    .build();
            }
            case no -> {
                return SongPagingDto.builder()
                    .brand(brand)
                    .songNo(searchWord)
                    .offset(offset)
                    .build();
            }
            default -> {
                return SongPagingDto.builder().build();
            }
        }
    }

}
