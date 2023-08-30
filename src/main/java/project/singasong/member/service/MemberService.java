package project.singasong.member.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import project.singasong.member.domain.Member;
import project.singasong.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> findByUserKey(String userKey) {
        return memberRepository.findByUserKey(userKey);
    }

    public void create(Member member) {
        Member findMember = memberRepository.findByUserKey(member.getUserKey()).orElseGet(() -> {
            memberRepository.create(member);
            return member;
        });

        if(!StringUtils.equals(member.getAgeGroup(), findMember.getAgeGroup())) {
            memberRepository.update(member);
        }
    }

}
