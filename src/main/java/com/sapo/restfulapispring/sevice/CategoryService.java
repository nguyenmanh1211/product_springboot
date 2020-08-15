package com.sapo.restfulapispring.sevice;

import com.sapo.restfulapispring.model.Category;
import com.sapo.restfulapispring.model.Product;
import com.sapo.restfulapispring.model.request.ProductRequest;

import java.util.List;

public interface CategoryService {
    List<Category> findAll(int page);

    Category create(ProductRequest productRequest);

    Category update(ProductRequest productRequest, long id,int status);

    Category findOne(long id);

    void delete(Long id);
}
