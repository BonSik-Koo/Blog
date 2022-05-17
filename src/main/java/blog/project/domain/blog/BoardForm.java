package blog.project.domain.blog;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "BOARD") //테이블 명이자 Entity 객체 이름
public class BoardForm {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //회원 아이디
    private String title; //제목
    private String context; //내용

    public BoardForm() {}

    public BoardForm(String name, String title, String context) {
        this.title = title;
        this.name = name;
        this.context = context;
    }
}
