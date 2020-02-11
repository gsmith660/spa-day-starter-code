package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping
    public String displayUsers(Model model) {
        model.addAttribute("users", UserData.getAll());
        return "user/index";
    }

    @GetMapping("add")
    public String displayAddUserForm() {
        return "user/add";
    }

    @PostMapping("add")
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {
        if (user.getPassword().equals(verify)) {
            UserData.add(user);
            model.addAttribute("users", UserData.getAll());
            return "redirect:";
        } else {
            model.addAttribute("error", "Passwords entered do not match!");
            model.addAttribute("user", user);
            return "user/add";
        }
    }

    @GetMapping("{userId}")
    public String displayUser(Model model, @PathVariable int userId) {
        model.addAttribute("user", UserData.getById(userId));
        return "user/detail";
    }

}
