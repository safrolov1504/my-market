package com.geekbrains.my.market.mymarket.model.beans;

import com.geekbrains.my.market.mymarket.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductInBasket {
//    private Long productId;
    private Product product;
    private Integer count;

    public ProductInBasket() {
    }

    public ProductInBasket(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public void addOneMore(){
        this.count++;
    }

    public void delOne(){
        this.count--;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getCount() {
        return count;
    }

    public void add(Integer count) {
        this.count+=count;
    }
}
