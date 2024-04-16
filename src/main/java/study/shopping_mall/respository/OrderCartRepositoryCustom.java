package study.shopping_mall.respository;

import com.querydsl.core.Tuple;
import study.shopping_mall.entity.OrderCart;

import java.util.List;

public interface OrderCartRepositoryCustom {
    List<OrderCart> findCartAll(String memberName);
    OrderCart findMemberItemId(long memberId, long itemId);
}
