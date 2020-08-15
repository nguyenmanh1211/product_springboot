package com.sapo.restfulapispring.sevice.Impl;

import com.sapo.restfulapispring.dao.common.ObjectDAO;
import com.sapo.restfulapispring.model.Category;
import com.sapo.restfulapispring.model.Product;
import com.sapo.restfulapispring.model.request.ProductRequest;
import com.sapo.restfulapispring.sevice.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ObjectDAO<Product> productDAO;

    @Autowired
    ObjectDAO<CategoryService> categoryDAO;

    @Override
    public List<Category> findAll(int page) {
        return null;
    }

    @Override
    public Category create(ProductRequest productRequest) {
        return null;
    }

    @Override
    public Category update(ProductRequest productRequest, long id, int status) {
        return null;
    }

    @Override
    public Category findOne(long id) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productDAO.delete("delete from product where category_id =1");
        categoryDAO.delete("delete from category where id=2");
    }
}
