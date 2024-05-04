package study.shopping_mall.Controller;

import com.querydsl.core.Tuple;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.shopping_mall.dto.CartDto;
import study.shopping_mall.dto.CartListDto;
import study.shopping_mall.dto.ItemListSearch;
import study.shopping_mall.dto.addressDto;
import study.shopping_mall.entity.Address;
import study.shopping_mall.entity.Member;
import study.shopping_mall.entity.OrderCart;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.service.ItemService;
import study.shopping_mall.service.MemberService;
import study.shopping_mall.service.OrderCartService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final OrderCartService orderCartService;
    private final ItemService itemService;
    private final MemberService memberService;

    //장바구니 추가
    @GetMapping("/user/cart")
    public @ResponseBody String addCart(@RequestParam("itemId") Long itemId, @RequestParam("num")int count){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username.equals("anonymousUser")){

        }

        if (memberService.findByName(username) == null){
            Member name = memberService.findByUsername(username);
            Item item = itemService.findById(itemId);
            OrderCart cart_duplication =  orderCartService.findMemberItemId(name.getId(), item.getId());
            System.out.println("cart_duplication = " + cart_duplication);
            if (cart_duplication != null){
                if (name.getId() ==  cart_duplication.getMember().getId()){
                    orderCartService.additionCart(cart_duplication, item, count);
                    return "요청 성공";
                }
            }
            orderCartService.addCart(name, item, count);

            return "요청 성공";

        }else {
            Member name = memberService.findByName(username);
            Item item = itemService.findById(itemId);
            OrderCart cart_duplication =  orderCartService.findMemberItemId(name.getId(), item.getId());
            System.out.println("cart_duplication = " + cart_duplication);
            if (cart_duplication != null){
                if (name.getId() ==  cart_duplication.getMember().getId()){
                    orderCartService.additionCart(cart_duplication, item, count);
                    return "요청 성공";
                }
            }
            orderCartService.addCart(name, item, count);
            return "요청 성공";
        }

    }

    //장바구니 목록
    @GetMapping("/cartList")
    public String cartList(Model model, @ModelAttribute("ItemListSearch") ItemListSearch itemListSearch, HttpSession session){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        session.setAttribute("username",username);
        List<OrderCart> carts = orderCartService.findCartAll(username);
        List<OrderCart> cartAll = orderCartService.findCartAll(username);
        model.addAttribute("carts",carts);
        model.addAttribute("cartAll", cartAll);
        return "cart/shoppingcart1";
    }



    //장바구니 결제창
    @GetMapping("/cart/Order")
    public String createCartOrder(@ModelAttribute("CartDTO")CartDto cartDto, @RequestParam("finalTotalPrice") int finalTotalPrice, Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username.equals("anonymousUser")){

        }

        List<CartListDto> cartList = getListDtoList(cartDto);
        Member member = memberService.findByUsername(username);

        model.addAttribute("carts", cartList);
        model.addAttribute("member", member);
        model.addAttribute("finalTotalPrice", finalTotalPrice);

        return "/cart/CartOrderForm";
    }

    private List<CartListDto> getListDtoList(CartDto cartDto) {
        List<CartListDto> cart = new ArrayList<>();

        for (int i = 0; i < cartDto.getItemName().size(); i++){
            CartListDto cartListDto = new CartListDto(cartDto.getCartId().get(i), cartDto.getItemName().get(i), cartDto.getItemPrice().get(i), cartDto.getNumber().get(i));
            cart.add(cartListDto);
        }
        return cart;
    }


    //장바구니 주문
    @PostMapping("/cart/orders")
    public String CartOrder(@ModelAttribute("addressDto") addressDto addressDto,
                            @RequestParam("memberId")Long memberId,
                            @ModelAttribute("CartDTO")CartDto cartDto
                            ){
        Address address = new Address(addressDto.getPostcode(), addressDto.getAddress(), addressDto.getDetailAddress());
        Member member = memberService.findById(memberId);
        List<CartListDto> cartList = getListDtoList(cartDto);
        member.setAddress(address);

        orderCartService.Order(memberId, cartList);
        return "redirect:/";

    }


    //장바구니 취소
    @PostMapping(value = "/cart/{cartId}/cancel")
    public String CartCancel(@PathVariable("cartId") Long cartId) {
        orderCartService.deleteCart(cartId);
        return "redirect:/cartList";
    }
}
