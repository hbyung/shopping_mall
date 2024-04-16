package study.shopping_mall.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInquire is a Querydsl query type for Inquire
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquire extends EntityPathBase<Inquire> {

    private static final long serialVersionUID = 279889488L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInquire inquire = new QInquire("inquire");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath InquireDate = createString("InquireDate");

    public final study.shopping_mall.entity.item.QItem item;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> LastModifiedDate = _super.LastModifiedDate;

    public final ListPath<InquireResponse, QInquireResponse> responses = this.<InquireResponse, QInquireResponse>createList("responses", InquireResponse.class, QInquireResponse.class, PathInits.DIRECT2);

    public final StringPath Writer = createString("Writer");

    public QInquire(String variable) {
        this(Inquire.class, forVariable(variable), INITS);
    }

    public QInquire(Path<? extends Inquire> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInquire(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInquire(PathMetadata metadata, PathInits inits) {
        this(Inquire.class, metadata, inits);
    }

    public QInquire(Class<? extends Inquire> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new study.shopping_mall.entity.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

