package com.geekbrains.my.market.mymarket.repositories;

import com.geekbrains.my.market.mymarket.model.Product;
import com.geekbrains.my.market.mymarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>,JpaRepository<User,Long> {
    Optional<User> findOneByName(String name);
}
