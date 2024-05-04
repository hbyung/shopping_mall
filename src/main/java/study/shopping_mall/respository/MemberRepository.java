package study.shopping_mall.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shopping_mall.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);
    Member findById(long id);
    Member findByEmail(String email);
    Boolean existsByUsername(String username);
    Member findByName(String name);
    Boolean existsByEmail(String email);
}
