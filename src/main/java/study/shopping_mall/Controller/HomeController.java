package study.shopping_mall.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import study.shopping_mall.dto.ItemListSearch;
import study.shopping_mall.entity.OrderCart;
import study.shopping_mall.entity.item.Item;
import study.shopping_mall.service.ItemService;
import study.shopping_mall.service.OrderCartService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@Slf4j //로그 기능
@RequiredArgsConstructor
public class HomeController {

    private final OrderCartService orderCartService;
    private final ItemService itemService;

    @RequestMapping("/")
    public String home(@ModelAttribute("ItemListSearch")ItemListSearch ItemListSearch, Model model, HttpSession session) {
        log.info("home controller");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<OrderCart> cartAll = orderCartService.findCartAll(username);
        List<Item> five = itemService.findFive();
        List<Item> book = itemService.findBook();
        List<Item> shoes = itemService.findShoes();
        List<Item> machine = itemService.findMachine();
        List<Item> food = itemService.findFood();

        logininfo(session);
        model.addAttribute("cartAll", cartAll);
        model.addAttribute("five", five);
        model.addAttribute("shoes", shoes);
        model.addAttribute("book", book);
        model.addAttribute("machine", machine);
        model.addAttribute("food", food);
        return "home";
    }

    private static void logininfo(HttpSession session) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username.equals("anonymousUser")){

        }else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            Iterator<? extends GrantedAuthority> iter = authorities.iterator();
            GrantedAuthority auth = iter.next();
            String role = auth.getAuthority();
            session.setAttribute("username",username);
            session.setAttribute("role",role);
        }

    }

}

