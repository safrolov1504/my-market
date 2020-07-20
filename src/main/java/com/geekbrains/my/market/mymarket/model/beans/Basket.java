package com.geekbrains.my.market.mymarket.model.beans;

import com.geekbrains.my.market.mymarket.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Basket {
    private List<ProductInBasket> listOfProduct;

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


}
