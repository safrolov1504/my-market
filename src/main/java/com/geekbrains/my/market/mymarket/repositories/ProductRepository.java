package com.geekbrains.my.market.mymarket.repositories;


import com.geekbrains.my.market.mymarket.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
//    List<Product> findAllByPriceGreaterThan(double minPrice, PageRequest of);
//
//    List<Product> findAllByPriceLessThan(double maxPrice, PageRequest of);
//
//    List<Product> findAllByPriceGreaterThanAndLessThan(double minPrice, double maxPrice, PageRequest of);

}
