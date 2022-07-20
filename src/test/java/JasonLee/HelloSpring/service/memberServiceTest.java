package JasonLee.HelloSpring.service;

import JasonLee.HelloSpring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class memberServiceTest {

    memberService memberService = new memberService();


//    @Test
//    void join() {
//    }
//    위 코드가 자동생성됐지만 사실 테스트 코드에서는 아래와같이 한글로 이름을 지어도 무관하다.
//    같이 일하는 사람들이 한국인이라면 이렇게 하는편이 더 직관적일 수 있다.


    @Test
    void 회원가입(){
    //테스트코드는 거의 대부분 이렇게 세 파트로 나뉘어진다. 무언가가 주어졌을때, 어떤 상황에서, 어떻게 되야한다.
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
        //멤버를 한명 생성후 회원가입시킴. 처음만든 멤버와 회원가입으로 들어간 멤버의 이름이 같은지 확인.

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("Jason");

        Member member2 = new Member();
        member2.setName("Jason");

        //when
        memberService.join(member1);

        //방법1
//        try {
//            memberService.join(member2);
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.??");
//        }
        //방법2
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //오른쪽 파라미터인 람다함수가 돌면 왼쪽 파라미터인 에러가 발생해야한다 라는소리.
        //한단계 더 나아가서 메세지도 검증해볼 수 있다.
        //위 부분에서 command+option+v 를 누르면 아래처럼 만들어진다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");



        //then


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}