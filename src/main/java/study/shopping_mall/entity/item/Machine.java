package study.shopping_mall.entity.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@DiscriminatorValue("M")
@Getter @Setter
public class Machine extends Item{

    private UUID MachineCode;
    private String MachineBrand;
}
