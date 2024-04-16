package study.shopping_mall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.exception.NotEnoghStockException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class OrderCart extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    private String itemName;
    private int itemPrice;
    private int count;
    private String mainFilePath;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;


    //연관 메서드

    public void setMember(Member member) {
        this.member = member;
        member.getCarts().add(this);
    }

    public void setItem(Item item) {
        this.item = item;
        item.getCart().add(this);
    }

    public static OrderCart createCart(Member member, Item item){
        OrderCart cart = new OrderCart();
        cart.setMember(member);
        cart.setItem(item);
        return cart;
    }

    public void  removeCartCount() {
        int restStock = 0;
        this.count = restStock;
    }


//    Order order = new Order();
//        order.setMember(member);
//        order.setDelivery(delivery);
//    String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd mm:ss"));
//        for (OrderItem orderItem : orderItems) {
//        order.addOrderItem(orderItem);
//    }
//        order.setStatus(OrderStatus.ORDER);
//        order.setOrderDate(formatDate);
//        return order;

}
