package study.shopping_mall.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import study.shopping_mall.dto.AdminDto;
import study.shopping_mall.entity.Reviews;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.respository.ItemRepository;
import study.shopping_mall.respository.ReviewRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewRepository reviewRepository;


    //리뷰 등록
    @Test
    void saveReivew() throws Exception {

        //given
        AdminDto adminDto = getAdminDto();

        int price = adminDto.getPrice();
        String name = adminDto.getName();
        int stockQuantity = adminDto.getStockQuantity();
        MultipartFile mainFile = getMultipartFile();

        List<MultipartFile> file = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            file.add(multipartFile);
        }

        //when
        itemService.CreateForm(adminDto, mainFile, file ,name, price, stockQuantity);
        Item name1 = itemRepository.findByName("뉴발슬리퍼1");
        String review = "정말 잘봤습니다.";
        String username = "병환";
        long reivewId = reviewService.SaveReivew(name1, review, username);

        //then
        Reviews reviews = reviewRepository.findById(reivewId).get();
        assertEquals("병환", reviews.getWriter());
        assertEquals("정말 잘봤습니다.", reviews.getWriterReview());

    }

    @Test
    void findReviewsAll() throws Exception {

        //given
        AdminDto adminDto = getAdminDto();

        int price = adminDto.getPrice();
        String name = adminDto.getName();
        int stockQuantity = adminDto.getStockQuantity();
        MultipartFile mainFile = getMultipartFile();

        List<MultipartFile> file = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            file.add(multipartFile);
        }

        //when
        itemService.CreateForm(adminDto, mainFile, file ,name, price, stockQuantity);
        Item name1 = itemRepository.findByName("뉴발슬리퍼1");
        String review = "정말 잘봤습니다.!";
        String username = "병환";
        long reivewId = reviewService.SaveReivew(name1, review, username);

        //then
        List<Reviews> allReview = reviewRepository.findAllReview(name1.getId());
        String allreviews =null;
        String allUsername = null;
        for (Reviews reviews : allReview) {
            allreviews = reviews.getWriterReview();
            allUsername = reviews.getWriter();
        }


        assertEquals("병환", allUsername);
        assertEquals("정말 잘봤습니다.", allreviews);

    }

    //file -> MultipartFile 만드는법
    private static MultipartFile getMultipartFile() {
        Path path = Paths.get("/path/to/the/file.txt");
        String name1 = "file.txt";
        String originalFileName = "file.txt";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile mainFile = new MockMultipartFile(name1,
                originalFileName, contentType, content);
        return mainFile;
    }


    private static AdminDto getAdminDto() {
        AdminDto adminDto = new AdminDto();
        adminDto.setName("뉴발슬리퍼1");
        adminDto.setPrice(15000);
        adminDto.setStockQuantity(8);
        adminDto.setList("책");
        return adminDto;
    }
}