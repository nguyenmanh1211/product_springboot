package com.sapo.restfulapispring.controller;

import com.sapo.restfulapispring.sevice.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryApi {
    @Autowired
    CategoryService categoryService;

    @DeleteMapping("/x")
    public void d(){
        categoryService.delete(2L);
    }
}
