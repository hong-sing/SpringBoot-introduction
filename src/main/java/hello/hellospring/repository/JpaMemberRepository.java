package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // JPA는 EntityManager라는 것으로 모든게 동작함

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 이렇게 하면 JPA가 insert 쿼리를 다 만들어서 DB에 입력하고 id까지 member에 set한다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);// 조회. 조회할 타입과 식별자(pk)를 넣어준다.
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class)
                .getResultList();   // 보통 테이블 대상으로 sql을 작성하는데 객체(엔티티)를 대상으로 쿼리를 작성하는 것이다. 그러면 sql로 번역이 된다. (as 생략가능)
    }
}
