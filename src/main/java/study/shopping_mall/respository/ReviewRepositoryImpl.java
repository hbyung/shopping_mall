package study.shopping_mall.respository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import study.shopping_mall.entity.Reviews;

import java.util.List;

import static study.shopping_mall.entity.QReviews.reviews;
import static study.shopping_mall.entity.item.QItem.item;

public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Reviews> findAllReview(Long itemId) {
        return queryFactory
                .selectFrom(reviews)
                .join(reviews.item, item)
                .where(IdLike(itemId))
                .limit(10)
                .fetch();
    }

    private BooleanExpression IdLike(Long itemId) {
        if (itemId == null) {

            return null;
        }
        return reviews.item.id.eq(itemId);
    }
}
