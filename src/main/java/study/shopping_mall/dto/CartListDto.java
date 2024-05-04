package study.shopping_mall.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CartListDto {

    private long cartId;
    private String itemName;
    private int itemPrice;
    private int number;

    public CartListDto(long cartId, String itemName, int itemPrice, int number) {
        this.cartId = cartId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.number = number;
    }
}
