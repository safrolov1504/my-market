package com.geekbrains.my.market.mymarket;

import com.geekbrains.my.market.mymarket.model.Product;
import com.geekbrains.my.market.mymarket.services.ProductServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductServer productServer;

    @Test
    void productServerTest(){
        Product product = productServer.findById(1L);
        System.out.println(product);
        assertThat(product).isNotNull();
    }


}
