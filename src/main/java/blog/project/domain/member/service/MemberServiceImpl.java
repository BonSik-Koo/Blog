package blog.project.domain.member.service;

import blog.project.domain.member.Member;
import blog.project.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //중복 아이디는 가입x
    @Override
    public void join(Member member) {
        validDuplicateMember(member);
        memberRepository.Save_Member(member);
    }

    //회원의 아이디로 검색
    @Override
    public Optional<Member> findById(String id) {
        return memberRepository.findMemberId(id);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void validDuplicateMember(Member member) { // 회원가입시 회원아이디 중복 검사
        memberRepository.findMemberId(member.getMember_id())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }


}
