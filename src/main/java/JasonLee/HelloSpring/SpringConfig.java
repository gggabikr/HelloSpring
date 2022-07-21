package JasonLee.HelloSpring;

import JasonLee.HelloSpring.repository.MemberRepository;
import JasonLee.HelloSpring.repository.MemoryMemberRepository;
import JasonLee.HelloSpring.service.memberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public memberService memberService(){
        return new memberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
