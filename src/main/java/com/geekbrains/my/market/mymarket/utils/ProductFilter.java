package com.geekbrains.my.market.mymarket.utils;


import com.geekbrains.my.market.mymarket.model.Product;
import com.geekbrains.my.market.mymarket.repositories.ProductSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private StringBuilder filterDefinition;

    public ProductFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("min_price") && !map.get("min_price").isEmpty()) {
            int minPrice = Integer.parseInt(map.get("min_price"));
            spec = spec.and(ProductSpecifications.PriceGEThan(minPrice));
            filterDefinition.append("&min_price=").append(minPrice);
        }
        if (map.containsKey("max_price") && !map.get("max_price").isEmpty()) {
            int maxPrice = Integer.parseInt(map.get("max_price"));
            spec = spec.and(ProductSpecifications.PriceLEThan(maxPrice));
            filterDefinition.append("&max_price=").append(maxPrice);
        }
        if(map.containsKey("title_search") && !map.get("title_search").isEmpty()){
            String nameLike = map.get("title_search");
            spec = spec.and(ProductSpecifications.NameLike(nameLike));
            filterDefinition.append("&title_search=").append(nameLike);
        }
    }

    public Specification<Product> getSpec() {
        return spec;
    }

    public StringBuilder getFilterDefinition() {
        return filterDefinition;
    }
}
