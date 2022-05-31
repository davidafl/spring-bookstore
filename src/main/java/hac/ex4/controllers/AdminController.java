package hac.ex4.controllers;

import hac.ex4.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private BookRepository repo;

    @GetMapping("/admin")
    public String main(Model model) {
        model.addAttribute("books", repo.findAll());
        return "admin";
    }
}
