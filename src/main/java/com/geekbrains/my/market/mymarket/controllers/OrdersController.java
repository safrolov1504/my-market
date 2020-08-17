package com.geekbrains.my.market.mymarket.controllers;

import com.geekbrains.my.market.mymarket.model.Order;
import com.geekbrains.my.market.mymarket.model.User;
import com.geekbrains.my.market.mymarket.model.beans.Basket;
import com.geekbrains.my.market.mymarket.services.OrderService;
import com.geekbrains.my.market.mymarket.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {
    private UserService usersService;
    private OrderService ordersService;
    private Basket basket;

    @GetMapping("/create")
    public String createOrder(Principal principal, Model model) {
        User user = usersService.findUserByName(principal.getName()).get();
        model.addAttribute("user", user);
        model.addAttribute("basket",basket);
        return "order_info";
    }

    @PostMapping("/confirm")
    public String confirmOrder(Model model,
                               Principal principal, @RequestParam String address, @RequestParam String phone) {
        User user = usersService.findByPhone(principal.getName());
        Order order = new Order(user, basket, phone, address);
        order = ordersService.saveOrder(order);
        model.addAttribute("totalPrice", order.getPrice());
        return "order_results";
    }
}
