package study.shopping_mall.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import study.shopping_mall.dto.MemberDto;
import study.shopping_mall.entity.Member;
import study.shopping_mall.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginpage(){
        return "Member/login";
    }

    @GetMapping("/Member/new")
    public String joinMember(Model model) {
        model.addAttribute("MemberDto", new MemberDto());
        return "Member/MemberJoin";
    }

    @PostMapping("/Member/new")
    public String joinMember(@ModelAttribute("MemberDto") @Valid MemberDto memberDto, BindingResult result, Model model){

        if (result.hasErrors()) {
            return "Member/MemberJoin";
        }

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
