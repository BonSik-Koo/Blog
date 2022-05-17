package blog.project.domain.member.repository;

import blog.project.domain.member.Member;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private final Map<String , Member> storge = new HashMap<String,Member>();

    @Override
    public Member Save_Member(Member member) {
        storge.put(member.getMember_id(), member);
        return member;
    }

    @Override
    public Optional<Member> findMemberId(String id) {
        return Optional.ofNullable(storge.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<Member>(storge.values());
    }
}
