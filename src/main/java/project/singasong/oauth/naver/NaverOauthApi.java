package project.singasong.oauth.naver;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import project.singasong.oauth.naver.dto.NaverCallbackInfoDto;
import project.singasong.oauth.naver.dto.NaverLoginUserDto;
import project.singasong.oauth.naver.dto.NaverOauthDto;
import project.singasong.oauth.naver.properties.NaverSecretProperty;

@Component
@RequiredArgsConstructor
public class NaverOauthApi {

    private final NaverSecretProperty naverSecretProperty;
    private final String SESSION_STATE = "NAVER_OAUTH_STATE";

    public String getAuthorizationUrlWithParams(HttpSession session) {
        String state = generateUuid();
        setSession(session, state);

        StringBuilder url = new StringBuilder();
        url.append(naverSecretProperty.getAuthorizationUrl())
            .append("&client_id=" + naverSecretProperty.getClientId())
            .append("&state=" + state)
            .append("&redirect_uri=" + naverSecretProperty.getRedirectUrl());

        return url.toString();
    }

    public Optional<NaverLoginUserDto> getAccessTokenWithParams(HttpSession session, String code, String state) {
        String sessionState = getSession(session);

        if (StringUtils.equals(sessionState, state)) {
            setSession(session, state);

            ResponseEntity<NaverOauthDto> naverTokenInfo = getToken(state, code);
            ResponseEntity<NaverCallbackInfoDto> loginUserInfo = getUserProfile(naverTokenInfo.getBody());

            return Optional.ofNullable(loginUserInfo.getBody().getResponse());
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
        params.add("client_id", naverSecretProperty.getClientId());
        params.add("client_secret", naverSecretProperty.getClientSecret());
        params.add("grant_type", "authorization_code");
        params.add("state", state);
        params.add("code", code);

        HttpEntity<MultiValueMap<String,String>> naverTokenRequest = new HttpEntity<>(params, new HttpHeaders());

        return restTemplate.exchange(
            naverSecretProperty.getTokenUrl(),
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
            naverSecretProperty.getUserInfoUrl(),
            HttpMethod.POST,
            naverProfileRequest,
            NaverCallbackInfoDto.class
        );
    }

}
