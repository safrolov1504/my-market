package com.geekbrains.my.market.mymarket.restControllers;

import com.geekbrains.my.market.mymarket.exceptions.ProductNotFoundException;
import com.geekbrains.my.market.mymarket.model.Product;
import com.geekbrains.my.market.mymarket.model.User;
import com.geekbrains.my.market.mymarket.model.dtos.ProductDto;
import com.geekbrains.my.market.mymarket.model.dtos.SystemUser;
import com.geekbrains.my.market.mymarket.services.ProductServer;
import com.geekbrains.my.market.mymarket.services.UserService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/products")
@Api("Set of endpoints for CRUD operations for Products")
public class RestProductController {
    private ProductServer productsService;
    private Product product;


    @Autowired
    public RestProductController(ProductServer productsService) {
        this.productsService = productsService;
    }



    @GetMapping("/dto")
    @ApiOperation("Returns list of all products data transfer objects")
    public List<ProductDto> getAllProductsDto() {
        return productsService.getDtoData();
    }

    @GetMapping(produces = "application/json")
    @ApiOperation("Returns list of all products")
    public List<Product> getAllProducts() {
        return productsService.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation("Returns one product by id")
    public ResponseEntity<?> getOneProduct(@PathVariable @ApiParam("Id of the product to be requested. Cannot be empty") Long id) {
        if (!productsService.existsById(id)) {
            FieldError error = new FieldError("searchError", "ID",
                    "Product with id "+id + " didn't find");
            BindingResult bindingResult = new BeanPropertyBindingResult(product,"product");
            bindingResult.addError(error);
            //throw new ProductNotFoundException("Product not found, id: " + id);
            String json = new Gson().toJson(bindingResult);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productsService.findById(id), HttpStatus.OK);
        }
    }


    @DeleteMapping
    @ApiOperation("Removes all products")
    public void deleteAllProducts() {
        productsService.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Removes one product by id")
    public void deleteOneProduct(@PathVariable Long id) {
        productsService.deleteById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a new product")
    public ResponseEntity<?> saveNewProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            product.setId(null);
        }
        return new ResponseEntity<>(productsService.saveOrUpdate(product),HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation("Modifies an existing product")
    public ResponseEntity<?> modifyProduct(@RequestBody Product product) {
        if (product.getId() == null || !productsService.existsById(product.getId())) {
            throw new ProductNotFoundException("Product not found, id: " + product.getId());
        }
        if (product.getPrice().doubleValue() < 0.0) {
            return new ResponseEntity<>("Product's price can not be negative", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productsService.saveOrUpdate(product), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(ProductNotFoundException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }
}
