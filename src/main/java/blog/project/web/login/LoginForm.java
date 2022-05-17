package blog.project.web.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty
    private String member_id;
    @NotEmpty
    private String member_password;
}

