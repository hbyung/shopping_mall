package study.shopping_mall.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CartListDto {

    private String itemName;
    private int itemPrice;
    private int number;

    public CartListDto(String itemName, int itemPrice, int number) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.number = number;
    }
}
