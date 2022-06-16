package hac.ex4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the fragments.
 */
@Controller
public class FragmentController {

    /**
     * The admin navbar.
     * @return - the admin navbar
     */
    @GetMapping("/admin-navbar")
    public String adminNavbar() {
        return "admin-navbar";
    }
}
