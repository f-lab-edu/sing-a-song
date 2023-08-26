package project.singasong.login.oauth.naver;

import jakarta.servlet.http.HttpSession;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import project.singasong.login.dto.naver.NaverCallbackInfoDto;
import project.singasong.login.dto.naver.NaverLoginUserDto;
import project.singasong.login.dto.naver.NaverOauthDto;

@Component
@RequiredArgsConstructor
public class NaverOauthApi {

    @Value("${naver.url.getAuthorization}")
    private String GET_AUTHORIZATION_URL;
    @Value("${naver.url.getToken}")
    private String GET_TOKEN_URL;
    @Value("${naver.url.getUserInfo}")
    private String GET_USER_INFO_URL;
    @Value("${oAuthCommon.callback.url}")
    private String REDIRECT_URI;
    @Value("${naver.client.id}")
    private String CLIENT_ID;
    @Value("${naver.client.secret}")
    private String CLIENT_SECRET;

    private final String SESSION_STATE = "NAVER_OAUTH_STATE";

    public String getAuthorizationUrlWithParams(HttpSession session) {
        String state = generateUuid();
        setSession(session, state);

        StringBuilder url = new StringBuilder();
        url.append(GET_AUTHORIZATION_URL)
            .append("&client_id=" + CLIENT_ID)
            .append("&state=" + state)
            .append("&redirect_uri=" + REDIRECT_URI);

        return url.toString();
    }

    public NaverLoginUserDto getAccessTokenWithParams(HttpSession session, String code, String state) {
        String sessionState = getSession(session);

        if (StringUtils.equals(sessionState, state)) {
            setSession(session, state);

            ResponseEntity<NaverOauthDto> naverTokenInfo = getToken(state, code);
            ResponseEntity<NaverCallbackInfoDto> loginUserInfo = getUserProfile(naverTokenInfo.getBody());

            return loginUserInfo.getBody().getResponse();
        }

        return null;
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }

    private void setSession(HttpSession session, String state) {
        session.setAttribute(SESSION_STATE, state);
    }

    private String getSession(HttpSession session) {
        return (String) session.getAttribute(SESSION_STATE);
    }

    private ResponseEntity<NaverOauthDto> getToken(String state, String code) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", CLIENT_SECRET);
        params.add("grant_type", "authorization_code");
        params.add("state", state);
        params.add("code", code);

        HttpEntity<MultiValueMap<String,String>> naverTokenRequest = new HttpEntity<>(params, new HttpHeaders());

        return restTemplate.exchange(
            GET_TOKEN_URL,
            HttpMethod.POST,
            naverTokenRequest,
            NaverOauthDto.class
        );
    }

    private ResponseEntity<NaverCallbackInfoDto> getUserProfile(NaverOauthDto tokenInfo) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+ tokenInfo.getAccess_token());

        HttpEntity<MultiValueMap<String,String>> naverProfileRequest= new HttpEntity<>(headers);

        return restTemplate.exchange(
            GET_USER_INFO_URL,
            HttpMethod.POST,
            naverProfileRequest,
            NaverCallbackInfoDto.class
        );
    }

}
