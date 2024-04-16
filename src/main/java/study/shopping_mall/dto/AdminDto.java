package study.shopping_mall.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;
import study.shopping_mall.entity.Category;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AdminDto {

    @NotEmpty(message = "이름은 필수입니다.")
    private String name;
    @Min(value = 1)
    private int price;
    @Min(value = 1)
    private int stockQuantity;
    private String list;
    private String author;
    private UUID isbn;
    private UUID FoodCode;
    private String FoodBrand;
    private UUID MachineCode;
    private String MachineBrand;
    private UUID ShoesCode;
    private String ShoesBrand;

}
