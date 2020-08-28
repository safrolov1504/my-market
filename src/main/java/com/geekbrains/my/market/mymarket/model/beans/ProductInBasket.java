package com.geekbrains.my.market.mymarket.model.beans;

import com.geekbrains.my.market.mymarket.model.Order;
import com.geekbrains.my.market.mymarket.model.Product;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders_items")
@Data
public class ProductInBasket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer count;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public ProductInBasket() {
    }

    public ProductInBasket(Product product, Integer count) {
        this.product = product;
        this.count = count;
        this.price = product.getPrice().multiply(new BigDecimal(count));
    }

    public void increment(){
        this.count++;
    }

    public void decrement(){
        this.count--;
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

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void add(Integer count) {
        this.count+=count;
    }
}
