package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.repo.Book;
import hac.ex4.repo.PaymentRepository;
import hac.ex4.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.security.Principal;

@Controller
public class StoreController {

    @Autowired
    private BookService bookService;

    @Resource(name = "myShoppingCart")
    private ShoppingCart cart;

    @GetMapping("/")
    public String main(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("books", bookService.get5DiscountBooks());
        model.addAttribute("cart", cart);
        return "index";
    }

    @GetMapping("allbooks")
    public String allBooks(Model model) {
        try {
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("cart", cart);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "all-books";
    }

    @PostMapping("/addtocart")
    public String addToCart(@RequestParam Long id, Model model) {
        try {
            Book book = bookService.getBook(id);
            cart.addBook(book);

            System.out.println(book);
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("cart", cart);
            System.out.println(cart);
        } catch (Exception e) {
            model.addAttribute("message", "Sorry we could not perform your request!");
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cart", cart);

        return "cart";
    }

    @PostMapping("/cart/delete")
    public String deleteFromCart(Model model, @RequestParam Long id){
        cart.removeBook(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String clearCart(Model model){
        cart.clear();
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String checkout(Model model){
        if (cart.getBooks().size() == 0) {
            return "redirect:/cart";
        }
        model.addAttribute("cart", cart);
        return "checkout";
    }

    @PostMapping("/cart/pay")
    public String pay(Model model){
        try {
            bookService.updateStock(cart);
            cart.clear();
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("cart", cart);
            return "cart";
        }
        model.addAttribute("cart", cart);
        return "confirmation";
    }

    @GetMapping("/cart/pay")
    public String payGet(Model model) {
        return "redirect:/cart";
    }

    @PostMapping("/search")
    public String searchForm(Model model, @RequestParam String searchParam){
        model.addAttribute("books", bookService.search(searchParam));
        model.addAttribute("cart", cart);
        return "index";
    }

    @GetMapping("/search")
    public String searchGet(Model model){
        return "redirect:/";
    }
}
