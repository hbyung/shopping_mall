package study.shopping_mall.respository;

import study.shopping_mall.entity.Inquire;

import java.util.List;

public interface InquireRepositoryCustom {

    List<Inquire> findAllId(Long itemId);
}
