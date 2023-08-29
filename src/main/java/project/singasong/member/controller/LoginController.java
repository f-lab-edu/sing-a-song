package project.singasong.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.singasong.oauth.naver.dto.NaverLoginUserDto;
import project.singasong.oauth.naver.NaverOauthApi;
import project.singasong.member.domain.Member;
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

        Optional<NaverLoginUserDto> loginUser = naverApi.getAccessTokenWithParams(session, code, state);

        if(loginUser == null) {
            return "redirect:/";
        }

        memberService.create(Member.of(loginUser.get()));

        model.addAttribute("loginUser", loginUser.get());

        return "success";
    }

    @PostMapping("/logout")
    public @ResponseBody void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

}
