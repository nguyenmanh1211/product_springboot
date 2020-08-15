package com.sapo.restfulapispring.sevice;

import com.sapo.restfulapispring.model.Product;
import com.sapo.restfulapispring.model.request.ProductRequest;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> findAll(int page,String name);

    Product create(ProductRequest productRequest);

    Product update(ProductRequest productRequest, long id,int status);

    Product findOne(long id);
}
