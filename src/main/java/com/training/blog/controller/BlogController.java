package com.training.blog.controller;

import javax.validation.Valid;

import com.training.blog.data.model.Blog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface BlogController{

    @GetMapping("/blogs")
    public ResponseEntity GetAll();
    
    @PostMapping("/blogs")
    public ResponseEntity saveOrUpdate(@Valid @RequestBody Blog blog);

}