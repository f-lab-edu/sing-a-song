package project.singasong.member.repository;

import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import project.singasong.member.domain.Member;

@Mapper
@Repository
public interface MemberRepository {

    Optional<Member> findByUserKey(String userKey);
    int create(Member member);
    int update(Member member);

}
