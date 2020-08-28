package com.geekbrains.my.market.mymarket.repositories;


import com.geekbrains.my.market.mymarket.model.Product;
import com.geekbrains.my.market.mymarket.model.dtos.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    List<ProductDto> findAllBy();


    Product findByTitle(String name);
}
