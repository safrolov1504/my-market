package com.geekbrains.my.market.mymarket.controllers;

import com.geekbrains.my.market.mymarket.model.Product;
import com.geekbrains.my.market.mymarket.model.beans.Basket;
import com.geekbrains.my.market.mymarket.model.beans.ProductInBasket;
import com.geekbrains.my.market.mymarket.services.CategoryService;
import com.geekbrains.my.market.mymarket.services.ProductServer;
import com.geekbrains.my.market.mymarket.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/basket")
@AllArgsConstructor
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
            model.addAttribute("totalPrice", basket.getPrice());
        }
        return "basket";
    }

    @GetMapping("/addToBasket")
    public void addToBasket(@RequestParam Map<String,String> requestMap,
                              HttpServletResponse response, HttpServletRequest request) throws IOException {
        Long id = Long.parseLong(requestMap.get("idInBasket"));
        Integer count = Integer.parseInt(requestMap.get("countInBasket"));
        addToBasketIn(id, count);

        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/increment/{productId}")
    public void incrementToBasket(HttpServletResponse response, HttpServletRequest request,
                                  @PathVariable String productId) throws IOException {
        basket.incrementBasket(Integer.parseInt(productId));
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/decrement/{productId}")
    public void decrementToBasket(HttpServletResponse response, HttpServletRequest request,
                                  @PathVariable String productId) throws IOException {
        basket.decrementBasket(Integer.parseInt(productId));
        response.sendRedirect(request.getHeader("referer"));
    }

    private void addToBasketIn(Long id, int count){
        if(basket == null){
            basket = new Basket();
        }
        basket.addProduct(new ProductInBasket(productServer.findById(id),count));
    }

    @GetMapping("/del/{id}")
    public String delFromBasket(@PathVariable Long id){
        basket.delProduct(id);
        return "redirect:/basket/";
    }
}
