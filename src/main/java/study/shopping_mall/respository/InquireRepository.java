package study.shopping_mall.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shopping_mall.entity.Inquire;


public interface InquireRepository extends JpaRepository<Inquire, Long>, InquireRepositoryCustom {
    Inquire findById(long id);
}
