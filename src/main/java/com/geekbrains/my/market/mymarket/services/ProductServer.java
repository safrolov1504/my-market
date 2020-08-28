package com.geekbrains.my.market.mymarket.services;


import com.geekbrains.my.market.mymarket.model.Product;
import com.geekbrains.my.market.mymarket.model.dtos.ProductDto;
import com.geekbrains.my.market.mymarket.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServer {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAll(Specification<Product> spec, Integer page){
        return productRepository.findAll(spec, PageRequest.of(page-1,5));
    }

    public Product saveOrUpdate(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<ProductDto> getDtoData() {
        return productRepository.findAllBy();
    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
