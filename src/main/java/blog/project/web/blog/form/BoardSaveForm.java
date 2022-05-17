package blog.project.web.blog.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardSaveForm {

    @NotEmpty
    private String name;

    @NotEmpty
    //@Max(value=20)
    private String title;

    @NotEmpty
    private String context;
}
