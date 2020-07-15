package com.geekbrains.my.market.mymarket.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;


    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;


    public Product(Long id, String title, int price, Category category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
    }
}
