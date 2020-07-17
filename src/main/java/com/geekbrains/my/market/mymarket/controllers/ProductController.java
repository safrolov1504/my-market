package com.geekbrains.my.market.mymarket.controllers;


import com.geekbrains.my.market.mymarket.model.Category;
import com.geekbrains.my.market.mymarket.model.Product;

import com.geekbrains.my.market.mymarket.model.beans.Basket;
import com.geekbrains.my.market.mymarket.model.beans.ProductInBasket;
import com.geekbrains.my.market.mymarket.repositories.ProductSpecifications;
import com.geekbrains.my.market.mymarket.services.CategoryService;
import com.geekbrains.my.market.mymarket.services.ProductServer;
import com.geekbrains.my.market.mymarket.utils.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {
    ProductServer productServer;
    CategoryService categoryService;
    private Integer page;


    @Autowired
    public void setProductServer(ProductServer productServer, CategoryService categoryService) {
        this.productServer = productServer;
        this.categoryService = categoryService;
        page = 1;
    }

    @GetMapping
    public String showAll(Model model,
                          @RequestParam Map<String,String> requestMap){
        Integer pageNumber = Integer.parseInt(requestMap.getOrDefault("p", "1"));
        List<Category> categories = categoryService.getAll();

        ProductFilter productFilter = new ProductFilter(requestMap,categories);

        Page<Product> products = productServer.getAll(productFilter.getSpec(), pageNumber);

        model.addAttribute("products",products);
        model.addAttribute("categories",categories);
        model.addAttribute("filterDef",productFilter.getFilterDefinition().toString());
        return "all_products";
    }

    @GetMapping("/add")
    public String showAddForm(){
        return "add_product_form";
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product newProduct){
        productServer.saveOrUpdate(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProduct(@PathVariable Long id, Model model){
        model.addAttribute("product",productServer.findById(id));
        return "edit_product_form";
    }


    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product editProduct){
        productServer.saveOrUpdate(editProduct);
        return "redirect:/products";
    }
}
