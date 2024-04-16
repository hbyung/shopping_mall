package study.shopping_mall.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMultiFiles is a Querydsl query type for MultiFiles
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMultiFiles extends EntityPathBase<MultiFiles> {

    private static final long serialVersionUID = 50852609L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMultiFiles multiFiles = new QMultiFiles("multiFiles");

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final study.shopping_mall.entity.item.QItem item;

    public QMultiFiles(String variable) {
        this(MultiFiles.class, forVariable(variable), INITS);
    }

    public QMultiFiles(Path<? extends MultiFiles> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMultiFiles(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMultiFiles(PathMetadata metadata, PathInits inits) {
        this(MultiFiles.class, metadata, inits);
    }

    public QMultiFiles(Class<? extends MultiFiles> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new study.shopping_mall.entity.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

