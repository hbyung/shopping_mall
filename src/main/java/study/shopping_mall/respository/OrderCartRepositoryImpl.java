package study.shopping_mall.respository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.util.StringUtils;
import study.shopping_mall.entity.OrderCart;
import study.shopping_mall.entity.QMember;
import study.shopping_mall.entity.QOrderCart;

import java.util.List;

import static study.shopping_mall.entity.QMember.member;
import static study.shopping_mall.entity.QOrderCart.orderCart;
import static study.shopping_mall.entity.QOrderItem.orderItem;
import static study.shopping_mall.entity.item.QItem.item;

public class OrderCartRepositoryImpl implements OrderCartRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OrderCartRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<OrderCart> findCartAll(String memberName) {
        List<OrderCart> carts = queryFactory
                .selectFrom(orderCart)
                .leftJoin(orderCart.member, member)
                .where(nameLike(memberName))
                .fetch();
        return carts;
    }

    @Override
    public OrderCart findMemberItemId(long memberId, long itemId) {
        return  queryFactory
                .selectFrom(orderCart)
                .join(orderCart.member, member)
                .where(memberId(memberId), itemId(itemId))
                .fetchOne();
    }

    private BooleanExpression nameLike(String memberName) {
        if (!StringUtils.hasText(memberName)) {

            return null;
        }
        return member.username.contains(memberName);
    }

    private BooleanExpression memberId(long id) {

        return orderCart.member.id.eq(id);
    }

    private BooleanExpression itemId(long id) {

        return orderCart.item.id.eq(id);
    }


}
