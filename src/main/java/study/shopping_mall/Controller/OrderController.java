package study.shopping_mall.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.shopping_mall.dto.ItemListSearch;
import study.shopping_mall.dto.OrderSearch;
import study.shopping_mall.dto.addressDto;
import study.shopping_mall.entity.*;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.service.ItemService;
import study.shopping_mall.service.MemberService;
import study.shopping_mall.service.OrderCartService;
import study.shopping_mall.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;
    private final MemberService memberService;
    private final OrderCartService orderCartService;


    //주문 폼
    @GetMapping("/order")
    public String createForm(@RequestParam("itemId") Long itemId,
                             @RequestParam("num") int count,
                             @RequestParam("totalprice") int totalprice,
                             Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username.equals("anonymousUser")){

        }
        Item items = itemService.findById(itemId);
        Member member = memberService.findByUsername(username);
        model.addAttribute("member", member);
        model.addAttribute("items", items);
        model.addAttribute("count", count);
        model.addAttribute("totalprice", totalprice);
        return "order/OrderForm";
    }


    //주문
    @PostMapping("/order")
    public String order(@RequestParam("itemId")Long itemId,
                        @RequestParam("memberId")Long memberId,
                        @RequestParam("num")int count,
                        @ModelAttribute("addressDto")addressDto addressDto, Model model) {
        Address address = new Address(addressDto.getPostcode(), addressDto.getAddress(), addressDto.getDetailAddress());
        Member member = memberService.findById(memberId);
        member.setAddress(address);

        Long order = orderService.Order(memberId, itemId, count);

        if (order == 0l){
            model.addAttribute("message","재고가 부족합니다..");
            model.addAttribute("searchUrl","redirect:/orders");
            return "Member/errorPage";
        }

        return "redirect:/orders";
    }


    //주문 목록
    @GetMapping("/orders")
    public String orderList(Pageable pageable, @ModelAttribute("orderSearch") OrderSearch orderSearch, Model model,
                            @ModelAttribute("ItemListSearch") ItemListSearch ItemListSearch, HttpSession session) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        session.setAttribute("username",username);
        Page<Order> orders = orderService.findList(pageable, orderSearch, username);

        long orderCount = orders.stream().count();
        List<OrderCart> cartAll = orderCartService.findCartAll(username);
        int startPage = 1;
        int endPage = orders.getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("orders", orders);
        model.addAttribute("orderCount", orderCount);
        model.addAttribute("cartAll", cartAll);

        return "order/OrderStaus";

    }


    //주문 취소
    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {

        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

}
