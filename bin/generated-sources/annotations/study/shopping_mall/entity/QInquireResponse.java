package study.shopping_mall.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInquireResponse is a Querydsl query type for InquireResponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquireResponse extends EntityPathBase<InquireResponse> {

    private static final long serialVersionUID = -1250103631L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInquireResponse inquireResponse = new QInquireResponse("inquireResponse");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInquire inquire;

    public final StringPath inquireResponseDateTime = createString("inquireResponseDateTime");

    public final study.shopping_mall.entity.item.QItem item;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> LastModifiedDate = _super.LastModifiedDate;

    public final StringPath Writer = createString("Writer");

    public QInquireResponse(String variable) {
        this(InquireResponse.class, forVariable(variable), INITS);
    }

    public QInquireResponse(Path<? extends InquireResponse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInquireResponse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInquireResponse(PathMetadata metadata, PathInits inits) {
        this(InquireResponse.class, metadata, inits);
    }

    public QInquireResponse(Class<? extends InquireResponse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inquire = inits.isInitialized("inquire") ? new QInquire(forProperty("inquire"), inits.get("inquire")) : null;
        this.item = inits.isInitialized("item") ? new study.shopping_mall.entity.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

