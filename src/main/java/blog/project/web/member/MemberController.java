package blog.project.web.member;

import blog.project.domain.member.Member;
import blog.project.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String join(@ModelAttribute("member") Member member) {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute Member member, BindingResult bindingResult) {

        //검증오류가 발생
        if(bindingResult.hasErrors()) {
            log.info("회원 가입 오류 발생:{}",bindingResult);
            return "member/join";
        }

        Member joinMember = new Member(member.getMember_id(),member.getMember_password(),member.getMember_name(), member.getMember_birth(), member.getMember_address());
        try { //성공적인 회원가입
            memberService.join(joinMember);
            return "redirect:/";
        }
        catch (IllegalStateException e) { //중복된 아이디의 회원가입
            bindingResult.rejectValue("member_id","duplicate", "중복된 아이디 입니다.");
            //model.addAttribute("member", member);
            return "member/join";
        }
    }

}
