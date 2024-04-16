package study.shopping_mall.respository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import study.shopping_mall.entity.Inquire;
import study.shopping_mall.entity.QInquire;
import study.shopping_mall.entity.QInquireResponse;

import java.util.List;

import static study.shopping_mall.entity.QInquire.inquire;
import static study.shopping_mall.entity.QInquireResponse.inquireResponse;

public class InquireRepositoryCustomImpl implements InquireRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public InquireRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Inquire> findAllId(Long itemId) {
        return queryFactory
                .selectFrom(inquire)
                .leftJoin(inquire.responses, inquireResponse)
                .where(IdLike(itemId))
                .fetch();
    }

    private BooleanExpression IdLike(Long itemId) {
        if (itemId == null) {

            return null;
        }
        return inquire.item.id.eq(itemId);
    }
}
