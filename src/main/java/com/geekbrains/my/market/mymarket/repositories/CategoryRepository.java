package com.geekbrains.my.market.mymarket.repositories;

import com.geekbrains.my.market.mymarket.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
