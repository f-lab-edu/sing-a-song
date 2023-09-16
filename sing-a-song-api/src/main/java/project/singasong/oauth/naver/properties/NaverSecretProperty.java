package project.singasong.oauth.naver.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NaverSecretProperty {

    @Value("${naver.url.getAuthorization}")
    private String authorizationUrl;
    @Value("${naver.url.getToken}")
    private String tokenUrl;
    @Value("${naver.url.getUserInfo}")
    private String userInfoUrl;
    @Value("${oAuthCommon.callback.url}")
    private String redirectUrl;
    @Value("${naver.client.id}")
    private String clientId;
    @Value("${naver.client.secret}")
    private String clientSecret;

}
