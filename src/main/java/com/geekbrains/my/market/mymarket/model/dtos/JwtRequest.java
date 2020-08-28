package com.geekbrains.my.market.mymarket.model.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
