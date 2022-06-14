package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.repo.Book;
import hac.ex4.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AdminController {

    @Autowired
    private BookService bookService;

    @Resource(name = "myShoppingCart")
    private ShoppingCart cart;

    @GetMapping("/admin")
    public String main(Model model, Principal principal) {
        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("cart", cart);
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "admin";
    }

    @PostMapping("/admin/addbook")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("cart", cart);
            //model.addAttribute("message", "Sorry we could not perform your request!");
            return "add-book";
        }
        try {
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("cart", cart);
            bookService.saveBook(book);

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("cart", cart);
            return "add-book";
        }
        //model.addAttribute("books", bookService.getBooks());
        return "redirect:/admin";
    }

    @GetMapping("/admin/addbook")
    public String addBookForm(Book book, Model model) {
        model.addAttribute("cart", cart);
        return "add-book";
    }

    @PostMapping("/admin/edit")
    public String editBook(@RequestParam("bookid") long id, Model model) {
        Book book = bookService.getBook(id);

        model.addAttribute("book", book);
        model.addAttribute("cart", cart);
        return "update-book";
    }

    @PostMapping("/admin/update")
    public String updateBook(/*@RequestParam("bookid") long id,*/ @Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            //book.setId(id);
            model.addAttribute("book", book);
            model.addAttribute("cart", cart);
            return "update-book";
        }
        bookService.saveBook(book);
        model.addAttribute("cart", cart);
        model.addAttribute("books", bookService.getBooks());
        return "admin";
    }

    @PostMapping("/admin/delete")
    public String deleteBook(@RequestParam("id") long id, Model model) {

        Book book = bookService.getBook(id);
        bookService.deleteBook(book);
//        model.addAttribute("users", bookService.getBooks());
        return "redirect:/admin";
    }

    @GetMapping("/admin/payments")
    public String payments(Model model) {
        model.addAttribute("payments", bookService.getPayments());
        model.addAttribute("cart", cart);
        return "payments";
    }
}
