package study.shopping_mall.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import study.shopping_mall.dto.reviewDto;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.service.ItemService;
import study.shopping_mall.service.ReviewService;

@Controller
@RequiredArgsConstructor
public class reviewController {

    private final ItemService itemService;
    private final ReviewService reviewService;

    //상품평 등록
    @GetMapping("/user/addreviews")
    public @ResponseBody String addreviews(@ModelAttribute("reviewDto")reviewDto reviewDto){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username.equals("anonymousUser")){
            return "로그인후 진행해주세요";
        }

        Item item = itemService.findById(reviewDto.getItemId());
        reviewService.SaveReivew(item, reviewDto.getReview(), username);
        return "상품평 등록 완료";
    }
}
