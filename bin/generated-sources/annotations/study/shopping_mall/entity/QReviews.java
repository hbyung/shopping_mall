package study.shopping_mall.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviews is a Querydsl query type for Reviews
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviews extends EntityPathBase<Reviews> {

    private static final long serialVersionUID = -575917896L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviews reviews = new QReviews("reviews");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final study.shopping_mall.entity.item.QItem item;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> LastModifiedDate = _super.LastModifiedDate;

    public final StringPath reviewDate = createString("reviewDate");

    public final StringPath Writer = createString("Writer");

    public final StringPath WriterReview = createString("WriterReview");

    public QReviews(String variable) {
        this(Reviews.class, forVariable(variable), INITS);
    }

    public QReviews(Path<? extends Reviews> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviews(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviews(PathMetadata metadata, PathInits inits) {
        this(Reviews.class, metadata, inits);
    }

    public QReviews(Class<? extends Reviews> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new study.shopping_mall.entity.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

