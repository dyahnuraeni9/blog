package com.training.blog.controller.impl;

import javax.validation.Valid;

import com.training.blog.controller.CategoryController;
import com.training.blog.model.Categories;
import com.training.blog.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryImplController implements CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseEntity getCategories(Integer id) {
        return categoryService.getById(id);
    }

    @Override
    public ResponseEntity getAllCategoriess() {
        return categoryService.getAllCategories();
    }

    @Override
    public ResponseEntity saveCategories(@Valid Categories categories) {
        return categoryService.save(categories);
    }

    @Override
    public ResponseEntity updateCategories(@Valid Categories categories, Integer id) {
        return categoryService.update(categories, id);
    }

    @Override
    public ResponseEntity deleteCategories(Integer id) {
        return categoryService.delete(id);
    }

}