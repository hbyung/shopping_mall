package study.shopping_mall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.shopping_mall.entity.Inquire;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.respository.InquireRepository;
import study.shopping_mall.respository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InquireService {

    private final InquireRepository inquireRepository;
    private final ItemRepository itemRepository;

    public List<Inquire> findAllId(Long itemId){
        return inquireRepository.findAllId(itemId);
    }

    public Inquire findById(long id){
        return inquireRepository.findById(id);
    }

    public Long Inquire(long itemId, String writer, String content){
        Item item = itemRepository.findById(itemId);
        Inquire inquire = Inquire.createInquire(item, writer, content);
        inquireRepository.save(inquire);

        return inquire.getId();
    }
}
