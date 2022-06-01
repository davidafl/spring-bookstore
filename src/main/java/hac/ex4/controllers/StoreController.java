package hac.ex4.controllers;

import hac.ex4.repo.Book;
import hac.ex4.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String main(Book book, Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "index";
    }
}
