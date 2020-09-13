package com.geekbrains.my.market.mymarket;

import com.geekbrains.my.market.mymarket.model.Product;
import com.geekbrains.my.market.mymarket.model.beans.Basket;
import com.geekbrains.my.market.mymarket.model.beans.ProductInBasket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class CartTest {

    @Test
    void cartTest(){
        Basket basket = new Basket();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId((long)i);
            product.setPrice(new BigDecimal(i));

            ProductInBasket productInBasket = new ProductInBasket();
            productInBasket.setProduct(product);
            productInBasket.setPrice(new BigDecimal(i));
            basket.addProduct(productInBasket);
        }

        Assertions.assertEquals(10,basket.getListOfProduct().size());
    }
}
