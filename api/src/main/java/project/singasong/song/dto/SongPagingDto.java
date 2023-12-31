package project.singasong.song.dto;

import lombok.Builder;
import lombok.Getter;
import project.common.enums.BrandType;
import project.singasong.song.enums.SearchConditionType;

@Getter
@Builder
public class SongPagingDto {

    private static int limitSize = 10;
    private int limit;
    private long offset;

    private Long id;
    private BrandType brand;
    private String title;
    private String singer;
    private String songNo;

    public static SongPagingDto of(BrandType brand, SearchConditionType searchCondition, String searchWord, long offset) {
        switch (searchCondition) {
            case SINGER -> {
                return SongPagingDto.builder()
                    .brand(brand)
                    .singer(searchWord)
                    .limit(limitSize)
                    .offset(offset)
                    .build();
            }
            case TITLE -> {
                return SongPagingDto.builder()
                    .brand(brand)
                    .title(searchWord)
                    .limit(limitSize)
                    .offset(offset)
                    .build();
            }
            case NO -> {
                return SongPagingDto.builder()
                    .brand(brand)
                    .songNo(searchWord)
                    .limit(limitSize)
                    .offset(offset)
                    .build();
            }
            default -> {
                return SongPagingDto.builder().build();
            }
        }
    }

}
