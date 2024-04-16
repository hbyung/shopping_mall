package study.shopping_mall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.shopping_mall.entity.Reviews;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.respository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public long SaveReivew(Item item, String review, String username){
        Reviews review1 = Reviews.addReview(item, review, username);
        reviewRepository.save(review1);
        return review1.getId();
    }

    public List<Reviews> findReviewsAll(long id){
        List<Reviews> allReview = reviewRepository.findAllReview(id);
        return allReview;
    }

}
