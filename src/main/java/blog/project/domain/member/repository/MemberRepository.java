package blog.project.domain.member.repository;

import blog.project.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    public Member Save_Member(Member member);
    public Optional<Member> findMemberId(String id);
    public List<Member> findAll();
}
