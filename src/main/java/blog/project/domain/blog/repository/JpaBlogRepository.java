package blog.project.domain.blog.repository;

import blog.project.domain.blog.BoardForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class JpaBlogRepository implements BlogRepository{

    private final EntityManager em;

    @Autowired
    public JpaBlogRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public BoardForm save(BoardForm boardForm) {
        em.persist(boardForm); //!!!!!!!!!!!!!!!!!!!!!!이부분이 문제 테이블이 두개 있는데 어떻게 구분해 ???? 관계를 없애야 되는데!!!!!??
        return boardForm;
    }

    @Override
    public List<BoardForm> findAll(String id) {
        List<BoardForm> boards = em.createQuery("select b from BOARD b where b.name=:id ", BoardForm.class)
                .setParameter("id" , id)
                .getResultList();
        return boards;
    }

    @Override
    public BoardForm findById(Long id) {
        BoardForm boardForm = em.find(BoardForm.class, id); //"PK"로 찾을때
        return boardForm;
    }


    @Override
    public BoardForm update(Long id, BoardForm boardForm) {

        BoardForm find = em.find(BoardForm.class, id);
        find.setTitle(boardForm.getTitle());
        find.setName(boardForm.getName());
        find.setContext(boardForm.getContext());

        return find;
    }


    @Override
    public BoardForm delete(Long id) {
        BoardForm findBoard = em.find(BoardForm.class, id);
        em.remove(findBoard);

        return findBoard;
    }

}
