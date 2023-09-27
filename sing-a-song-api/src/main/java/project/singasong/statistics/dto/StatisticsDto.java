package project.singasong.statistics.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import project.common.enums.BrandType;
import project.singasong.song.enums.SearchConditionType;

@Getter
@Builder
public class StatisticsDto {

    private Long id;
    private String title;
    private String singer;
    private long songNo;
    private BrandType brand;
    private int likes;
    private String gender;
    private String ageGroup;
    private LocalDateTime insertDate;
    private String formatDate;

}
