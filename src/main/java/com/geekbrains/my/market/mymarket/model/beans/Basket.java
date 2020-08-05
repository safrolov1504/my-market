package com.geekbrains.my.market.mymarket.model.beans;

import com.geekbrains.my.market.mymarket.model.Product;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Basket {
    private List<ProductInBasket> listOfProduct;
    private BigDecimal price;

    public Basket() {
        listOfProduct = new ArrayList<>();
    }

    public void addProduct(ProductInBasket productInBasket){
        int check = checkProduct(productInBasket.getProduct().getId());
        if(check == -1){
            listOfProduct.add(productInBasket);
        } else {
            listOfProduct.get(check).add(productInBasket.getCount());
        }
        recalculate();
    }

    private int checkProduct(Long idProduct){
        for (int i = 0; i < listOfProduct.size(); i++) {
            if(idProduct == listOfProduct.get(i).getProduct().getId()){
                return i;
            }
        }
        return -1;
    }

    public List<ProductInBasket> getListOfProduct() {
        return listOfProduct;
    }

    public void delProduct(Long id) {
        int delId = searchById(id);
        if (listOfProduct.get(delId).getCount() > 1){
            listOfProduct.get(delId).delOne();
        } else {
            listOfProduct.remove(delId);
        }
    }

    private int searchById(Long id){
        for (int i = 0; i < listOfProduct.size(); i++) {
            if(id == listOfProduct.get(i).getProduct().getId()){
                return i;
            }
        }
        throw new RuntimeException("What?");
    }

    public void incrementBasket(int idProduct){
        for (ProductInBasket p:listOfProduct) {
            if(p.getProduct().getId() == idProduct){
                p.increment();
                recalculate();
                break;
            }
        }
    }

    public void decrementBasket(int idProduct){
        for (ProductInBasket p:listOfProduct) {
            if(p.getProduct().getId() == idProduct){
                p.decrement();
                recalculate();
                break;
            }
        }
    }

    public void recalculate() {
        price = new BigDecimal(0.0);
        for (ProductInBasket i : listOfProduct) {
            price = price.add(i.getPrice());
        }
    }

    public void clear() {
        listOfProduct.clear();
        recalculate();
    }
}
