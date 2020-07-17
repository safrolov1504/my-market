package com.geekbrains.my.market.mymarket.controllers;

import com.geekbrains.my.market.mymarket.model.Product;
import com.geekbrains.my.market.mymarket.model.beans.Basket;
import com.geekbrains.my.market.mymarket.model.beans.ProductInBasket;
import com.geekbrains.my.market.mymarket.services.CategoryService;
import com.geekbrains.my.market.mymarket.services.ProductServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/basket")
public class BasketController {
    private Basket basket;
    ProductServer productServer;
    CategoryService categoryService;

    @Autowired
    public void setProductServer(ProductServer productServer, CategoryService categoryService) {
        this.productServer = productServer;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        if (basket != null){
            model.addAttribute("productsInBasket", basket.getListOfProduct());
        }
        return "basket";
    }

    @GetMapping("/addToBasket/{id}")
    public String addToBasket(@PathVariable Long id){
        addToBasketIn(id);
        return "redirect:/products";
    }

    @GetMapping("/addToBasket/{id}/{filter}")
    public String addToBasket(@PathVariable Long id,
                              @PathVariable(required = false
                              ) String filter){
        addToBasketIn(id);
        System.out.println(filter);
        return "redirect:/products?p=1"+filter;
    }

    private void addToBasketIn(Long id){
        if(basket == null){
            basket = new Basket();
        }
        basket.addProduct(new ProductInBasket(productServer.findById(id),1));
    }

    @GetMapping("/del/{id}")
    public String delFromBasket(@PathVariable Long id){
        basket.delProduct(id);
        return "redirect:/basket/";
    }
}
