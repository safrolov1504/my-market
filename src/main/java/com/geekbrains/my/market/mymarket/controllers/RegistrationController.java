package com.geekbrains.my.market.mymarket.controllers;

import com.geekbrains.my.market.mymarket.model.User;
import com.geekbrains.my.market.mymarket.model.dtos.SystemUser;
import com.geekbrains.my.market.mymarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class RegistrationController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register")
    public String showMyLoginPage(Model model) {
        model.addAttribute("systemUser", new SystemUser());
        return "registration-form";
    }

    @PostMapping("/register/process")
    public String processRegistrationForm(@ModelAttribute("systemUser") @Validated SystemUser systemUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }
        Optional<User> existing = userService.findUserByName(systemUser.getName());
        if (existing.isPresent()) {
            model.addAttribute("registrationError", "User with phone number: [" + systemUser.getName() + "] is already exist");
            systemUser.setName(null);
            model.addAttribute("systemUser", systemUser);
            return "registration-form";
        }
        userService.save(systemUser);
        return "registration-confirmation";
    }
}
