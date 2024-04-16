package study.shopping_mall.entity.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 203950763L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItem item = new QItem("item");

    public final study.shopping_mall.entity.QBaseEntity _super = new study.shopping_mall.entity.QBaseEntity(this);

    public final ListPath<study.shopping_mall.entity.OrderCart, study.shopping_mall.entity.QOrderCart> cart = this.<study.shopping_mall.entity.OrderCart, study.shopping_mall.entity.QOrderCart>createList("cart", study.shopping_mall.entity.OrderCart.class, study.shopping_mall.entity.QOrderCart.class, PathInits.DIRECT2);

    public final study.shopping_mall.entity.QCategory category;

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<study.shopping_mall.entity.InquireResponse, study.shopping_mall.entity.QInquireResponse> inquireResponses = this.<study.shopping_mall.entity.InquireResponse, study.shopping_mall.entity.QInquireResponse>createList("inquireResponses", study.shopping_mall.entity.InquireResponse.class, study.shopping_mall.entity.QInquireResponse.class, PathInits.DIRECT2);

    public final ListPath<study.shopping_mall.entity.Inquire, study.shopping_mall.entity.QInquire> inquires = this.<study.shopping_mall.entity.Inquire, study.shopping_mall.entity.QInquire>createList("inquires", study.shopping_mall.entity.Inquire.class, study.shopping_mall.entity.QInquire.class, PathInits.DIRECT2);

    public final StringPath itemTime = createString("itemTime");

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> LastModifiedDate = _super.LastModifiedDate;

    public final StringPath mainFileName = createString("mainFileName");

    public final StringPath mainFilePath = createString("mainFilePath");

    public final ListPath<study.shopping_mall.entity.MultiFiles, study.shopping_mall.entity.QMultiFiles> multiFiles = this.<study.shopping_mall.entity.MultiFiles, study.shopping_mall.entity.QMultiFiles>createList("multiFiles", study.shopping_mall.entity.MultiFiles.class, study.shopping_mall.entity.QMultiFiles.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final ListPath<study.shopping_mall.entity.Reviews, study.shopping_mall.entity.QReviews> reviews = this.<study.shopping_mall.entity.Reviews, study.shopping_mall.entity.QReviews>createList("reviews", study.shopping_mall.entity.Reviews.class, study.shopping_mall.entity.QReviews.class, PathInits.DIRECT2);

    public final NumberPath<Integer> stockQuantity = createNumber("stockQuantity", Integer.class);

    public QItem(String variable) {
        this(Item.class, forVariable(variable), INITS);
    }

    public QItem(Path<? extends Item> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItem(PathMetadata metadata, PathInits inits) {
        this(Item.class, metadata, inits);
    }

    public QItem(Class<? extends Item> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new study.shopping_mall.entity.QCategory(forProperty("category"), inits.get("category")) : null;
    }

}

