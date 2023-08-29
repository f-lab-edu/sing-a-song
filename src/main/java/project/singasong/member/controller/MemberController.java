package project.singasong.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import project.singasong.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

}
