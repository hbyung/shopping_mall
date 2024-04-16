package study.shopping_mall.entity.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@DiscriminatorValue("S")
@Getter @Setter
public class Shoes extends Item {

    private UUID ShoesCode;
    private String ShoesBrand;
}
