package study.shopping_mall.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CartDto {
    private List<String> itemName;
    private List<Integer> itemPrice;
    private List<Integer> number;


}
