package blog.project.domain.blog.service;

import blog.project.domain.blog.BoardForm;
import blog.project.domain.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BoardFormServiceImpl implements BoardService {

    private final BlogRepository blogRepository;

    @Autowired
    public BoardFormServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BoardForm registration(BoardForm boardForm) {
        blogRepository.save(boardForm);
        return boardForm;
    }

    @Override
    public List<BoardForm> boards(String id) {
        List<BoardForm> boards = blogRepository.findAll(id);
        return boards;
    }

    @Override
    public BoardForm search(Long id) {
        BoardForm board = blogRepository.findById(id);
        return board;
    }

    @Override
    public BoardForm updateBoard(Long id, BoardForm boardForm) {
        BoardForm updateBoard = blogRepository.update(id, boardForm);
        return updateBoard;
    }

    @Override
    public BoardForm deleteBoard(Long id) {
        BoardForm delete = blogRepository.delete(id);
        return delete;
    }
}
