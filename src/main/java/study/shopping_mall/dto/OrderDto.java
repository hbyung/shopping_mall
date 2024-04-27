package study.shopping_mall.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.shopping_mall.entity.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class OrderDto {

    private Long orderId;
    private String name;
    private List<OrderItemDto> orderItems;
    private Address address;
    private String orderDate;
    private OrderStatus status;

    @QueryProjection
    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.name = order.getMember().getUsername(); //lazy 초기화
        this.orderItems = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemDto(orderItem))
                .collect(toList());
        this.address = order.getDelivery().getAddress(); //lazy 초기화
        this.orderDate = order.getOrderDate();
        this.status = order.getStatus();
    }
}
