package study.shopping_mall.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.shopping_mall.dto.MemberDto;
import study.shopping_mall.entity.Member;
import study.shopping_mall.respository.MemberRepository;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
      //given
        MemberDto member = new MemberDto();
        member.setUsername("kim");
        member.setPassword("1234");
        member.setEmail("god0478@naver");
      //when
        memberService.joinProcess(member);
        Member id = memberRepository.findByUsername("kim");

        //then
        //memberDto 사용해서 entity 가 다름
//        assertEquals(member, memberRepository.findById(joinProcess));

        assertEquals(member.getUsername(), id.getUsername());
    }

    @Test
    public void 중복_회원_예외() throws  Exception {

        //given
        MemberDto member1 = new MemberDto();
        member1.setUsername("kim");
        member1.setPassword("1234");
        member1.setEmail("god0478@naver");

        MemberDto member2 = new MemberDto();
        member2.setUsername("kim");
        member2.setPassword("1234");
        member2.setEmail("god0478@naver.com");
        //when
        memberService.joinProcess(member1);
        memberService.joinProcess(member2);

        //then
        Member username = memberRepository.findByUsername("kim");
        String email = username.getEmail();
        assertEquals("god0478@naver", email);
//        fail("예외가 발생해야 된다.");

    }

}