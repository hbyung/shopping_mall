package study.shopping_mall.RestApiController;


import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import study.shopping_mall.dto.MemberDto;
import study.shopping_mall.service.MemberService;

@RestController
@RequiredArgsConstructor
public class RestMemberController {

    private final MemberService memberService;


    //회원가입
    @PostMapping("/v1/Member/new")
    public String joinMember(@ModelAttribute("MemberDto")MemberDto memberDto, Model model){

        if (memberDto.getPassword().equals(memberDto.getRepassword())){
            long joinProcess = memberService.joinProcess(memberDto);
            if (joinProcess == 0){
                model.addAttribute("message","중북된 회원입니다.");
                model.addAttribute("searchUrl","/Member/new");
                return "Member/errorPage";
            }
            return "Member/login";
        }else {
            model.addAttribute("message","비밀번호 확인과 일치 하여야합니다.");
            model.addAttribute("searchUrl","/");
            return "Member/errorPage";
        }

    }

}
