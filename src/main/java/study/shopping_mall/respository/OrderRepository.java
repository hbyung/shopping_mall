package study.shopping_mall.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shopping_mall.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
