package org.example.springmvcthymeleaf.web;

import jakarta.validation.Valid;
import org.example.springmvcthymeleaf.app.UserApp;
import org.example.springmvcthymeleaf.dao.RoleDao;
import org.example.springmvcthymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("currentUser", UserApp.getCurrentUser());
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
        userApp.createOrUpdate(user);
        return "redirect:/users";
    }

    @GetMapping("/edit-user")
    public String editUser(final Model model, @RequestParam("id") long id) {
        model.addAttribute("user", userApp.getOrElseThrow(id, "could not find user with id " + id));
        model.addAttribute("roles", roleDao.findAll());
        return "register";
    }

    @DeleteMapping("/id")
    public String delete(final Model model, @PathVariable("id") long id) {
        userApp.delete(id);
        model.addAttribute("users", userApp.getAll());
        return "users :: content";
    }
}
