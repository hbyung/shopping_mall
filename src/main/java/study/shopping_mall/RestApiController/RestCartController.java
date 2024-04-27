package study.shopping_mall.RestApiController;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import study.shopping_mall.dto.*;
import study.shopping_mall.entity.Address;
import study.shopping_mall.entity.Member;
import study.shopping_mall.entity.OrderCart;
import study.shopping_mall.service.MemberService;
import study.shopping_mall.service.OrderCartService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestCartController {

    private final OrderCartService orderCartService;
    private final MemberService memberService;


    //장바구니 목록
    @GetMapping("/v1/cartList")
    public List<RestCarDto> cartList(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<RestCarDto> carts = orderCartService.findRestCartAll(username);
        return carts;
    }

    //장바구니 주문
    @PostMapping("/v1/cart/orders")
    public String CartOrder(@ModelAttribute("addressDto") addressDto addressDto,
                            @RequestParam("memberId")Long memberId,
                            @ModelAttribute("CartDTO") CartDto cartDto
    ){
        Address address = new Address(addressDto.getPostcode(), addressDto.getAddress(), addressDto.getDetailAddress());
        Member member = memberService.findById(memberId);
        List<CartListDto> cartList = getListDtoList(cartDto);
        member.setAddress(address);

        Long order = orderCartService.Order(memberId, cartList);
        return "주문번호 = " +order +" 장바구니 주문완료";

    }

    //장바구니 취소
    @PostMapping(value = "/v1/cart/{cartId}/cancel")
    public String CartCancel(@PathVariable("cartId") Long cartId) {
        orderCartService.cancelCart(cartId);
        return "주문번호 = " + cartId +" 장바구니 주문취소";
    }


    private List<CartListDto> getListDtoList(CartDto cartDto) {
        List<CartListDto> cart = new ArrayList<>();

        for (int i = 0; i < cartDto.getItemName().size(); i++){
            String name = cartDto.getItemName().get(i);
            OrderCart orderCart = orderCartService.findByitemName(name);
            CartListDto cartListDto = new CartListDto(orderCart.getItemName(), orderCart.getItemPrice(), orderCart.getCount());
            cart.add(cartListDto);
        }
        return cart;
    }


}
