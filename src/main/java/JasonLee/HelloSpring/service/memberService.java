package JasonLee.HelloSpring.service;

import JasonLee.HelloSpring.domain.Member;
import JasonLee.HelloSpring.repository.MemberRepository;
import JasonLee.HelloSpring.repository.MemoryMemberRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Transactional
public class memberService {
    private final MemberRepository memberRepository;

    public memberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //같은 이름이있는 중복 회원X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        //result값이 있으면 해당 오류 발생. result가 Optional이기때문에 가능한 방법.
        //Optional이 아니면 if(result == !null)~~ 이렇게 가야한다.

        //그러나 굳이 result로 할필요없이 바로 하는것도 가능.


        //타임측정 로직
//        long start = System.currentTimeMillis();
//
//        try {
            validateDuplicateMember(member);//중복회원 검증
            memberRepository.save(member);
            return member.getId();
//        }finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = " + timeMs + "ms");
//        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }
    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
