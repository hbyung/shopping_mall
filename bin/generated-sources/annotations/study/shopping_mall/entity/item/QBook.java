package study.shopping_mall.entity.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = 203737729L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBook book = new QBook("book");

    public final QItem _super;

    public final StringPath author = createString("author");

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

    public final ComparablePath<java.util.UUID> isbn = createComparable("isbn", java.util.UUID.class);

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

    //inherited
    public final NumberPath<Integer> stockQuantity;

    public QBook(String variable) {
        this(Book.class, forVariable(variable), INITS);
    }

    public QBook(Path<? extends Book> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBook(PathMetadata metadata, PathInits inits) {
        this(Book.class, metadata, inits);
    }

    public QBook(Class<? extends Book> type, PathMetadata metadata, PathInits inits) {
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

