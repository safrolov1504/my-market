package com.geekbrains.my.market.mymarket.controllers;

import com.geekbrains.my.market.mymarket.model.User;
import com.geekbrains.my.market.mymarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String profilePage(Principal principal, Model model){
        String name = principal.getName();
        System.out.println(name);
        User user = userService.findUserByName(name).get();
        System.out.println(user.toString());
        model.addAttribute("user",user);
        return "profile";
    }
}
