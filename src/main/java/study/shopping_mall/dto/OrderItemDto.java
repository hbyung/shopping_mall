package study.shopping_mall.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import study.shopping_mall.entity.OrderItem;
import study.shopping_mall.entity.item.Item;

@Data
public class OrderItemDto {

    private String ItemName;
    private int orderPrice; // 주문가격
    private int count; //주문 수량

    @QueryProjection
    public OrderItemDto(OrderItem orderItem) {
        this.ItemName = orderItem.getItem().getName();
        this.orderPrice = orderItem.getItem().getPrice();
        this.count = orderItem.getCount();
    }
}
