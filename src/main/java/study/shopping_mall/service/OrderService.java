package study.shopping_mall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.shopping_mall.dto.OrderDto;
import study.shopping_mall.dto.OrderSearch;
import study.shopping_mall.entity.Delivery;
import study.shopping_mall.entity.Member;
import study.shopping_mall.entity.Order;
import study.shopping_mall.entity.OrderItem;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.respository.ItemRepository;
import study.shopping_mall.respository.MemberRepository;
import study.shopping_mall.respository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public Long Order(long memberId, long itemId, int count) {

        Member member = memberRepository.findById(memberId);
        Item item = itemRepository.findById(itemId);
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        if (item.getStockQuantity() < count){
            return 0l;
        }
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member,delivery,orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    public Page<Order> findList(Pageable pageable, OrderSearch orderSearch, String username){
        Page<Order> orderList = orderRepository.findOrderList(pageable, orderSearch, username);
        return orderList;
    }

    public Page<OrderDto> findRestList(Pageable pageable, OrderSearch orderSearch, String username){
        Page<OrderDto> orderRestList = orderRepository.findOrderRestList(pageable, orderSearch, username);
        return orderRestList;
    }

    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalAccessError::new);

        order.cancel();
    }



}
