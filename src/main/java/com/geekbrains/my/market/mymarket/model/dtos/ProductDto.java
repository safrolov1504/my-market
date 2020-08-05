package com.geekbrains.my.market.mymarket.model.dtos;

import java.math.BigDecimal;

public interface ProductDto {
    Long getId();
    String getTitle();
    BigDecimal getPrice();
}
