package project.singasong.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.singasong.login.dto.naver.NaverLoginUserDto;
import project.singasong.login.oauth.naver.NaverOauthApi;
import project.singasong.member.domain.Member;
import project.singasong.member.enums.JoinType;
import project.singasong.member.service.MemberService;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final NaverOauthApi naverApi;
    private final MemberService memberService;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @PostMapping("/login")
    public @ResponseBody String login(HttpSession session) {
        return naverApi.getAuthorizationUrlWithParams(session);
    }

    @GetMapping("/login/success")
    public String loginCallback(@RequestParam String code, @RequestParam String state
                ,HttpSession session, Model model) {

        NaverLoginUserDto loginUser = naverApi.getAccessTokenWithParams(session, code, state);

        if(loginUser == null) {
            return "redirect:/";
        }

        memberService.create(createMember(loginUser));

        model.addAttribute("loginUser", loginUser);

        return "success";
    }

    @PostMapping("/logout")
    public @ResponseBody void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

    private Member createMember(NaverLoginUserDto loginUser) {
        return Member.builder()
            .userKey(loginUser.getId())
            .ageGroup(loginUser.getAge())
            .gender(loginUser.getGender())
            .name(loginUser.getName())
            .joinType(JoinType.NAVER)
            .build();
    }

}
