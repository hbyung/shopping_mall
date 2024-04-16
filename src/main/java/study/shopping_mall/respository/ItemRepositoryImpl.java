package study.shopping_mall.respository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.util.StringUtils;
import study.shopping_mall.dto.ItemListSearch;
import study.shopping_mall.dto.ItemSearch;
import study.shopping_mall.dto.QitemDto;
import study.shopping_mall.dto.itemDto;
import study.shopping_mall.entity.QMultiFiles;
import study.shopping_mall.entity.item.Item;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static study.shopping_mall.entity.QCategory.category;
import static study.shopping_mall.entity.QMultiFiles.multiFiles;
import static study.shopping_mall.entity.item.QItem.item;


public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<itemDto> category(Pageable pageable, ItemSearch itemSearch) {
        QueryResults<itemDto> results = queryFactory
                .select(new QitemDto(
                        item.id.as("itemId"),
                        item.mainFilePath,
                        item.name,
                        item.stockQuantity,
                        item.itemTime,
                        category.name.as("categoryName")
                ))
                .from(item)
                .join(item.category, category)
                .where(item.stockQuantity.gt(0), nameLike(itemSearch.getMemberName()))

                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<itemDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);

    }

    @Override
    public Page<Item> findList(Pageable pageable, ItemListSearch itemListSearch) {
        QueryResults<Item> results = queryFactory
                .selectFrom(item)
                .join(item.category, category)
                .where(item.stockQuantity.gt(0), IsSearchAble(itemListSearch))

                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Item> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);

    }

    @Override
    public List<Item> findMultiFilesById(long id) {
        return queryFactory
                .selectFrom(item)
                .join(item.multiFiles, multiFiles)
                .where(item.id.eq(id))
                .fetch();
    }

    @Override
    public List<Item> findAllFive() {
        return   queryFactory
                    .selectFrom(item)
                    .limit(5)
                    .orderBy(item.id.desc())
                    .fetch();
    }

    @Override
    public List<Item> findBook() {
        return    queryFactory
                .selectFrom(item)
                .where(bookCategory())
                .limit(9)
                .fetch();
    }

    @Override
    public List<Item> findFood() {
        return    queryFactory
                .selectFrom(item)
                .where(foodCategory())
                .limit(9)
                .fetch();
    }

    @Override
    public List<Item> findMachine() {
        return    queryFactory
                .selectFrom(item)
                .where(machineCategory())
                .limit(9)
                .fetch();
    }

    @Override
    public List<Item> findShoes() {
        return    queryFactory
                    .selectFrom(item)
                    .where(shoesCategory())
                    .limit(9)
                    .fetch();

    }

    @Override
    public Page<Item> findSearch(Pageable pageable,String name) {

        QueryResults<Item> results = queryFactory
                .selectFrom(item)
                .join(item.category, category)
                .where(item.stockQuantity.gt(0), nameLike(name))

                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Item> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression IsSearchAble(ItemListSearch itemListSearch) {


        if (itemListSearch.getCategory().equals("책")) {

            return categoryLike(itemListSearch);

        } else if (itemListSearch.getCategory().equals("음식")) {

            return categoryLike(itemListSearch);

        } else if (itemListSearch.getCategory().equals("전자")) {

            return categoryLike(itemListSearch);

        } else if (itemListSearch.getCategory().equals("신발")) {

            return categoryLike(itemListSearch);

        }

        return null;
    }



    private BooleanExpression nameLike(String memberName) {
        if (!StringUtils.hasText(memberName)) {

            return null;
        }
        return item.name.contains(memberName).or(item.category.name.contains(memberName));
    }


    private BooleanExpression categoryLike(ItemListSearch itemListSearch) {
        if (!StringUtils.hasText(itemListSearch.getName())) {

            return item.category.name.eq(itemListSearch.getCategory());
        }

        return item.category.name.eq(itemListSearch.getCategory()).and(item.name.contains(itemListSearch.getName()));
    }

    private BooleanExpression bookCategory() {

        return item.category.name.eq("책");
    }

    private BooleanExpression foodCategory() {

        return item.category.name.eq("음식");
    }

    private BooleanExpression machineCategory() {

        return item.category.name.eq("전자");
    }

    private BooleanExpression shoesCategory() {

        return item.category.name.eq("신발");
    }


}
