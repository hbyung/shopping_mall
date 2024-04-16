package study.shopping_mall.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderCart is a Querydsl query type for OrderCart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderCart extends EntityPathBase<OrderCart> {

    private static final long serialVersionUID = -291460885L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderCart orderCart = new QOrderCart("orderCart");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final study.shopping_mall.entity.item.QItem item;

    public final StringPath itemName = createString("itemName");

    public final NumberPath<Integer> itemPrice = createNumber("itemPrice", Integer.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> LastModifiedDate = _super.LastModifiedDate;

    public final StringPath mainFilePath = createString("mainFilePath");

    public final QMember member;

    public QOrderCart(String variable) {
        this(OrderCart.class, forVariable(variable), INITS);
    }

    public QOrderCart(Path<? extends OrderCart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderCart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderCart(PathMetadata metadata, PathInits inits) {
        this(OrderCart.class, metadata, inits);
    }

    public QOrderCart(Class<? extends OrderCart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new study.shopping_mall.entity.item.QItem(forProperty("item"), inits.get("item")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

