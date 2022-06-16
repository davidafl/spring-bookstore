package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.repo.Book;
import hac.ex4.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Controller for the admin.
 */
@Controller
public class AdminController {

    @Autowired
    private BookService bookService;

    @Resource(name = "myShoppingCart")
    private ShoppingCart cart;

    /**
     * Admin Home page.
     * @param model
     * @param principal
     * @return the admin page
     */
    @GetMapping("/admin")
    public String main(Model model, Principal principal) {
        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("cart", cart);
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "admin";
    }

    /**
     * Add a new book to the database.
     * @param book - the book to add
     * @param result - the result of the validation
     * @param model - the model
     * @return - the add-book page if the validation fails,
     * the admin page if the validation succeeds
     */
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
        return "redirect:/admin";
    }

    /**
     * get method for the add-book page.
     * @param model
     * @return the add-book page
     */
    @GetMapping("/admin/addbook")
    public String addBookForm(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("cart", cart);
        model.addAttribute("book", new Book());
        return "add-book";
    }

    /**
     * edit a book in the database.
     * @param id - the id of the book to edit
     * @param model - the model
     * @return - the update-book page
     */
    @PostMapping("/admin/edit")
    public String editBook(@RequestParam("bookid") long id, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        Book book = bookService.getBook(id);

        model.addAttribute("book", book);
        model.addAttribute("cart", cart);
        return "update-book";
    }

    /**
     * get method for the update-book page.
     * @return - the update-book page
     */
    @GetMapping("/admin/edit")
    public String editBookForm() {
        return "redirect:/admin";
    }

    /**
     * update a book in the database.
     * @param book
     * @param result
     * @param model
     * @return  the update-book if there are errors, the admin page if there are no errors
     */
    @PostMapping("/admin/update")
    public String updateBook(@Valid Book book, BindingResult result, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }

        if (result.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("cart", cart);
            return "update-book";
        }
        bookService.saveBook(book);
        model.addAttribute("cart", cart);
        model.addAttribute("books", bookService.getBooks());
        return "admin";
    }

    /**
     * get method for the update-book page.
     * @return - the update-book page
     */
    @GetMapping("/admin/update")
    public String updateBookForm() {
        return "redirect:/admin";
    }

    /**
     * delete a book from the database.
     * @param id - the id of the book to delete
     * @param model - the model
     * @return the admin page
     */
    @PostMapping("/admin/delete")
    public String deleteBook(@RequestParam("id") long id, Model model) {
        Book book = bookService.getBook(id);
        bookService.deleteBook(book);
        return "redirect:/admin";
    }

    /**
     * get method for the delete-book page.
     * @return - the delete-book page
     */
    @GetMapping("/admin/delete")
    public String deleteBookForm() {
        return "redirect:/admin";
    }

    /**
     * shows all payments made in the database.
     * @param model - the model
     * @param principal - the principal
     * @return the admin/payments page
     */
    @GetMapping("/admin/payments")
    public String payments(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("payments", bookService.getPayments());
        model.addAttribute("cart", cart);
        return "payments";
    }
}
