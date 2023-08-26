package project.singasong.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.singasong.member.domain.Member;
import project.singasong.member.enums.JoinType;
import project.singasong.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @PostMapping("/member")
    public void create(@RequestParam String id, @RequestParam String name,
            @RequestParam String gender, @RequestParam String age) {

        Member createMember = Member.builder()
            .userKey(id)
            .joinType(JoinType.NAVER)
            .name(name)
            .gender(gender)
            .ageGroup(age)
            .build();

        service.create(createMember);
    }

}
