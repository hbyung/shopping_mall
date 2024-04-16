package study.shopping_mall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquireDto {
    private String writer;
    private String content;
    private long itemId;
}
