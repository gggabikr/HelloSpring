package JasonLee.HelloSpring.repository;
import JasonLee.HelloSpring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //Optional은 자바 8에서 생긴 기능.
    //요즘은 값이 없을때는 null이 나오는데 null을 그대로 반환하기보다는 옵셔널로 감싸서 반환하는 방식을 선호한다.
    List<Member> findAll();
}
