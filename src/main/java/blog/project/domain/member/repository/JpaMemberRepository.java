package blog.project.domain.member.repository;

import blog.project.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member Save_Member(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findMemberId(String id) {
        List<Member> result = em.createQuery("select m from Member m where m.member_id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }
}
