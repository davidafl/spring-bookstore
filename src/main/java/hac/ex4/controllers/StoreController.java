package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.repo.Book;
import hac.ex4.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * Controller for the Store.
 */
@Controller
public class StoreController {

    /**
     * The book service.
     */
    @Autowired
    private BookService bookService;

    /**
     * The shopping cart.
     */
    @Resource(name = "myShoppingCart")
    private ShoppingCart cart;

    /**
     * The main page of the store.
     * @param model - the model
     * @param principal - the principal
     * @return - the store page
     */
    @GetMapping("/")
    public String main(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("books", bookService.get5DiscountBooks());
        model.addAttribute("cart", cart);
        return "index";
    }

    /**
     * shows all books at the store.
     * @param model - the model
     * @return - the all-books page
     */
    @GetMapping("allbooks")
    public String allBooks(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        try {
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("cart", cart);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "all-books";
    }

    /**
     * adds a book to the cart.
     * @param id - the id of the book
     * @param model - the model
     * @return - the store page
     */
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

    /**
     * my cart page.
     * @param model - the model
     * @return - the cart page
     */
    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    /**
     * remove a book from the cart.
     * @param model - the model
     * @param id - the id of the book to remove
     * @return - the cart page
     */
    @PostMapping("/cart/delete")
    public String deleteFromCart(Model model, @RequestParam Long id){
        cart.removeBook(id);
        return "redirect:/cart";
    }

    /**
     * clear the cart.
     * @param model - the model
     * @return - the cart page
     */
    @PostMapping("/cart/clear")
    public String clearCart(Model model){
        cart.clear();
        return "redirect:/cart";
    }

    /**
     * checkout page.
     * if no books in the cart, redirect to the cart page.
     * @param model
     * @return
     */
    @GetMapping("/cart/checkout")
    public String checkout(Model model){
        if (cart.getBooks().size() == 0) {
            return "redirect:/cart";
        }
        model.addAttribute("cart", cart);
        return "checkout";
    }

    /**
     * pay for the cart. and clear the cart.
     * user have to login to pay.
     * if the payment is successful, redirect to the main page.
     * else redirect to the cart page.
     * @param model
     * @param principal
     * @return
     */
    @PostMapping("/cart/pay")
    public String pay(Model model, Principal principal){
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
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

    /**
     * get method for the pay page.
     * @param model - the model
     * @param principal - the principal
     * @return - the cart page
     */
    @GetMapping("/cart/pay")
    public String payGet(Model model, Principal principal){
        if (principal != null) {
            model.addAttribute("username", principal.getName());
            model.addAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

    /**
     * search for a book in the store.
     * @param model - the model
     * @param searchParam - the search param
     * @return - the search results in index page
     */
    @PostMapping("/search")
    public String searchForm(Model model, @RequestParam String searchParam){
        model.addAttribute("books", bookService.search(searchParam));
        model.addAttribute("cart", cart);
        return "index";
    }

    /**
     * get method for the search page.
     * @param model - the model
     * @return - the home page /
     */
    @GetMapping("/search")
    public String searchGet(Model model){
        return "redirect:/";
    }
}
