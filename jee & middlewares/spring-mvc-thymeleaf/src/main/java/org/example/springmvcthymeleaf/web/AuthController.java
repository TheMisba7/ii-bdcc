package org.example.springmvcthymeleaf.web;

import org.example.springmvcthymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping(path = "/login")
    public String login(final Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
}
