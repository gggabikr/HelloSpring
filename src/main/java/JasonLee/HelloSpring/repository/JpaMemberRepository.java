package JasonLee.HelloSpring.repository;

import JasonLee.HelloSpring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        //persist는 영속하다, 영구저장하다 뭐 그런뜻.
        //이것만 해주면 끝. 아래 리턴 값은 그냥 이 메소드의 리턴 밸류를 맞춰주기 위한것일뿐.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        //em.find에 조회할 타입이랑, 식별자 PK(Primary Key)값만 넣어주면 끝.
        return Optional.ofNullable(member);
        //Optional로 받아야하는데, 값이 없을 수도 있고 하니까 이렇게.
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        //이렇게 리스트형태로 나오는데(결과가 하나가 아닐 수 있으니까.),
        // 이 메소드는 하나만 찾는거니까 이렇게 result 변수로 빼서
        //아래처럼 findAny 로 해주면 된다.

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
