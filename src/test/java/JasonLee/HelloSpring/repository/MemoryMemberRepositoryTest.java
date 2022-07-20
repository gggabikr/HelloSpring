package JasonLee.HelloSpring.repository;

import JasonLee.HelloSpring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member));
//        위와 같이 테스트 결과를 출력하는것도 가능은 하다. 하지만 아래 방법이 더 좋다.

//        Assertions.assertEquals(member,result);
        //이걸 사용하려면 org.junit.jupiter.api.Assertions를 import해야함
        //이 방식을 사용하면 앞에는 기대값, 뒤쪽에는 실제값을 넣어서
        //실행 했을때 그 두 값이 같으면 실행창에 초록불이 뜨고, 아니면 빨간불이 뜬다.

        //또다른 방식으로는 org.assertj.core.api.Assertions를 import해서 쓰는게 있다.
//        Assertions.assertThat(member).isEqualTo(result);
        //사실 거의 같기는 한데 어떤게 실제값이고 어떤게 기대값인지가 명확하기때문에
        //가독성이 더 높기는 하다.

        //위쪽 어설션스 부분에서 option+enter하면 static import를 하는것도 가능한데,
        //이렇게 하면 Assertions없이 바로 assertThat으로 사용하는것도 가능하다.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //좀더 정확한 테스트를 위해서 멤버를 두명 추가해보았다.
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //위쪽 멤버1 세줄 복붙후 member1부분에서 shift+f6을 누르고 이름을 바꾸면
        //해당 변수이름이 들어간 부분이 모조리 한번에 바뀐다. ("spring1"부분은 직접해야함)

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
