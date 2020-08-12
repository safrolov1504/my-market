package com.geekbrains.my.market.mymarket.services;

import com.geekbrains.my.market.mymarket.model.Role;
import com.geekbrains.my.market.mymarket.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {
    private RoleRepository rolesRepository;

    @Autowired
    public void setRolesRepository(RoleRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Role findByName(String name) {
        return rolesRepository.findOneByName(name);
    }
}
