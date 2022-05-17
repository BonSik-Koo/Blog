package blog.project.domain.member;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
//회원기본 가입정보

@Entity(name="Member")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String member_name;
    @NotEmpty
    private String member_id;
    @NotEmpty
    private String member_password;
    @NotNull
    private Integer member_birth;
    @NotEmpty
    private String member_address;

    public Member() {}

    public Member(String member_id, String member_password, String member_name, int member_birth, String member_address) {
        this.member_id = member_id;
        this.member_password = member_password;
        this.member_name= member_name;
        this.member_birth = member_birth;
        this.member_address = member_address;
    }
}
