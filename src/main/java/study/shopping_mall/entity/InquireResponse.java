package study.shopping_mall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import study.shopping_mall.entity.item.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class InquireResponse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id")
    private long id;
    private String Writer;
    private String content;
    private String inquireResponseDateTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "inquire_id")
    private Inquire inquire;

    public void setItem(Item item) {
        this.item = item;
        item.getInquireResponses().add(this);
    }

    public void setInquire(Inquire inquire) {
        this.inquire = inquire;
        inquire.getResponses().add(this);
    }

    public static InquireResponse createInquire(Item item,Inquire inquire ,String writer, String content){
        InquireResponse inquireResponse = new InquireResponse();
        inquireResponse.setItem(item);
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        inquireResponse.setInquireResponseDateTime(formatDate);
        inquireResponse.setWriter(writer);
        inquireResponse.setInquire(inquire);
        inquireResponse.setContent(content);
        return inquireResponse;
    }

}
