package com.geekbrains.my.market.mymarket.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public interface ProductDto {
    Long getId();
    String getTitle();
    BigDecimal getPrice();

    void setTitle(String harry_potter);
}
