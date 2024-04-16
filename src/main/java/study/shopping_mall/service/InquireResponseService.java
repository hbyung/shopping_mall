package study.shopping_mall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.shopping_mall.entity.Inquire;
import study.shopping_mall.entity.InquireResponse;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.respository.InquireRepository;
import study.shopping_mall.respository.ItemRepository;
import study.shopping_mall.respository.inquireResponseRepository;

@Service
@RequiredArgsConstructor
public class InquireResponseService {

    private final ItemRepository itemRepository;
    private final inquireResponseRepository inquireResponseRepository;
    private final InquireRepository inquireRepository;

    public Long InquireResponse(long itemId, long inquireId, String writer, String content){
        Item item = itemRepository.findById(itemId);
        Inquire inquire = inquireRepository.findById(inquireId);
        InquireResponse response = InquireResponse.createInquire(item, inquire, writer, content);
        inquireResponseRepository.save(response);
        return response.getId();
    }
}
