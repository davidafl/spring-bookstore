package hac.ex4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

    @GetMapping("/admin-navbar")
    public String adminNavbar() {
        return "admin-navbar";
    }
}
