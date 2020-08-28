package com.geekbrains.my.market.mymarket.ws;

import com.geekbrains.my.market.mymarket.repositories.ProductRepository;
import com.geekbrains.my.market.mymarket.ws.products.GetProductRequest;
import com.geekbrains.my.market.mymarket.ws.products.GetProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductsEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/market/ws/products";

    private ProductRepository productRepository;

    @Autowired
    public ProductsEndpoint(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        response.setProduct(productRepository.findByTitle(request.getTitle()));
        return response;
    }
}
