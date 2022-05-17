package blog.project.domain.blog.service;

import blog.project.domain.blog.BoardForm;

import java.util.List;

public interface BoardService {

    public BoardForm registration(BoardForm boardForm);
    public List<BoardForm> boards(String id);
    public BoardForm search(Long id); //pk로 조회
    public BoardForm updateBoard(Long id, BoardForm boardForm);
    public BoardForm deleteBoard(Long id);

}
