package study.shopping_mall.respository;

import study.shopping_mall.entity.Reviews;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Reviews> findAllReview(Long itemId);
}
