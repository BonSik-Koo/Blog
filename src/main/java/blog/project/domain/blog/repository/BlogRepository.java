package blog.project.domain.blog.repository;

import blog.project.domain.blog.BoardForm;

import java.util.List;

public interface BlogRepository {

    public BoardForm save(BoardForm boardForm);
    public List<BoardForm> findAll(String id); //회원의 아이디로 검색
    public BoardForm findById(Long id); //"PK"로 검색
    public BoardForm update(Long id, BoardForm boardForm);
    public BoardForm delete(Long id);


}
