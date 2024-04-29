package study.shopping_mall.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import study.shopping_mall.dto.ItemListSearch;
import study.shopping_mall.entity.Inquire;
import study.shopping_mall.entity.OrderCart;
import study.shopping_mall.entity.Reviews;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.service.ItemService;
import study.shopping_mall.service.OrderCartService;
import study.shopping_mall.service.ReviewService;
import study.shopping_mall.service.InquireService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    final private ItemService itemService;
    final private ReviewService reviewService;
    final private InquireService inquireService;
    final private OrderCartService orderCartService;


    @GetMapping("/ItemList")
    public String ItemList(@ModelAttribute("ItemListSearch")ItemListSearch itemListSearch, Model model, @PageableDefault(size = 10) Pageable pageable, HttpSession session) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<Item> ItemList = itemService.findAll(pageable, itemListSearch);
        List<OrderCart> cartAll = orderCartService.findCartAll(username);

        int startPage = 1;
        int endPage = ItemList.getTotalPages();

        if(endPage == 0){
            startPage = 1;
            endPage = 1;
            model.addAttribute("startPage", startPage);
            model.addAttribute("items", ItemList);
            model.addAttribute("endPage", endPage);
            model.addAttribute("cartAll", cartAll);
            return "Item/ItemList";
        }
        System.out.println("itemListSearch = " + itemListSearch.getCategory());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("items", ItemList);
        model.addAttribute("cartAll", cartAll);

        return "Item/ItemList";

    }

    @GetMapping("/SearchItem")
    public String findSearch(@ModelAttribute("ItemListSearch")ItemListSearch itemListSearch, @RequestParam("name")String name, Model model, @PageableDefault(size = 10) Pageable pageable){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<Item> search = itemService.findSearch(pageable, name);
        List<OrderCart> cartAll = orderCartService.findCartAll(username);
        int startPage = 1;
        int endPage = search.getTotalPages();

        model.addAttribute("items", search);
        model.addAttribute("cartAll", cartAll);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "Item/ItemList";
    }





    @GetMapping(value = "/item/{id}/detail")
    public String ItemDetail(@ModelAttribute("ItemListSearch")ItemListSearch itemListSearch,@PathVariable("id")Long ItemId, Model model, HttpSession session){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        session.setAttribute(username, "username");
        System.out.println("username = " + username);
        Item id = itemService.findById(ItemId);
        List<Item> multiFilesById = itemService.findMultiFilesById(ItemId);
        List<Reviews> reviewsAll = reviewService.findReviewsAll(ItemId);
        List<Inquire> inquireList = inquireService.findAllId(ItemId);
        List<OrderCart> cartAll = orderCartService.findCartAll(username);

        long count = reviewsAll.stream().count();
        System.out.println("count = " + count);
        model.addAttribute("item",id);
        model.addAttribute("allFiles",multiFilesById);
        model.addAttribute("reviewsAll",reviewsAll);
        model.addAttribute("inquireList",inquireList);
        model.addAttribute("count", count);
        model.addAttribute("cartAll", cartAll);
        model.addAttribute("username",username);
//        model.addAttribute("requestId", requestId);

        return "Item/ItemDetail";
    }

    private static List<String> getStringList() {
        List<String> category = new ArrayList<>();
        category.add("책");
        category.add("음식");
        category.add("기계");
        category.add("신발");
        return category;
    }


}
