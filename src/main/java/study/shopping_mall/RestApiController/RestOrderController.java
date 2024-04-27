package study.shopping_mall.RestApiController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.shopping_mall.dto.OrderDto;
import study.shopping_mall.dto.OrderSearch;
import study.shopping_mall.dto.addressDto;
import study.shopping_mall.entity.Address;
import study.shopping_mall.entity.Member;
import study.shopping_mall.service.MemberService;
import study.shopping_mall.service.OrderService;

@RestController
@RequiredArgsConstructor
public class RestOrderController {

    private final OrderService orderService;
    private final MemberService memberService;


    //주문
    @PostMapping("/v1/order")
    public String order(@RequestParam("itemId")Long itemId,
                        @RequestParam("memberId")Long memberId,
                        @RequestParam("num")int count,
                        @ModelAttribute("addressDto") addressDto addressDto, Model model) {
        Address address = new Address(addressDto.getPostcode(), addressDto.getAddress(), addressDto.getDetailAddress());
        Member member = memberService.findById(memberId);
        member.setAddress(address);

        Long order = orderService.Order(memberId, itemId, count);

        if (order == 0l){
            return "재고가 부족합니다.";
        }

        return order + " = 주문번호, 주문 완료 ";
    }


    //주문 목록
    @GetMapping("/v1/orders")
    public Page<OrderDto> orderList(@PageableDefault(size = 1) Pageable pageable, @ModelAttribute("orderSearch") OrderSearch orderSearch, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Page<OrderDto> orderRestList = orderService.findRestList(pageable, orderSearch, username);
        return orderRestList;
    }

    //주문 취소
    @PostMapping(value = "/v1/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {

        orderService.cancelOrder(orderId);
        return orderId + " = 주문번호, 취소 완료";
    }
}
