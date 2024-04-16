package study.shopping_mall.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class itemDto {
    private Long itemId;
    private String mainFilePath;
    private String name;
    private int stockQuantity;
    private String itemTime;
    private String categoryName;

    @QueryProjection
    public itemDto(Long itemId, String mainFilePath, String name, int stockQuantity, String itemTime, String categoryName) {
        this.itemId = itemId;
        this.mainFilePath = mainFilePath;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.itemTime = itemTime;
        this.categoryName = categoryName;
    }
}
