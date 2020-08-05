package com.geekbrains.my.market.mymarket.controllers;

import com.geekbrains.my.market.mymarket.model.User;
import com.geekbrains.my.market.mymarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String profilePage(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "admin";
    }

    @GetMapping("/block")
    public String blocUser(@RequestParam String id){
        System.out.println("block "+id);
        userService.changeStatus(id, "false");
        return "redirect:/admin";
    }

    @GetMapping("/unblock")
    public String unblockUser(@RequestParam String id){
        System.out.println("unblock "+id);
        userService.changeStatus(id, "true");
        return "redirect:/admin";
    }
}
