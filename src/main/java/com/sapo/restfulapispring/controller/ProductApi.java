package com.sapo.restfulapispring.controller;

import com.sapo.restfulapispring.model.Product;
import com.sapo.restfulapispring.model.request.ProductRequest;
import com.sapo.restfulapispring.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductApi {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts(@RequestParam String name, @RequestParam int page) {
        return productService.findAll(page,name);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getOneProducts(@PathVariable Long id) {
        return productService.findOne(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return productService.create(productRequest);
    }

    @RequestMapping(value = "/products/{id}/{status}", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody @Valid ProductRequest productRequest, @PathVariable long id, @PathVariable int status) throws NoSuchFieldException, IllegalAccessException {
        return productService.update(productRequest, id, status);
    }

}
