package study.shopping_mall.respository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import study.shopping_mall.dto.OrderDto;
import study.shopping_mall.dto.OrderSearch;
import study.shopping_mall.dto.QOrderDto;
import study.shopping_mall.entity.*;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.entity.item.QItem;

import java.util.List;

import static study.shopping_mall.entity.QMember.member;
import static study.shopping_mall.entity.QOrder.order;
import static study.shopping_mall.entity.QOrderItem.orderItem;
import static study.shopping_mall.entity.item.QItem.item;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<Order> findOrderList(Pageable pageable, OrderSearch orderSearch, String username) {

        QueryResults<Order> results = queryFactory
                .select(order)
                .from(order)
                .join(order.orderItems, orderItem)
                .where(usernameLike(username),statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getItemName()))
                .orderBy(
                      order.orderDate.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Order> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }


    @Override
    public Page<OrderDto> findOrderRestList(Pageable pageable, OrderSearch orderSearch, String username) {

        QueryResults<OrderDto> results = queryFactory
                .select(new QOrderDto(order))
                .from(order)
                .join(order.orderItems, orderItem)
                .where(usernameLike(username),statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getItemName()))
                .orderBy(
                        order.orderDate.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<OrderDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);

    }


    private BooleanExpression usernameLike(String username) {
        if (!StringUtils.hasText(username)) {

            return null;
        }
        return order.member.username.contains(username);
    }


    private BooleanExpression nameLike(String itemName) {
        if (!StringUtils.hasText(itemName)) {

            return null;
        }
        return orderItem.item.name.contains(itemName);
    }

    private BooleanExpression statusEq(OrderStatus statusCond) {
        if (statusCond == null) {
            return null;
        }
        return order.status.eq(statusCond);
    }
}
