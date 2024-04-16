package study.shopping_mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.shopping_mall.dto.CartListDto;
import study.shopping_mall.entity.item.Item;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문가격
    private int count; //주문 수량

    public OrderItem(Item item, int orderPrice, int count) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    //생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    public static List<OrderItem> createCartOrderItem(List<Item> itemList) {

        List<OrderItem> listOrderItem = new ArrayList<>();

        for (Item item : itemList) {
            OrderItem orderItem = new OrderItem(item, item.getPrice(), item.getCount());
            item.removeStock(item.getCount());
            listOrderItem.add(orderItem);
        }

        return listOrderItem;
    }

    //==비지니스 로직==//
    public void cancel() {
        getItem().addStock(count);

    }

    //== 조회 로직==//
    public int getTotalPrice() {
        return  getOrderPrice() * getCount();
    }
}
