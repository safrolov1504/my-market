package com.geekbrains.my.market.mymarket;

import com.geekbrains.my.market.mymarket.controllers.ProductController;
import com.geekbrains.my.market.mymarket.model.dtos.ProductDto;
import com.geekbrains.my.market.mymarket.services.ProductServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AllProductsTest {


    @MockBean
    private ProductServer productServer;

    @Test
    void allProductsTest() throws Exception {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        ProductDto productDto = factory.createProjection(ProductDto.class);
        productDto.setTitle("PS");
        List<ProductDto> allProducts  = Arrays.asList(
                productDto
        );
        given(productServer.getDtoData()).willReturn(allProducts);

    }
}
