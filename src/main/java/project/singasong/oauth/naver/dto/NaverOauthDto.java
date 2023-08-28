package project.singasong.oauth.naver.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverOauthDto {

    private String access_token;
    private String refresh_token;
    private String token_type;
    private Long expires_in;

    private String error;
    private String error_description;

}
