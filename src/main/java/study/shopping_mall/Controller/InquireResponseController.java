package study.shopping_mall.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import study.shopping_mall.dto.InquireResponseDTO;
import study.shopping_mall.service.InquireResponseService;

@Controller
@RequiredArgsConstructor
public class InquireResponseController {
    private final InquireResponseService inquireResponseService;

    @PostMapping("/user/inquireResponse")
    public String InquireResponse(@ModelAttribute("InquireResponseDTO")InquireResponseDTO inquireResponseDTO){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username.equals("anonymousUser")){

        }
        long id = inquireResponseDTO.getItemId();
        inquireResponseService.InquireResponse(id, inquireResponseDTO.getInquireId(), username, inquireResponseDTO.getProductResponse());
        return "redirect:/item/"+id+"/detail";
    }
}
