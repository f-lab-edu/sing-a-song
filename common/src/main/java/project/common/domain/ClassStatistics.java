package project.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.enums.BrandType;
import project.common.enums.Gender;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassStatistics {
    private Long id;
    private String title;
    private String singer;
    private long songNo;
    private BrandType brand;
    private Gender gender;
    private String ageGroup;
    private LocalDateTime insertDate;
    private int likes;
}
