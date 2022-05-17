package blog.project.domain.login;

import blog.project.domain.member.Member;
import blog.project.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String member_id, String member_password) {
        return memberRepository.findMemberId(member_id)
                .filter( m -> m.getMember_password().equals(member_password)) //일치하면 회원 반환
                .orElse(null); //일치하지 않으면 null반환
    }


}
