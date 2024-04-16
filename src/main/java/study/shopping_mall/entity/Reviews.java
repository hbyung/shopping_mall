package study.shopping_mall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import study.shopping_mall.entity.item.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Reviews extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String Writer;
    private String WriterReview;
    private String reviewDate;


    //연관 관계 메서드
    public void setItem(Item item) {
        this.item = item;
        item.getReviews().add(this);
    }


    public static Reviews addReview(Item item, String review, String username){
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        Reviews review1 = new Reviews();
        review1.setItem(item);
        review1.setReviewDate(formatDate);
        review1.setWriter(username);
        review1.setWriterReview(review);
        System.out.println("review1.getWriterReview() = " + review1.getWriterReview());
        return review1;
    }
}
