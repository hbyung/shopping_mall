package study.shopping_mall.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.shopping_mall.respository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional()
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;


    //Auditing 사용확인
    @Test
    public void JpaEventBaseEntity() throws Exception {
      //given
        Member member = new Member("member1");
        memberRepository.save(member); //PrePersist

        Thread.sleep(100);
        member.setUsername("member2");

        em.flush();
        em.clear();

        //when
        Member member1 = memberRepository.findById(member.getId());

        //then
        System.out.println("member1.getCreateDate() = " + member1.getCreateDate());
        System.out.println("member1.getUpdateDate() = " + member1.getLastModifiedDate());
        System.out.println("member1.getCreateBy() = " + member1.getCreateBy());
        System.out.println("member1.getLastModifiedBy() = " + member1.getLastModifiedBy());
    }

}