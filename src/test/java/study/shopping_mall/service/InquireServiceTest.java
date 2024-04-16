package study.shopping_mall.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import study.shopping_mall.dto.AdminDto;
import study.shopping_mall.entity.Inquire;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.respository.InquireRepository;
import study.shopping_mall.respository.ItemRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class InquireServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    InquireService inquireService;
    @Autowired
    InquireRepository inquireRepository;

    //상품문의
    @Test
    void inquire() throws Exception {
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
        String content = "상품 이상이있어서 문의합니다.";
        String writer = "병환";
        Item name1 = itemRepository.findByName("뉴발슬리퍼1");
        Long inquireId = inquireService.Inquire(name1.getId(), writer, content);

        //then
        Inquire inquire = inquireRepository.findById(inquireId).get();

        assertEquals("병환", inquire.getWriter());
        assertEquals("상품 이상이있어서 문의합니다.", inquire.getContent());

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