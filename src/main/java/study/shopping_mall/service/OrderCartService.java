package study.shopping_mall.service;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.shopping_mall.dto.CartListDto;
import study.shopping_mall.entity.*;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.exception.NotEnoghStockException;
import study.shopping_mall.respository.ItemRepository;
import study.shopping_mall.respository.MemberRepository;
import study.shopping_mall.respository.OrderCartRepository;
import study.shopping_mall.respository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderCartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderCartRepository orderCartRepository;
    private final OrderRepository orderRepository;

    public Long addCart(Member member, Item item, int count){
        OrderCart orderCart = OrderCart.createCart(member,item);
        orderCart.setMainFilePath(item.getMainFilePath());
        orderCart.setItemPrice(item.getPrice());
        orderCart.setItemName(item.getName());
        orderCart.setCount(count);
        orderCartRepository.save(orderCart);
        return orderCart.getId();
    }

    public Long additionCart(OrderCart cart_duplication, Item item, int count){
        cart_duplication.setCount(cart_duplication.getCount() + count);
        System.out.println("cart_duplication.getCount() = " + cart_duplication.getCount());
        orderCartRepository.save(cart_duplication);
        return cart_duplication.getId();
    }

    public OrderCart findById(long id){
        OrderCart findId = orderCartRepository.findById(id);

        return findId;
    }

    public  List<OrderCart> findAll(){
        List<OrderCart> carts = orderCartRepository.findAll();
        return carts;
    }

    public List<OrderCart>  findCartAll(String memberName){
        List<OrderCart>  cartAll = orderCartRepository.findCartAll(memberName);
        return cartAll;
    }

    public OrderCart findByitemName(String itemName){
        OrderCart byitemName = orderCartRepository.findByitemName(itemName);
        return byitemName;
    }


    public Long Order(long memberid, List<CartListDto> cartList) {
        Member member = memberRepository.findById(memberid);
        List<Item> itemList = new ArrayList<>();

        for (CartListDto cartListDto : cartList) {
            Item itemName = itemRepository.findByName(cartListDto.getItemName());
            itemName.setCount(cartListDto.getNumber());
            itemList.add(itemName);
        }

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        List<OrderItem> cartOrderItem = OrderItem.createCartOrderItem(itemList);
        Order order = Order.createCartOrder(member,delivery,cartOrderItem);

        orderRepository.save(order);
        for (CartListDto cartListDto : cartList) {
            OrderCart name = orderCartRepository.findByitemName(cartListDto.getItemName());
            name.removeCartCount();
        }
        return order.getId();

    }

    public void cancelCart(Long cartId) {
        OrderCart orderCart = orderCartRepository.findById(cartId).orElseThrow(IllegalArgumentException::new);
        orderCart.removeCartCount();
    }

    public OrderCart findMemberItemId(long memberId, Long itemId) {
        return orderCartRepository.findMemberItemId(memberId, itemId);
    }
}
