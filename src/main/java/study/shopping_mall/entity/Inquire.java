package study.shopping_mall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import study.shopping_mall.entity.item.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Inquire extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquire_id")
    private long id;
    private String Writer;
    private String content;
    private String InquireDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToMany(mappedBy = "inquire", cascade = CascadeType.ALL)
    private List<InquireResponse> responses = new ArrayList<>();

    public void setItem(Item item) {
        this.item = item;
        item.getInquires().add(this);
    }

    public void addResponse(InquireResponse inquireResponse) {
        responses.add(inquireResponse);
        inquireResponse.setInquire(this);
    }

    public static Inquire createInquire(Item item, String writer, String content){
        Inquire inquire = new Inquire();
        inquire.setItem(item);
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        inquire.setInquireDate(formatDate);
        inquire.setWriter(writer);
        inquire.setContent(content);
        return inquire;
    }
}
