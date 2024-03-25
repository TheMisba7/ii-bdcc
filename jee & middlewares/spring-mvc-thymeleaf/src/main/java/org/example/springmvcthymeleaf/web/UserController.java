package org.example.springmvcthymeleaf.web;

import jakarta.validation.Valid;
import org.example.springmvcthymeleaf.app.UserApp;
import org.example.springmvcthymeleaf.dao.RoleDao;
import org.example.springmvcthymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/users")
public class UserController {
    private final UserApp userApp;
    private final RoleDao roleDao;

    public UserController(UserApp userApp, RoleDao roleDao) {
        this.userApp = userApp;
        this.roleDao = roleDao;
    }

    @GetMapping
    public String displayUsers(final Model model) {
        model.addAttribute("users", userApp.getAll());
        return "users";
    }
    @GetMapping(path = "/register")
    public String newUserForm(final Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleDao.findAll());
        return "register";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute final User user,
                           final BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "register";
        userApp.create(user);
        return "redirect:/users";
    }
}
