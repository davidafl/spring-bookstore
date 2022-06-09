package hac.ex4.controllers;

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

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private BookService bookService;


    @GetMapping("/admin")
    public String main(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "admin";
    }

    @PostMapping("/admin/addbook")
    public String addBook(Book book, Model model) {
        try {
            bookService.saveBook(book);
            model.addAttribute("books", bookService.getBooks());

        } catch (Exception e) {
            model.addAttribute("message", "Sorry we could not perform your request!");
        }
        model.addAttribute("users", bookService.getBooks());
        return "redirect:/admin";
    }

    @GetMapping("/admin/addbook")
    public String addBookForm(Book book, Model model) {
        return "add-book";
    }

    @PostMapping("/admin/edit")
    public String editBook(@RequestParam("id") long id, Model model) {
        Book book = bookService.getBook(id);

        // the name "user"  is bound to the VIEW
        model.addAttribute("book", book);
        return "update-book";
    }

    @PostMapping("/admin/update/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }

        bookService.saveBook(book);
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
        return "payments";
    }
}
