package JasonLee.HelloSpring.controller;

import JasonLee.HelloSpring.domain.Member;
import JasonLee.HelloSpring.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //private final memberService memberService = new memberService();
    //위와 같이 그냥 new로 만들수도 있기는 하지만 스프링이 관리를 하게되면 다 스프링 컨테이너에 등록을하고
    //스프링 컨테이너로부터 받아서 쓰도록 바꿔야하는게 원칙.
    private final memberService memberService;

    @Autowired
    //생성자가 하나뿐이면 위에 Autowired 태그는 사실 없어도 된다.
    //이 태그가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. 이렇게 객체 의존 관계를 외부에서 넣어주는 것을 DI라고 한다.
    //이전 테스트에서는 개발자가 직접 주입했고, 여기서는 @Autowired에 의해 스프링이 주입해준다.
    public MemberController(JasonLee.HelloSpring.service.memberService memberService) {
        this.memberService = memberService;
//        System.out.println(memberService.getClass());
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
        //redirect to 경로 '/' = 홈화면으로 보내겠다는 소리
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
