package study.shopping_mall.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.shopping_mall.entity.Address;
import study.shopping_mall.entity.Member;
import study.shopping_mall.entity.OrderCart;
import study.shopping_mall.entity.item.Book;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.respository.OrderCartRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderCartServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderCartService orderCartService;

    @Autowired
    OrderCartRepository orderCartRepository;


    //장바구니 추가
    @Test
    void addCart() {
        //given
        Member member = createMember();
        Item book = createBook("kim",10000,10);
        int count = 3;

        //when
        Long addCart = orderCartService.addCart(member, book, count);
        OrderCart id = orderCartService.findById(addCart);

        //then
        assertEquals(3,id.getCount());
    }

    //이미 장바구니 존재할경우 수량만 추가
    @Test
    void additionCart() {
        //given
        Member member = createMember();
        Item book = createBook("kim",10000,10);
        int count = 3;

        //when
        Long addCart = orderCartService.addCart(member, book, count);
        OrderCart memberItemId = orderCartService.findMemberItemId(member.getId(), book.getId());
        orderCartService.additionCart(memberItemId, book, count);

        OrderCart id = orderCartService.findById(addCart);

        //수량 3개에서 3개늘려서 = 6
        //then
        assertEquals(6,id.getCount());
    }


    //회원 이름인걸로 장바구니 목록 불러오기
    @Test
    void findCartAll() {
        //given
        Member member = createMember();
        Item book = createBook("kim",10000,10);
        int count = 3;

        //when
        Long addCart = orderCartService.addCart(member, book, count);
        String username = "회원1";
        String name = null;
        List<OrderCart> cartAll = orderCartService.findCartAll(username);
        long count1 = cartAll.stream().count();

        for (OrderCart orderCart : cartAll) {
            name = orderCart.getItemName();
        }

        OrderCart id = orderCartService.findById(addCart);

        //then
        assertEquals(1,count1);
        assertEquals(name, id.getItemName());
    }

    //장바구니 취소
    @Test
    void cancelCart() {
        //given
        Member member = createMember();
        Item book = createBook("kim",10000,10);
        int count = 3;

        //when
        Long addCart = orderCartService.addCart(member, book, count);


        OrderCart id = orderCartService.findById(addCart);
        id.removeCartCount();

        //then
        assertEquals(0, id.getCount());

    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        book.setMainFilePath("1234.jpg");
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setUsername("회원1");
        member.setAddress(new Address("서울", "경기", "123-123"));
        em.persist(member);
        return member;
    }
}