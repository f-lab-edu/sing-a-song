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

    private final MemberRepository repository;

    public void create(Member member) {
        Member findMember = repository.findByUserId(member.getUserKey()).orElseGet(() -> {
            repository.create(member);
            return member;
        });

        if(!StringUtils.equals(member.getAgeGroup(), findMember.getAgeGroup())) {
            repository.update(member);
        }
    }

}
