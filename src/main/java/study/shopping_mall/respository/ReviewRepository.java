package study.shopping_mall.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shopping_mall.entity.Reviews;

public interface ReviewRepository extends JpaRepository<Reviews, Long>, ReviewRepositoryCustom {

}
