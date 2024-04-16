package study.shopping_mall.dto;

import lombok.Getter;
import lombok.Setter;
import study.shopping_mall.entity.OrderStatus;

@Getter @Setter
public class OrderSearch {

    private String itemName;
    public OrderStatus orderStatus;
}
