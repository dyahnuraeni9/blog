package com.training.blog.service;

import com.training.blog.model.Categories;

import org.springframework.http.ResponseEntity;

public interface CategoryService {

    public ResponseEntity save(Categories Categories);

    public ResponseEntity update(Categories Categories, Integer id);

    public ResponseEntity getAllCategories();

    public ResponseEntity getById(Integer id);

    public ResponseEntity delete(Integer id);

}