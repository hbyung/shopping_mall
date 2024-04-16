package study.shopping_mall.entity.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShoes is a Querydsl query type for Shoes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShoes extends EntityPathBase<Shoes> {

    private static final long serialVersionUID = 2036393552L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShoes shoes = new QShoes("shoes");

    public final QItem _super;

    //inherited
    public final ListPath<study.shopping_mall.entity.OrderCart, study.shopping_mall.entity.QOrderCart> cart;

    // inherited
    public final study.shopping_mall.entity.QCategory category;

    //inherited
    public final NumberPath<Integer> count;

    //inherited
    public final StringPath createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final ListPath<study.shopping_mall.entity.InquireResponse, study.shopping_mall.entity.QInquireResponse> inquireResponses;

    //inherited
    public final ListPath<study.shopping_mall.entity.Inquire, study.shopping_mall.entity.QInquire> inquires;

    //inherited
    public final StringPath itemTime;

    //inherited
    public final StringPath lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> LastModifiedDate;

    //inherited
    public final StringPath mainFileName;

    //inherited
    public final StringPath mainFilePath;

    //inherited
    public final ListPath<study.shopping_mall.entity.MultiFiles, study.shopping_mall.entity.QMultiFiles> multiFiles;

    //inherited
    public final StringPath name;

    //inherited
    public final NumberPath<Integer> price;

    //inherited
    public final ListPath<study.shopping_mall.entity.Reviews, study.shopping_mall.entity.QReviews> reviews;

    public final StringPath ShoesBrand = createString("ShoesBrand");

    public final StringPath shoesBrand = createString("shoesBrand");

    public final ComparablePath<java.util.UUID> ShoesCode = createComparable("ShoesCode", java.util.UUID.class);

    public final ComparablePath<java.util.UUID> shoesCode = createComparable("shoesCode", java.util.UUID.class);

    //inherited
    public final NumberPath<Integer> stockQuantity;

    public QShoes(String variable) {
        this(Shoes.class, forVariable(variable), INITS);
    }

    public QShoes(Path<? extends Shoes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShoes(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShoes(PathMetadata metadata, PathInits inits) {
        this(Shoes.class, metadata, inits);
    }

    public QShoes(Class<? extends Shoes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QItem(type, metadata, inits);
        this.cart = _super.cart;
        this.category = _super.category;
        this.count = _super.count;
        this.createBy = _super.createBy;
        this.createDate = _super.createDate;
        this.id = _super.id;
        this.inquireResponses = _super.inquireResponses;
        this.inquires = _super.inquires;
        this.itemTime = _super.itemTime;
        this.lastModifiedBy = _super.lastModifiedBy;
        this.LastModifiedDate = _super.LastModifiedDate;
        this.mainFileName = _super.mainFileName;
        this.mainFilePath = _super.mainFilePath;
        this.multiFiles = _super.multiFiles;
        this.name = _super.name;
        this.price = _super.price;
        this.reviews = _super.reviews;
        this.stockQuantity = _super.stockQuantity;
    }

}

