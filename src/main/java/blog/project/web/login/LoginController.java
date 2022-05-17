package blog.project.web.login;

import blog.project.domain.login.LoginService;
import blog.project.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
                        HttpServletRequest request){

        if(bindingResult.hasErrors()) {
            log.info("로그인 오류:{}", bindingResult);
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getMember_id(), loginForm.getMember_password());

        //아이디, 비밀번호가 틀릴 시
        if(loginMember==null) {
            bindingResult.reject("loginFail", "아이디 혹은 비밀번호가 일치하지 않습니다.");
            return "login/loginForm";
        }

        //정상적인 로그인
        HttpSession session = request.getSession();
        session.setAttribute("loginId", loginForm.getMember_id());

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if(session!=null) {
            session.removeAttribute("loginId");
        }

        return "redirect:/";
    }
}
