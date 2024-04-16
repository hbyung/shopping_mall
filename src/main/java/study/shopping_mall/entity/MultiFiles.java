package study.shopping_mall.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.shopping_mall.entity.item.Item;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Setter @Getter
@NoArgsConstructor
public class MultiFiles  {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "multiFiles_id")
    private long id;
    private String fileName;
    private String filePath;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public MultiFiles(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
