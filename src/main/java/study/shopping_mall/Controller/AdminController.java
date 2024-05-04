package study.shopping_mall.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.shopping_mall.dto.AdminDto;
import study.shopping_mall.dto.ItemListSearch;
import study.shopping_mall.dto.ItemSearch;
import study.shopping_mall.dto.itemDto;
import study.shopping_mall.entity.OrderCart;
import study.shopping_mall.entity.item.*;
import study.shopping_mall.service.ItemService;
import study.shopping_mall.service.OrderCartService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AdminController {
    final private ItemService itemService;
    final private OrderCartService orderCartService;

    @GetMapping("/errorPage")
    public String errorPage(Model model)  {
        model.addAttribute("message","접근 권한이 없습니다.");
        model.addAttribute("searchUrl","/");
        return "Member/errorPage";
    }

    //상품 등록폼
    @GetMapping("/admin/order")
    public String AdminOrder(Model model, AdminDto adminDto){
        uuid(adminDto);
        model.addAttribute("adminDto", adminDto);
        return "admin/adminOrderForm";
    }

    //상품 등록
    @PostMapping("/admin/order")
    public String AdminOrderForm(@ModelAttribute("adminDto") @Valid AdminDto adminDto, BindingResult result, MultipartFile mainFile ,
                                 List<MultipartFile> file, Model model) throws Exception {

        String error = errorAll(result, mainFile, file, model);
        if (error != null) return error;
        int price = adminDto.getPrice();
        String Dtoname = adminDto.getName();
        String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
        String name = Dtoname.replaceAll(match, " ");

        int stockQuantity = adminDto.getStockQuantity();

        itemService.CreateForm(adminDto, mainFile, file,name, price, stockQuantity);

        System.out.println("완료되었습니다.");
        return "redirect:/admin/orderList";
    }

    private static void uuid(AdminDto adminDto) {
        UUID uuid = UUID.randomUUID();
        adminDto.setIsbn(uuid);
        adminDto.setFoodCode(uuid);
        adminDto.setMachineCode(uuid);
        adminDto.setShoesCode(uuid);
    }

    private static String errorAll(BindingResult result, MultipartFile mainFile, List<MultipartFile> file, Model model) {
        if (result.hasErrors()) {
            return "admin/adminOrderForm";
        }

        String mainfileName = mainFile.getOriginalFilename();
        if (mainfileName.isEmpty()) {
            model.addAttribute("message","파일을 등록해주세요.");
            model.addAttribute("searchUrl","/admin/order");
            return "Member/errorPage";
        }

        for (MultipartFile files : file) {
            String fileName = files.getOriginalFilename();
            if (fileName.isEmpty()){
                model.addAttribute("message","파일을 등록해주세요.");
                model.addAttribute("searchUrl","/admin/order");
                return "Member/errorPage";
            }
        }
        return null;
    }


    //상품페이지
    @GetMapping("/admin/orderList")
    public String AdminOrderList(@ModelAttribute("ItemSearch") ItemSearch itemSearch,
                                 @ModelAttribute("ItemListSearch") ItemListSearch itemListSearch,
                                 Model model, @PageableDefault(size = 5) Pageable pageable, HttpSession session){

        Page<itemDto> OrderList = itemService.findCategory(pageable,itemSearch);
        List<OrderCart> cartAll = orderCartService.findCartAll((String) session.getAttribute("username"));

        model.addAttribute("cartAll", cartAll);
        int startPage = 1;
        int endPage = OrderList.getTotalPages();

        if (endPage == 0 ){
            endPage = 1;
            System.out.println("OrderList.getPageable().getPageNumber() = " + OrderList.getPageable().getPageNumber());
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("items", OrderList);

            return "admin/adminOrderFormList";
        }
        System.out.println("OrderList.getPageable().getPageNumber() = " + OrderList.getPageable().getPageNumber());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("items", OrderList);


        return "admin/adminOrderFormList";
    }

    @PostMapping(value = "/admin/{itemId}/cancel")
    public String updateItemForm(@PathVariable("itemId") Long itemId, HttpServletRequest request) {

        itemService.cancelItem(itemId);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;

    }


}
