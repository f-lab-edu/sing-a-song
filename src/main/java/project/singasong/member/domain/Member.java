package project.singasong.member.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.singasong.oauth.naver.dto.NaverLoginUserDto;
import project.singasong.member.enums.JoinType;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private Long id;
    private String userKey;
    private JoinType joinType;
    private LocalDateTime joinDate;
    private String name;
    private String gender;
    private String ageGroup;

    public static Member of(NaverLoginUserDto naverLoginUserDto) {
        return Member.builder()
            .userKey(naverLoginUserDto.getId())
            .ageGroup(naverLoginUserDto.getAge())
            .gender(naverLoginUserDto.getGender())
            .name(naverLoginUserDto.getName())
            .joinType(JoinType.NAVER)
            .build();
    }

}
