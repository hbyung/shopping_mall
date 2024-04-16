package study.shopping_mall.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import study.shopping_mall.dto.AdminDto;
import study.shopping_mall.dto.ItemListSearch;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.respository.ItemRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    //상품등록
    @Test
    void createForm() throws Exception {

        //given
        AdminDto adminDto = getAdminDto();

        int price = adminDto.getPrice();
        String name = adminDto.getName();
        int stockQuantity = adminDto.getStockQuantity();
        MultipartFile mainFile = getMultipartFile();

        List<MultipartFile> file = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            file.add(mainFile);
        }

        //when
        itemService.CreateForm(adminDto, mainFile, file ,name, price, stockQuantity);
        Item name1 = itemRepository.findByName("뉴발슬리퍼1");


        //then
        assertEquals("뉴발슬리퍼1", name1.getName());

    }

    //상품 검색및 목록
    @Test
    void findAll() throws Exception {

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
        Pageable pageable = PageRequest.of(0,10);
        ItemListSearch itemListSearch = new ItemListSearch();
        itemListSearch.setCategory("책");
        itemListSearch.setName("뉴발슬리퍼1");
        Page<Item> items = itemService.findAll(pageable, itemListSearch);

        String names = null;
        for (Item item : items) {
            names = item.getName();
        }

        //then
        assertEquals("뉴발슬리퍼1", names);
    }

    //상품 등록 취소
    @Test
    void cancelItem() throws Exception {

        //given
        AdminDto adminDto = getAdminDto();

        int price = adminDto.getPrice();
        String name = adminDto.getName();
        int stockQuantity = adminDto.getStockQuantity();

        MultipartFile mainFile = getMultipartFile();

        List<MultipartFile> file = new ArrayList<>();
        file.add(mainFile);

        //when
        itemService.CreateForm(adminDto, mainFile, file ,name, price, stockQuantity);
        Item name2 = itemRepository.findByName("뉴발슬리퍼1");
        int stockQuantity1 = name2.getStockQuantity();
        name2.removeStock(stockQuantity1);

        //then
        assertEquals(0, name2.getStockQuantity());
    }


    @Test
    void findSearch() throws Exception {

        //given
        AdminDto adminDto = getAdminDto();

        int price = adminDto.getPrice();
        String name = adminDto.getName();
        int stockQuantity = adminDto.getStockQuantity();

        MultipartFile mainFile = getMultipartFile();

        List<MultipartFile> file = new ArrayList<>();
        file.add(mainFile);

        //when
        itemService.CreateForm(adminDto, mainFile, file ,name, price, stockQuantity);
        Pageable pageable = PageRequest.of(0,10);
        Page<Item> items = itemService.findSearch(pageable, "뉴발슬리퍼1");
        String names = null;

        for (Item item : items) {
            names = item.getName();
        }

        //then
        assertEquals("뉴발슬리퍼1", names);
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