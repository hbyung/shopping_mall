package study.shopping_mall.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.shopping_mall.dto.ItemListSearch;
import study.shopping_mall.dto.ItemSearch;
import study.shopping_mall.dto.itemDto;
import study.shopping_mall.entity.item.Item;

import java.util.List;

public interface ItemRepositoryCustom {
    Page<itemDto> category(Pageable pageable, ItemSearch itemSearch);
    Page<Item> findList(Pageable pageable, ItemListSearch itemListSearch);
    List<Item> findMultiFilesById(long id);
    List<Item> findAllFive();
    List<Item> findBook();
    List<Item> findFood();
    List<Item> findMachine();
    List<Item> findShoes();
    Page<Item> findSearch(Pageable pageable, String name);
}
