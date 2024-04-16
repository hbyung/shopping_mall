package study.shopping_mall.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.shopping_mall.dto.InquireDto;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.respository.ItemRepository;
import study.shopping_mall.service.InquireService;

@Controller
@RequiredArgsConstructor
public class InquireController {

    private final InquireService inquireService;

    @GetMapping("/user/request")
    public String Inquire(@RequestParam("itemId")Long itemId, Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username.equals("anonymousUser")){

        }
        model.addAttribute("itemId", itemId);
        model.addAttribute("writer",username);

        return "/Item/itemReviewsForm.html";
    }

    @PostMapping("/user/reviewRequest")
    public String Inquire(@ModelAttribute("InquireDto") InquireDto inquireDto, Model model){
        String content = inquireDto.getContent();
        long id = inquireDto.getItemId();
        if (content.equals("")){
            model.addAttribute("message","내용을 입력해주세요.");
            model.addAttribute("searchUrl", "redirect:/user/"+id+"/detail");
            return "Member/errorPage";
        }
        inquireService.Inquire(inquireDto.getItemId(), inquireDto.getWriter(), content);
        return "redirect:/item/"+id+"/detail";
    }
}
