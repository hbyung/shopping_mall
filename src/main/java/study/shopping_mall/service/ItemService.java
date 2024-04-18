package study.shopping_mall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import study.shopping_mall.dto.AdminDto;
import study.shopping_mall.dto.ItemListSearch;
import study.shopping_mall.dto.ItemSearch;
import study.shopping_mall.dto.itemDto;
import study.shopping_mall.entity.Category;
import study.shopping_mall.entity.MultiFiles;
import study.shopping_mall.entity.item.*;
import study.shopping_mall.respository.ItemRepository;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {


    private final ItemRepository itemRepository;

    public void CreateForm(AdminDto adminDto, MultipartFile mainFile, List<MultipartFile> file, String name, int price, int stockQuantity) throws Exception {

        //파일 업로드
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img";

        int size = file.size();
        List<MultiFiles> multiFiles = new ArrayList<>();

        UUID uuid = UUID.randomUUID();
        String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
        String fileName = uuid +" "+mainFile.getOriginalFilename();
        String mainfileName = fileName.replaceAll(match, " ");
        File savemainFile = new File(projectPath, mainfileName +".jpg");
        mainFile.transferTo(savemainFile);

        for (MultipartFile files : file) {
            String fileNames = files.getOriginalFilename();
            String mainfileNames = uuid +" "+fileNames.replaceAll(match, " ");
            File saveFile = new File(projectPath, mainfileNames + ".jpg");
            files.transferTo(saveFile);

            MultiFiles multi = new MultiFiles(mainfileNames, "/img/" + mainfileNames);
            multiFiles.add(multi);

        }

        //카테고리 명
        Category category = new Category();
        category.setName(adminDto.getList());
        String categoryName = category.getName();

        //등록
        CreateAdimForm(adminDto, name, price, stockQuantity, multiFiles, mainfileName, categoryName, category);

    }

    private void CreateAdimForm(AdminDto adminDto, String name, int price, int stockQuantity,
                                List<MultiFiles> multiFiles, String mainfileName, String categoryName, Category category) {
        //등록시간
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        Searchcategory(adminDto, name, price, stockQuantity, multiFiles,  mainfileName, categoryName, category, formatDate);
    }

    public Item findName(String name) {
        Item itemName = itemRepository.findByName(name);

        return itemName;
    }

    public Item findById(long id) {
        Item itemId = itemRepository.findById(id);
        return itemId;
    }

    public Page<Item> findAll(Pageable pageable, ItemListSearch itemListSearch) {
        Page<Item> ItemAll = itemRepository.findList(pageable, itemListSearch);
        return ItemAll;
    }

    public Page<itemDto> findCategory(Pageable pageable, ItemSearch itemSearch) {
        Page<itemDto> category = itemRepository.category(pageable, itemSearch);
        return category;
    }

    public void cancelItem(Long itemId) {
        Item item = itemRepository.findById(itemId).get();
        int stockQuantity = item.getStockQuantity();
        item.removeStock(stockQuantity);
    }

    public Page<Item> findSearch(Pageable pageable, String name){
        return itemRepository.findSearch(pageable,name);
    }

    public List<Item> findFive(){
        return itemRepository.findAllFive();
    }
    public List<Item> findBook(){
        return itemRepository.findBook();
    }
    public List<Item> findFood(){
        return itemRepository.findFood();
    }
    public List<Item> findMachine(){
        return itemRepository.findMachine();
    }
    public List<Item> findShoes(){
        return itemRepository.findShoes();
    }
    public List<Item> findMultiFilesById(long id){
        return itemRepository.findMultiFilesById(id);
    }


    private void Searchcategory(AdminDto adminDto, String name, int price, int stockQuantity,  List<MultiFiles> multiFiles , String mainfileName, String categoryName, Category category, String formatDate) {
        if (categoryName.equals("책")) {
            Book book = new Book();
            book.setIsbn(adminDto.getIsbn());
            book.setAuthor(adminDto.getAuthor());
            createItem(adminDto, name, price, stockQuantity, multiFiles, mainfileName, category, formatDate, book);
            itemRepository.save(book);

        } else if (categoryName.equals("음식")) {
            Food food = new Food();
            food.setFoodCode(adminDto.getFoodCode());
            food.setFoodBrand(adminDto.getFoodBrand());
            createItem(adminDto, name, price, stockQuantity, multiFiles, mainfileName, category, formatDate, food);
            itemRepository.save(food);

        } else if (categoryName.equals("전자")) {
            Machine machine = new Machine();
            machine.setMachineCode(adminDto.getMachineCode());
            machine.setMachineBrand(adminDto.getMachineBrand());
            createItem(adminDto, name, price, stockQuantity, multiFiles, mainfileName, category, formatDate, machine);
            itemRepository.save(machine);

        } else if (categoryName.equals("신발")) {
            Shoes shoes = new Shoes();
            shoes.setShoesCode(adminDto.getShoesCode());
            shoes.setShoesBrand(adminDto.getShoesBrand());
            createItem(adminDto, name, price, stockQuantity, multiFiles, mainfileName, category, formatDate, shoes);
            itemRepository.save(shoes);

        }
    }

    private static void createItem(AdminDto adminDto, String name, int price, int stockQuantity, List<MultiFiles> multiFiles,
                                   String mainfileName, Category category, String formatDate, Item item) {
        item.setCategory(category);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
        item.setMainFileName(mainfileName);
        item.setMainFilePath("/img/" + mainfileName);
        item.setItemTime(formatDate);
        System.out.println("item.getItemTime() = " + item.getItemTime());
        //세부화면 저장
        for (MultiFiles multiFile : multiFiles) {
            item.addMultiFiles(multiFile);
            System.out.println("multiFile = " + multiFile.getFileName());
        }

    }

}

