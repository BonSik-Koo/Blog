package blog.project.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String homeLogin(@SessionAttribute(value = "loginId", required = false) String member_id, Model model) {

        //로그인 하지 않은 사용자
        if(member_id==null) {
            return "home";
        }

        model.addAttribute("member_id", member_id);
        return "loginHome";

    }
}
