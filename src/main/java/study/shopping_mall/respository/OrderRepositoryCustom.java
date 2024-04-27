package study.shopping_mall.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.shopping_mall.dto.OrderDto;
import study.shopping_mall.dto.OrderSearch;
import study.shopping_mall.entity.Order;
import study.shopping_mall.entity.OrderStatus;

public interface OrderRepositoryCustom {
    Page<Order> findOrderList(Pageable pageable, OrderSearch orderSearch, String username);
    Page<OrderDto> findOrderRestList(Pageable pageable, OrderSearch orderSearch, String username);
}
