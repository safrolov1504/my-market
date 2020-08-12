package com.geekbrains.my.market.mymarket.services;

import com.geekbrains.my.market.mymarket.model.Role;
import com.geekbrains.my.market.mymarket.model.User;
import com.geekbrains.my.market.mymarket.model.dtos.SystemUser;
import com.geekbrains.my.market.mymarket.repositories.RoleRepository;
import com.geekbrains.my.market.mymarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private RolesService rolesService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByName(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        if (user.getStatus().equals("false")){
            throw new UsernameNotFoundException("User is blocked");
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public Optional<User> findUserByName(String user){
        return userRepository.findOneByName(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void changeStatus(String id, String status){
        Long idLong = Long.parseLong(id);
        User newUser = userRepository.findById(idLong).get();
        newUser.setStatus(status);
        userRepository.save(newUser);
    }

    public User findByPhone(String phone) {
        return userRepository.findOneByPhone(phone).get();
    }

    @Transactional
    public User save(SystemUser systemUser) {
        User user = new User();
        findUserByName(systemUser.getName()).ifPresent((u) -> {
            throw new RuntimeException("User with phone " + systemUser.getName() + " is already exist");
        });
        user.setName(systemUser.getName());
        user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        user.setFirstName(systemUser.getFirstName());
        user.setSecondName(systemUser.getLastName());
        user.setPhone(systemUser.getPhone());
        user.setEmail(systemUser.getEmail());
        user.setRoles(Arrays.asList(rolesService.findByName("ROLE_CUSTOMER")));
        user.setStatus("true");
        return userRepository.save(user);
    }
}
