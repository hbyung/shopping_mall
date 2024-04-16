package study.shopping_mall.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import study.shopping_mall.dto.ItemListSearch;
import study.shopping_mall.dto.OrderSearch;
import study.shopping_mall.entity.Address;
import study.shopping_mall.entity.Member;
import study.shopping_mall.entity.Order;
import study.shopping_mall.entity.OrderStatus;
import study.shopping_mall.entity.item.Book;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.respository.OrderRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;


    //주문하기
    @Test
    public void order() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook("jpa", 10000, 10);

        int orderCount = 2;

        //when
        Long OrderId = orderService.Order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findById(OrderId).orElseThrow(IllegalArgumentException::new);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(1,getOrder.getOrderItems().size());
        assertEquals(10000 * orderCount, getOrder.getTotalPrice());
        assertEquals(8, book.getStockQuantity());

    }

    //주문 목록 검색
    @Test
    void findList() {

        //given
        Member member = createMember();
        Book book = createBook("jpa", 10000, 10);

        int orderCount = 2;

        //when
        Long OrderId = orderService.Order(member.getId(), book.getId(), orderCount);

        em.flush();
        em.clear();

        Order order1 = orderRepository.findById(OrderId).get();
        System.out.println("order1.getMember().getName() = " + order1.getMember().getName());


        //then

        OrderSearch orderSearch = new OrderSearch();
        orderSearch.setOrderStatus(OrderStatus.ORDER);
        orderSearch.setItemName("jpa");
        String username = "회원1";
        Pageable pageable = PageRequest.of(0,10);
        Page<Order> list = orderService.findList(pageable, orderSearch, username);

        //갯수 확인
        long count = list.stream().count();
        System.out.println("count = " + count);


        //데이터 담기
        String name = "";
        String ItemNames = "";

        for (Order order : list) {
            name = order.getMember().getUsername();
            System.out.println("order.getMember().getName() = " + order.getMember().getName());
            ItemNames = order.getOrderItems().get(0).getItem().getName();
            System.out.println("order.getOrderItems().get(0).getItem().getName() = " + order.getOrderItems().get(0).getItem().getName());
        }

        //검증

        assertEquals("회원1", name);
        assertEquals("jpa", ItemNames);
    }


    //재고 초과할경우
    @Test
    public void 상품주문_주문수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 11;

        //when
        Long order = orderService.Order(member.getId(), item.getId(), orderCount);

        //then
        assertEquals(0l, order);
    }

    //주문 취소
    @Test
    void cancelOrder() throws Exception {
        //given
        Member member = createMember();
        Book item = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.Order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findById(orderId).get();

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals(10, item.getStockQuantity());

    }


    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
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