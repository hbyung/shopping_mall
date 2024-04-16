package study.shopping_mall.entity.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import study.shopping_mall.entity.*;
import study.shopping_mall.exception.NotEnoghStockException;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String mainFileName;
    private String mainFilePath;
    private String itemTime;
    private int count;

    @OneToOne(cascade =CascadeType.ALL, fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    //연관 메서드

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderCart> cart = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Reviews> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Inquire> inquires = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<InquireResponse> inquireResponses = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<MultiFiles> multiFiles = new ArrayList<>();

    public void setCategory(Category category) {
        this.category = category;
        category.setItem(this);
    }

    public void addMultiFiles(MultiFiles multiFile) {
        multiFiles.add(multiFile);
        multiFile.setItem(this);
    }

    //==비지니스 로직==//

    // 재고 증가

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void  removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoghStockException("need mor stock");
        }
        this.stockQuantity = restStock;
    }
}