package project.singasong.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import project.singasong.member.domain.Member;
import project.singasong.member.service.MemberService;
import project.singasong.oauth.naver.NaverOauthApi;
import project.singasong.oauth.naver.dto.NaverCallbackInfoDto;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final NaverOauthApi naverApi;
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String accessToken = (String) request.getSession().getAttribute("accessToken");
        if (accessToken == null) {
            throw new IllegalStateException("다시 로그인 해주셔야 합니다.");
        }

        getUserProfile(request, response);
        return true;
    }

    private void getUserProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accessToken = (String) request.getSession().getAttribute("accessToken");
        ResponseEntity<NaverCallbackInfoDto> loginUser = naverApi.getUserProfile(accessToken, request.getSession());

        if(StringUtils.equals(loginUser.getBody().getMessage(), "success")) {
            response.sendRedirect("/");
        }

        Optional<Member> userProfile = memberService.findByUserKey(loginUser.getBody().getResponse().getId());
        if(userProfile.isEmpty()) {
            response.sendRedirect("/");
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loginUser.getBody().getResponse());
        session.setAttribute("userId", userProfile.get().getId());
    }

}
