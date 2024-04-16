package study.shopping_mall.entity.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@DiscriminatorValue("F")
@Getter @Setter
public class Food extends Item {

    private UUID FoodCode;
    private String FoodBrand;

}
