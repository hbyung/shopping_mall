package study.shopping_mall.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import study.shopping_mall.entity.OrderCart;

@Data
public class RestCarDto {

    private Long CartId;
    private String itemName;
    private int itemPrice;
    private int count;
    private String mainFilePath;
    private String name;

    @QueryProjection
    public RestCarDto(OrderCart orderCart) {
        CartId = orderCart.getId();
        this.itemName = orderCart.getItem().getName();
        this.itemPrice = orderCart.getItemPrice();
        this.count = orderCart.getCount();
        this.mainFilePath = orderCart.getMainFilePath();
        this.name = orderCart.getMember().getUsername();
    }
}
