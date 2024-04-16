package study.shopping_mall.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shopping_mall.entity.InquireResponse;


public interface inquireResponseRepository extends JpaRepository<InquireResponse, Long>, inquireResponseRepositoryCustom {
}
