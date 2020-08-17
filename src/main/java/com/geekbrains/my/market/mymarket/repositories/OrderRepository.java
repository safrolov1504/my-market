package com.geekbrains.my.market.mymarket.repositories;

import com.geekbrains.my.market.mymarket.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> {
}
