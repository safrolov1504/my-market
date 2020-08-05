package com.geekbrains.my.market.mymarket.repositories;

import com.geekbrains.my.market.mymarket.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
