package study.shopping_mall.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shopping_mall.entity.OrderCart;

public interface OrderCartRepository extends JpaRepository<OrderCart, Long>, OrderCartRepositoryCustom {
    OrderCart findById(long id);
    OrderCart findByitemName(String itemName);
}
