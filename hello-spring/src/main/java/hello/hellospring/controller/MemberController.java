package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//"@Controller"를 보고 스프링이 뜰 때 MemberController 객체를 만들어서 스프링에 넣어둠
// 위와 같은 표현: 스프링 컨테이너에서 스프링 빈이 관리된다.
@Controller
public class MemberController {
    //memberService는 기능 별로 없으니까 그냥 스프링 컨테이너에 등록해놓고 공용으로 쓰자
    private final MemberService memberService;

    //스프링 컨테이너에서 memberService를 가져옴
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //url에 직접 치는거:get방식(or 데이터 조회)
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    //보통 form에 넣어서 데이터를 전달할 때 post방식을 사용
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }




}
