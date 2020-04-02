package com.training.blog.controller;

import javax.validation.Valid;

import com.training.blog.model.Categories;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface CategoryController{

    @GetMapping("/categories/{id}")
    public ResponseEntity getCategories(@PathVariable("id") Integer id);

    @GetMapping("/categories")
    public ResponseEntity getAllCategoriess();
    
    @PostMapping("/categories")
    public ResponseEntity saveCategories(@Valid @RequestBody Categories categories);

    @PutMapping("/categories/{id}")
    public ResponseEntity updateCategories(@Valid @RequestBody Categories categories, @PathVariable("id") Integer id);

    @DeleteMapping("/categories/{id}")
    public ResponseEntity deleteCategories(@PathVariable("id") Integer id);

}