package blog.project.domain.member.service;

import blog.project.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    public void join(Member member); // 회원가입
    public Optional<Member> findById(String id); //아이디 찾기
    public List<Member> findAll();

}
