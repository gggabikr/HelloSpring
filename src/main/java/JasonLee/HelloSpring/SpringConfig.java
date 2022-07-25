package JasonLee.HelloSpring;

import JasonLee.HelloSpring.repository.JdbcMemberRepository;
import JasonLee.HelloSpring.repository.MemberRepository;
import JasonLee.HelloSpring.repository.MemoryMemberRepository;
import JasonLee.HelloSpring.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public memberService memberService(){
        return new memberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);

    }
}
