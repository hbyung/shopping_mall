package study.shopping_mall;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.shopping_mall.entity.Member;
import study.shopping_mall.respository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class ShoppingMallApplicationTests {

	@Autowired
	EntityManager em;
	@Autowired
	MemberRepository memberRepository;

	@Test
	void contextLoads() {
	}







}
